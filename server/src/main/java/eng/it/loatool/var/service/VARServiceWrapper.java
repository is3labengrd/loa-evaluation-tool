package eng.it.loatool.var.service;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import eng.it.loatool.var.bean.Attrs;
import eng.it.loatool.var.bean.MainProcess;
import eng.it.loatool.var.bean.Processes;
import eng.it.loatool.var.bean.SubProcesses;
import eng.it.loatool.var.request.VARProcessSegmentImpl;

public class VARServiceWrapper {
	
	
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
 			
 			
 			if(main.getSubProcesses().isEmpty())  {
 				main.setSubProcLowerLevel(0);}else {	
 				for (SubProcesses sub1 : main.getSubProcesses()) {
 					if(sub1.getSubProcesses().isEmpty()) {
 						main.setSubProcLowerLevel(1);}else {
 						for (SubProcesses sub2 : sub1.getSubProcesses()) {
 							if(sub2.getSubProcesses().isEmpty()) {
 								main.setSubProcLowerLevel(2);}else {
 		 						for (SubProcesses sub3 : sub2.getSubProcesses()) {
 		 							if(sub3.getSubProcesses().isEmpty()) {
 		 								continue;}else
 		 		 						main.setSubProcLowerLevel(3);		
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
	
	
	/**
	  * Wrap the Processes Segment List recovered from the VAR
	  * @return
	  */
	
	 public static String getProcessesSegmentListDYNAMIC () {
		 
		 		List<Processes> processes = VARProcessSegmentImpl.getProcessSegment();		 		
		 		List<Processes> mainProcess = new ArrayList<Processes>();
		 		List<Processes> mainProcessFinal = new ArrayList<Processes>();
		 		List<String> checkAttrs = new ArrayList<String>();
		 		
		 		
		 		
		 		for (Processes proc : processes) {
		 			checkAttrs.clear();
		 			for (Attrs attrs : proc.getAttr()) {
		 				checkAttrs.add(attrs.getName());
					}
		 			if(!checkAttrs.contains("hasAfterEndDependency"))		 					
		 				mainProcess.add(proc);
		 								
				}
		 		
		 		for (Processes proc2 : mainProcess) {
		 			checkAttrs.clear();
		 			for (Attrs attrs : proc2.getAttr()) {
		 				checkAttrs.add(attrs.getName());
					}
		 			if (checkAttrs.contains("isMadeOf"))
		 				mainProcessFinal.add(proc2);
				}
		 				 				 		
	 			
			    return buildProcessSegmentTree(mainProcessFinal, processes);
		 

	 }
	 
	 /**
	  * Wrap the Processes Segment List recovered from the VAR
	  * @return
	  */
	 public static String getProcessesSegmentList () {
		 String result = "[{\"name\":\"Rear Light Assembly\", \"processSegmentId\":\"1\", \"SubProcLowerLevel\":2, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\" }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 2\", \"processSegmentId\":\"20\", \"SubProcLowerLevel\":3, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] } ] }, { \"name\":\"Rear Light Assembly 3\", \"processSegmentId\":\"1\", \"SubProcLowerLevel\":3, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 88\", \"processSegmentId\":\"22\", \"SubProcLowerLevel\":2, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\" }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 9\", \"processSegmentId\":\"110\", \"SubProcLowerLevel\":1, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\" }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\" }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\" }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\" }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\" } ] } ]";
		 return result;
	 }
	 
	 
	
}
