package eng.it.loatool.var.request;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eng.it.loatool.var.bean.Attrs;
import eng.it.loatool.var.bean.Processes;

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

}
