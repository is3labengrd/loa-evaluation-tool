package eng.it.loatool.var.service;

import java.io.IOException;
import eng.it.loatool.var.bean.MainProcess;
import eng.it.loatool.var.request.*;

public class VARServiceWrapper {

	
	/**
	  * Wrap the Processes Segment List recovered from the VAR
	  * @return Process hierarchy as json string
	  */

	 public static MainProcess getProcessesSegmentList () throws IOException {
		 	return VARProcessSegmentImpl.main();
	 }
	 
	 /**
	  * Wrap the Processes Segment List recovered from the VAR
	  * @return
	  */
	 /*
	 public static String getProcessesSegmentList () {
		 String result = "[{\"name\":\"Rear Light Assembly\", \"processSegmentId\":\"1\", \"SubProcLowerLevel\":2, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\" }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 2\", \"processSegmentId\":\"20\", \"SubProcLowerLevel\":3, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] } ] }, { \"name\":\"Rear Light Assembly 3\", \"processSegmentId\":\"1\", \"SubProcLowerLevel\":3, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\", \"subProcesses\":[ { \"level\":3, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" } ] }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 88\", \"processSegmentId\":\"22\", \"SubProcLowerLevel\":2, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\", \"subProcesses\":[ { \"level\":2, \"name\":\"Rear light adjustment\", \"processSegmentId\":\"3\" }, { \"level\":2, \"name\":\"Rivet nut fastening\", \"processSegmentId\":\"4\" }, { \"level\":2, \"name\":\"Trolley Movement\", \"processSegmentId\":\"5\" } ] }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\", \"subProcesses\":[ { \"level\":2, \"name\":\"Final Inspection\", \"processSegmentId\":\"7\" } ] }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\", \"subProcesses\":[ { \"level\":2, \"name\":\"add cover plates\", \"processSegmentId\":\"9\" }, { \"level\":2, \"name\":\"install brake light module\", \"processSegmentId\":\"10\" }, { \"level\":2, \"name\":\"Mount screw\", \"processSegmentId\":\"11\" }, { \"level\":2, \"name\":\"Screw positioning\", \"processSegmentId\":\"12\" }, { \"level\":2, \"name\":\"screw rear light\", \"processSegmentId\":\"13\" } ] }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\", \"subProcesses\":[ { \"level\":2, \"name\":\"Parts grasping\", \"processSegmentId\":\"15\" } ] }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\", \"subProcesses\":[ { \"level\":2, \"name\":\"Operation star\", \"processSegmentId\":\"17\" }, { \"level\":2, \"name\":\"Initial Trolley Movement\", \"processSegmentId\":\"18\" } ] } ] }, { \"name\":\"Rear Light Assembly 9\", \"processSegmentId\":\"110\", \"SubProcLowerLevel\":1, \"subProcesses\":[ { \"level\":1, \"name\":\"Handling\", \"processSegmentId\":\"2\" }, { \"level\":1, \"name\":\"Checking\", \"processSegmentId\":\"6\" }, { \"level\":1, \"name\":\"Joining\", \"processSegmentId\":\"8\" }, { \"level\":1, \"name\":\"Picking\", \"processSegmentId\":\"14\" }, { \"level\":1, \"name\":\"Preparation\", \"processSegmentId\":\"16\" } ] } ]";
		 return result;
	 }*/

