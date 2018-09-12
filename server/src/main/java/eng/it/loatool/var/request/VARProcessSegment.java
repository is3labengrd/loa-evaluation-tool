package eng.it.loatool.var.request;


import eng.it.util.PropertyManager;

import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class VARProcessSegment {

	public static String prefix() {

		return "prefix b2mml: <http://www.mesa.org/xml/B2MML-V0600#>\r\n" + 
				"prefix var:  <http://a4blue/ontologies/var.owl#>\r\n" + 
				"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\r\n" + 
				"PREFIX owl:  <http://www.w3.org/2002/07/owl#>\r\n" + 
				"PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>";
	};
		
	public static String getProcessSegmentList() throws IOException {
        PropertyManager prop = new PropertyManager();
        final String BASE_URL = prop.getPropValues("base.url");
		final String uri = BASE_URL + "/SPARQLQuery";

	    RestTemplate restTemplate = new RestTemplate();
	    String request = prefix() + "SELECT *" +
	    		"WHERE { ?list a b2mml:ProcessSegment ." +
	    		"}";
	    String result = restTemplate.postForObject(uri, request, String.class);
	     
	    return result;
	}
	
	public static String getProcessSegmentAttribute(String addressSpace) throws IOException {
        PropertyManager prop = new PropertyManager();
        final String BASE_URL = prop.getPropValues("base.url");
	    final String uri = BASE_URL + "/SPARQLQuery";
	     
	    RestTemplate restTemplate = new RestTemplate();
	    String request = prefix() + "SELECT DISTINCT ?name ?value ?type ?range" + 
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
	

}
