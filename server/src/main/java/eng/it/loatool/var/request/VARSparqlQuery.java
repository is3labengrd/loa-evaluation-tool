package eng.it.loatool.var.request;


import eng.it.util.PropertyManager;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;

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

	public static String getWorkUnitList() throws IOException {
		String BASE_URL = System.getenv("ENV_SAR_URL");
		if(BASE_URL==null){
			PropertyManager prop = new PropertyManager();
			BASE_URL = prop.getPropValues("base.url");
		}
		final String uri = BASE_URL + "/SPARQLQuery";

		RestTemplate restTemplate = new RestTemplate();
		String request = prefix() + "SELECT * " +
				"WHERE { ?list a b2mml:WorkUnit ." +
				"}";
		String result = restTemplate.postForObject(uri, request, String.class);

		return result;
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
}