	/**
	 * Input (json string) =
	 *
	 * "{" +
	 *                 "\"assetName\": \"Assetname\"," +
	 *                 "\"loAPhysical\": 0," +
	 *                 "\"loACognitive\": 0," +
	 *                 "\"numberOfOperators\": 0," +
	 *                 "\"annualMaintenanceCost\": 0," +
	 *                 "\"installationSurface\": 0," +
	 *                 "\"costPerSurfacePerMonth\": 0," +
	 *                 "\"machinePurchaseValue\": 0," +
	 *                 "\"machineSalesValue\": 0," +
	 *                 "\"economicUsefulLife\": 0," +
	 *                 "\"annualElectricityConsumptionWhileWorking\" : 0," +
	 *                 "\"annualElectricityConsumptionStandBy\" : 0," +
	 *                 "\"equipmentId\": \"equipmentId\"," +
	 *                 "\"equipmentLevel\": \"equipmentLevel\"," +
	 *                 "\"interestRate\": 0 ," +
	 *                 "\"electricityPrice\": 0" +
	 *                 "}"
	 *
	 *
	 * Create WorkUnit Instance on Add Resource
	 * @return void
	 */

	public static void addResource (String json) throws IOException {

		VARWorkUnitImpl.createBody(json);

	}

	/**
	 * Input (json string) =
	 *
	 * 	  "{" +
	 * 	                  "\"assetName\": \"Assetname\"," +
	 * 	                  "\"loAPhysical\": 0," +
	 * 	                  "\"loACognitive\": 0," +
	 * 	                  "\"numberOfOperators\": 0," +
	 * 	                  "\"annualMaintenanceCost\": 0," +
	 * 	                  "\"installationSurface\": 0," +
	 * 	                  "\"costPerSurfacePerMonth\": 0," +
	 * 	                  "\"machinePurchaseValue\": 0," +
	 * 	                  "\"machineSalesValue\": 0," +
	 * 	                  "\"economicUsefulLife\": 0," +
	 * 	                  "\"annualElectricityConsumptionWhileWorking\" : 0," +
	 * 	                  "\"annualElectricityConsumptionStandBy\" : 0," +
	 * 	                  "\"equipmentId\": \"equipmentId\"," +
	 * 	                  "\"equipmentLevel\": \"equipmentLevel\"," +
	 * 	                  "\"interestRate\": 0 ," +
	 * 	                  "\"electricityPrice\": 0" +
	 * 	                  "}"
	 *
	 *
	 *
	 * Update WorkUnit Instance on Edit Resource
	 * @return void
	 */

	public static void editResource (String json) throws IOException {

		VARWorkUnitImpl.updateBody(json);

	}


	/**
	 * Input (json string) =
	 *
	 * "{				\"assetName\": \"+Assetname+\"," +
	 *                 "\"productionVolume\": 0" +
	 *                 "}"
	 *
	 *
	 * Create/Modify ProductDefinition Instance on Add Product Planning
	 * @return void
	 */

	public static void editProductPlanning (String json) throws IOException {

		VARProductDefinitionImpl.createBody(json);

	}

	/**
	 * Input (json string) =
	 *
	 * "{				\"assetName\": \"+Assetname+\"," +
	 *                 "\NumberOfShiftsPerDay\": 0," +
	 *                 "\HoursPerShift\": 0," +
	 *                 "\WorkingDaysPerYear\": 0" +
	 *                 "}"
	 *
	 * Create/modify ProcessSegment Instance on Add ProductDefinition
	 * @return void
	 */

	public static void editProcessSpecificInformation (String json) throws IOException {

		VARProcessSpecificInformationImpl.createBody(json);

	}

    /**
     * Input (json string) =
     *
     * "{" +
	 * 					"\"assetName\": \"+Assetname+\"," +
     *                 "\valueString\": 0," +
     *                 "\"unitOfMeasure\": 0," +
     *                 "\propertyID\": 0" +
     *                 "}"
     *
     * Create ProportionalWageCost Instance When Sub Scenario Matrix is saved
     * @return void
     */

	public static void editProportionalWageCost (String json) throws IOException {

		VARProportionalWageCost.createBody(json);

	}

	/**
	 * input  Class Instance Name
	 *
	 * return Product Planning Instance
	 */

	public static String getProductPlanningIntance(String InstanceName) throws IOException {

		return VARGetAssetImpl.getProductPlanningIntance(InstanceName);

	}

}
