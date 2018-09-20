package eng.it.loatool.var.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import eng.it.loatool.var.bean.Attrs;
import eng.it.loatool.var.bean.MainProcess;
import eng.it.loatool.var.bean.Processes;
import eng.it.loatool.var.bean.SubProcesses;

public class VARProcessSegmentImpl {
	
	

	public static String stringParser(String string) {
		String[] nameparts = string.split("#", 2);
		String[] nameparts2 = nameparts[1].split("\"", 2);
		
		return nameparts2[0];
	}
	
	public static List<Processes> getProcessSegment(){
				
		ObjectMapper objectMapper = new ObjectMapper();
		JsonNode jsonNode = null;
		List<Processes> processList = new ArrayList<>();
		try {
			jsonNode = objectMapper.readTree(VARProcessSegment.getProcessSegmentList());
			
			List<String> addressSpace = new ArrayList<>();
			for (int i = 0 ; i< jsonNode.get("results").get("bindings").size() ; i++) {
				addressSpace.add(jsonNode.get("results").get("bindings").get(i).get("list").get("value").asText());
				
			}
			
			addressSpace.stream().forEach((name) -> {
				
				Processes proc = new Processes();
				
				List<Attrs> attributes = new ArrayList<Attrs>();
				
				proc.setName(stringParser(name));
				
				JsonNode attrs = null;
				try {
					attrs = objectMapper.readTree(VARProcessSegment.getProcessSegmentAttribute(name));
				
				for (int k = 0 ; k< attrs.get("results").get("bindings").size() ; k++) {
					Attrs attList = new Attrs();
						
						attList.setName(stringParser(attrs.get("results").get("bindings").get(k).get("name").get("value").asText()));
						attList.setValue(attrs.get("results").get("bindings").get(k).get("value").get("value").asText());
						attributes.add(attList);
						
				}
				
				proc.setAttr(attributes);				
				processList.add(proc);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
			    
			
			
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return processList;
	}

	public static List<Processes> buildSubProcessesTree(Processes proc, List<Processes> processes){

		List<String> subProcName = new ArrayList<String>();
		List<Processes> subProcList = new ArrayList<Processes>();

		for (Attrs attrs : proc.getAttr()) {
			if(attrs.getName().equals("isMadeOf"))
				subProcName.add(VARProcessSegmentImpl.stringParser(attrs.getValue()));
		}



		for (Processes procObj : processes) {
			for (String name : subProcName) {
				if(procObj.getName().equals(name))
					subProcList.add(procObj);
			}
		}

		return subProcList;
	}

	public static String getProcessSegmentId(List<Attrs> attrList) {
		String processSegmentId = null;
		for (Attrs attrs : attrList) {
			if(attrs.getName().equals("processSegmentId"))
				processSegmentId = attrs.getValue();
		}
		return processSegmentId;
	}

	public static List<SubProcesses> nestedSubProcesses(Processes proc, List<Processes> processes, int level) {

		List<Processes> mainProcess = buildSubProcessesTree(proc,processes);
		List<SubProcesses> SubProcessTree = new ArrayList<SubProcesses>();



		for (Processes subProcesses : mainProcess) {

			List<SubProcesses> SubProcessTree2 = new ArrayList<SubProcesses>();

			SubProcesses sub = new SubProcesses();
			sub.setLevel(level);
			sub.setName(subProcesses.getName());
			sub.setProcessSegmentId(getProcessSegmentId(subProcesses.getAttr()));

			List<Processes> subL2 = buildSubProcessesTree(subProcesses, processes);

			for (Processes processes2 : subL2) {
				List<SubProcesses> SubProcessTree3 = new ArrayList<SubProcesses>();
				SubProcesses sub2 = new SubProcesses();

				sub2.setLevel(level+1);
				sub2.setName(processes2.getName());
				sub2.setProcessSegmentId(getProcessSegmentId(processes2.getAttr()));

				List<Processes> subL3 = buildSubProcessesTree(processes2, processes);
				for (Processes processes3 : subL3) {
					SubProcesses sub3 = new SubProcesses();

					sub3.setLevel(level+3);
					sub3.setName(processes3.getName());
					sub3.setProcessSegmentId(getProcessSegmentId(processes3.getAttr()));
				}
				sub2.setSubProcesses(SubProcessTree3);
				SubProcessTree2.add(sub2);
			}
			sub.setSubProcesses(SubProcessTree2);
			SubProcessTree.add(sub);
		}
		return SubProcessTree;
	}

	public static String buildProcessSegmentTree (List<Processes> mainProcessFinal, List<Processes> processes) {

		List<Processes> mainProcess = new ArrayList<Processes>();
		List<MainProcess> mainProcessTree = new ArrayList<MainProcess>();

		for (Processes tree : mainProcessFinal) {
			mainProcess.clear();
			MainProcess main = new MainProcess();

			main.setName(tree.getName());
			main.setProcessSegmentId(getProcessSegmentId(tree.getAttr()));

			main.setSubProcesses(nestedSubProcesses(tree,processes,1));

			int count = 0;
			if(main.getSubProcesses().isEmpty())  {
				main.setSubProcLowerLevel(count);}else {
				for (SubProcesses sub1 : main.getSubProcesses()) {
					count+=1;
					if(sub1.getSubProcesses().isEmpty()) {
						main.setSubProcLowerLevel(count);}else {
						for (SubProcesses sub2 : sub1.getSubProcesses()) {
							count+=1;
							if(sub2.getSubProcesses().isEmpty()) {
								main.setSubProcLowerLevel(count);}else {
								for (SubProcesses sub3 : sub2.getSubProcesses()) {
									count+=1;
									if(sub3.getSubProcesses().isEmpty()) {
										continue;}else
										main.setSubProcLowerLevel(count);
								}
							}
						}
					}
				}
			}


			mainProcessTree.add(main);




		}

		Gson gson = new Gson();

		return gson.toJson(mainProcessTree);
	}

}
