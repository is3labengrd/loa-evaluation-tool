package eng.it.loatool.var.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import eng.it.loatool.var.request.VARProcessSegment;
import eng.it.loatool.var.bean.MainProcess;
import eng.it.loatool.var.bean.SubProcesses;

public class VARServiceWrapper {
	
	/**
	  * Wrap the Processes Segment List recovered from the VAR
	  * @return Json string
	  */
	
	 public static void getProcessesSegmentListTODO () {

				JsonNode jsonNode = null;
				ObjectMapper objectMapper = new ObjectMapper();
				
				List<MainProcess> result = new ArrayList<>();
				List<SubProcesses> subProc = null;
		        try {
					jsonNode = objectMapper.readTree(VARProcessSegment.getProcessSegment());
									
					for (int i = 0 ; i< jsonNode.size() ; i++) {
						MainProcess main = new MainProcess();
						main.setName(jsonNode.get(i).get("normalizedName").asText());
						JsonNode attrsList = jsonNode.get(i).get("attributes");
						for (int k = 0 ; k< attrsList.size() ; k++) {
											
							if ( attrsList.get(k).get("propertyName").asText().equals("processSegmentId")) {
								main.setProcessSegmentId(attrsList.get(k).get("propertyValue").asText());
							}
						}
						main.setSubProcesses(subProc);
						result.add(main);
						
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        
		      /*  Gson gson = new Gson();
                String jsonstring = gson.toJson(result);

			    return jsonstring; */
		 

	 }
	 
	 /**
	  * Wrap the Processes Segment List recovered from the VAR
	  * @return Json string
	  */
	 public static String getProcessesSegmentList () {
         return "[{\"name\":\"Rear Light Assembly\", \"processSegmentId\":\"1\", \"SubProcLowerLevel\":2, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\" }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 2\", \"processSegmentId\":\"20\", \"SubProcLowerLevel\":3, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] } ] }, { \"name\":\"Rear Light Assembly 3\", \"processSegmentId\":\"1\", \"SubProcLowerLevel\":3, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 88\", \"processSegmentId\":\"22\", \"SubProcLowerLevel\":2, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\" }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 9\", \"processSegmentId\":\"110\", \"SubProcLowerLevel\":1, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\" }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\" }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\" }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\" }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\" } ] } ]";

	 }
	 
	 
	
}
