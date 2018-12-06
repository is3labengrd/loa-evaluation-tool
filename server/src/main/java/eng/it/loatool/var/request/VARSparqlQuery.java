package eng.it.loatool.var.request;


import eng.it.loatool.var.bean.Attrs;
import eng.it.util.PropertyManager;

import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VARSparqlQuery {

	private static String prefix() {

		return "prefix b2mml: <http://www.mesa.org/xml/B2MML-V0600#>\r\n" + 
				"prefix var:  <http://a4blue/ontologies/var.owl#>\r\n" + 
				"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX owl:  <http://www.w3.org/2002/07/owl#>\r\n" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
	}
		
	public static String getProcessSegmentList() throws IOException {
		String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
		final String uri = BASE_URL + "/SPARQLQuery";

	    RestTemplate restTemplate = new RestTemplate();
	    String request = prefix() + "SELECT * " +
	    		"WHERE { ?list a b2mml:ProcessSegment ." +
	    		"}";
	    String result = restTemplate.postForObject(uri, request, String.class);
	     
	    return result;
	}

	public static List<Attrs> getWorkUnitList() throws IOException {

		List<Attrs> wu = new ArrayList<Attrs>();

		String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
		final String uri = BASE_URL + "/SPARQLQuery";

		RestTemplate restTemplate = new RestTemplate();
		String WorkUnit = prefix() + "SELECT * " +
				"WHERE { ?list a b2mml:WorkUnit ." +
				"}";

		String Tool = prefix() + "SELECT * " +
				"WHERE { ?list a var:Tool ." +
				"}";

		String Robot = prefix() + "SELECT * " +
				"WHERE { ?list a var:Robot ." +
				"}";

		Attrs WU = new Attrs();
		String result1 = restTemplate.postForObject(uri, WorkUnit, String.class);
		WU.setName("WorkUnit");
		WU.setValue(result1);
		
		Attrs T = new Attrs();
		String result2 = restTemplate.postForObject(uri, Tool, String.class);
		T.setName("Tool");
		T.setValue(result2);
		
		Attrs R = new Attrs();
		String result3 = restTemplate.postForObject(uri, Robot, String.class);
		R.setName("Robot");
		R.setValue(result3);
		
		wu.add(WU);
		wu.add(T);
		wu.add(R);

		return wu;
	}

	public static String getIsMadeOf() throws IOException {
		String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
		final String uri = BASE_URL + "/SPARQLQuery";

		RestTemplate restTemplate = new RestTemplate();
		String request = prefix() + "select ?isFather ?isChildren " +
				"where{" +
				"?isChildren a b2mml:ProcessSegment ." +
				"?isFather var:isMadeOf ?isChildren ." +
				"}";
		String result = restTemplate.postForObject(uri, request, String.class);

		return result;
	}
	
	public static String getProcessSegmentAttribute(String addressSpace) throws IOException {
		String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
	    final String uri = BASE_URL + "/SPARQLQuery";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String request = prefix() + "SELECT DISTINCT ?name ?value ?type ?range " +
	    		"WHERE { ?end ?name ?value. " + 
	    		"FILTER(?end = <"+addressSpace+">)" + 
	    		"?name rdf:type ?type." + 
	    		"OPTIONAL { ?name rdfs:range ?range } " + 
	    		"  FILTER(!(?name = rdf:type))" + 
	    		"  FILTER(!(?type= owl:FunctionalProperty))}" + 
	    		"ORDER BY ?name";
	    String result = restTemplate.postForObject(uri, request, String.class);
	     
	    return result;
	}

	public static String getList() throws IOException {
		String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
		final String uri = BASE_URL + "/assets?className=ProcessSegment&retrieveForChildren=true";

		RestTemplate restTemplate = new RestTemplate();

		String result = restTemplate.getForObject(uri, String.class);

		return result;
	}
	
	public static String postWorkUnitModel(String json) throws IOException {
		String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
	    final String uri = BASE_URL + "/SPARQLUpdate";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    ObjectMapper objectMapper = new ObjectMapper();
	    JsonNode jsonNode = objectMapper.readTree(json);
	    String request = prefix() + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:loAPhysical \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:loAPhysical "+jsonNode.get("loAPhysical").asText()+" };\r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:loACognitive \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:loACognitive "+jsonNode.get("loACognitive").asText()+" };\r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:numberOfOperators \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:numberOfOperators "+jsonNode.get("numberOfOperators").asText()+" };\r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:annualMaintenanceCost \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:annualMaintenanceCost "+jsonNode.get("annualMaintenanceCost").asText()+" };\r\n" + 
	    		"						 \r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:installationSurface \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:installationSurface "+jsonNode.get("installationSurface").asText()+" };\r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:machinePurchaseValue \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:machinePurchaseValue "+jsonNode.get("machinePurchaseValue").asText()+" };\r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:economicUsefulLife \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:economicUsefulLife "+jsonNode.get("economicUsefulLife").asText()+" };\r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:annualElectricityConsumption \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:annualElectricityConsumption "+jsonNode.get("annualElectricityConsumption").asText()+" };\r\n" + 
	    		"						 \r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:costPerSurfacePerMonth \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:costPerSurfacePerMonth "+jsonNode.get("costPerSurfacePerMonth").asText()+" };						 \r\n" + 
	    		"		\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:machineSalesValue \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:machineSalesValue "+jsonNode.get("machineSalesValue").asText()+" };	\r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:annualElectricityConsumptionWhileWorking \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:annualElectricityConsumptionWhileWorking "+jsonNode.get("annualElectricityConsumptionWhileWorking").asText()+" };\r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:annualElectricityConsumptionStandBy \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:annualElectricityConsumptionStandBy "+jsonNode.get("annualElectricityConsumptionStandBy").asText()+" };						 \r\n" + 
	    		"\r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:equipmentId \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:equipmentId "+jsonNode.get("equipmentId").asText()+" };	\r\n" + 
	    		"						 \r\n" + 
	    		"DELETE DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:equipmentLevel \"1\" };\r\n" + 
	    		"INSERT DATA { <http://a4blue/ontologies/var-tekniker.owl#"+jsonNode.get("assetName").asText()+"> var:equipmentLevel "+jsonNode.get("equipmentLevel").asText()+" };		\r\n" + 
	    		"";
	    
	    String result = restTemplate.postForObject(uri, request, String.class);
	     
	    return result;
	}
}
