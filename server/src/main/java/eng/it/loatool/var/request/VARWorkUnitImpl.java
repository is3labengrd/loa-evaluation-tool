package eng.it.loatool.var.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eng.it.loatool.var.bean.Attrs;
import eng.it.loatool.var.bean.Individual;
import eng.it.util.PropertyManager;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class VARWorkUnitImpl {

    private static String workUnitTemplate (String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        String workUnitTemplate = "{" +
                "\"assetName\": " + jsonNode.get("assetName") + "," +
                "\"className\": " + jsonNode.get("className") + "," +
                "\"domainName\":\"\"," +
                "\"orionConfigId\": \"\"," +
                "\"attribute\":" +
                "[" +
                "{\"name\": \"loAPhysical\",\"value\":" + jsonNode.get("loAPhysical") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"loACognitive\",\"value\":"+ jsonNode.get("loACognitive") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"numberOfOperators\",\"value\":"+ jsonNode.get("numberOfOperators") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"annualMaintenanceCost\",\"value\": "+ jsonNode.get("annualMaintenanceCost") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"annualMaintenanceCostPercent\",\"value\": "+ jsonNode.get("annualMaintenanceCostPercent") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"installationSurface\",\"value\": "+ jsonNode.get("installationSurface") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"costPerSurfacePerMonth\",\"value\":"+ jsonNode.get("costPerSurfacePerMonth") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"machinePurchaseValue\",\"value\": "+ jsonNode.get("machinePurchaseValue") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"machineSalesValue\",\"value\":"+ jsonNode.get("machineSalesValue") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"economicUsefulLife\",\"value\":"+ jsonNode.get("economicUsefulLife") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"annualElectricityConsumptionWhileWorking\",\"value\":"+ jsonNode.get("annualElectricityConsumptionWhileWorking") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"annualElectricityConsumptionStandBy\",\"value\":"+ jsonNode.get("annualElectricityConsumptionStandBy") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"equipmentId\",\"value\":"+ jsonNode.get("equipmentId") +",\"type\":\"java.lang.String\"}," +
                "{\"name\": \"equipmentLevel\",\"value\":\"WORKUNIT\",\"type\":\"java.lang.String\"}" +
                "]" +
                "}";
        return workUnitTemplate;
    }

    private static String siteTemplate (String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        String siteTemplate = "{" +
                "\"assetName\": \"RWTH\"," +
                "\"className\":\"Site\"," +
                "\"modelName\": \"Site\"," +
                "\"domainName\":\"\"," +
                "\"orionConfigId\": \"\"," +
                "\"attribute\":" +
                "[" +
                "{\"name\": \"interestRate\",\"value\":"+ jsonNode.get("interestRate") +",\"type\":\"java.lang.Float\"}," +
                "{\"name\": \"electricityPrice\",\"value\":"+ jsonNode.get("electricityPrice") +",\"type\":\"java.lang.Double\"}" +
                "]" +
                "}";

        return siteTemplate;
    }

    public static void createBody(String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String workUnitTemplate = workUnitTemplate(json);
        String siteTemplate = siteTemplate(json);
        JsonNode jsonNode = objectMapper.readTree(json);

        String instance = VARInstance.getInstance("RWTH");
        JsonNode checkInstance = objectMapper.readTree(instance);
        String instance2 = VARInstance.getInstance(jsonNode.get("assetName").asText());
        JsonNode checkInstance2 = objectMapper.readTree(instance2);
        
        if (checkInstance.get(0) == null)
            VARInstance.createInstance(siteTemplate);
        else
            VARInstance.updateInstance(siteTemplate);
        
        if (checkInstance2.get(0) == null)
        	VARInstance.createInstance(workUnitTemplate);
        else{
        	try
            {
        		 VARInstance.updateInstance(workUnitTemplate);
            }
            catch (Throwable t)
                {
            		createAttr(json);
                }
        }
        
       
      
    }

    public static void updateBody(String json) throws IOException {

    	ObjectMapper objectMapper = new ObjectMapper();

        String workUnitTemplate = workUnitTemplate(json);
        String siteTemplate = siteTemplate(json);
        JsonNode jsonNode = objectMapper.readTree(json);

        String instance = VARInstance.getInstance("RWTH");
        JsonNode checkInstance = objectMapper.readTree(instance);
        String instance2 = VARInstance.getInstance(jsonNode.get("assetName").asText());
        JsonNode checkInstance2 = objectMapper.readTree(instance2);
        
        if (checkInstance.get(0) == null)
            VARInstance.createInstance(siteTemplate);
        else
            VARInstance.updateInstance(siteTemplate);
        
        if (checkInstance2.get(0) == null)
        	VARInstance.createInstance(workUnitTemplate);
        else {
        	try
            {	
        		VARInstance.updateInstance(workUnitTemplate);
            }
            catch (Throwable t)
                {
            		createAttr(json);
                }
        }
         
    }

    public static String getSiteIntance () throws IOException {
        String BASE_URL = System.getenv("ENV_SAR_URL");
        if(BASE_URL==null){
            PropertyManager prop = new PropertyManager();
            BASE_URL = prop.getPropValues("base.url");
        }
        final String uri = BASE_URL + "/assets/RWTH/attributes";

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        return result;


    }

    private static List<Attrs> getIndividuals () throws IOException {


        List<Attrs> individuals = new ArrayList<Attrs>();
        ObjectMapper objectMapper = new ObjectMapper();
        for(Attrs cl : VARSparqlQuery.getWorkUnitList()){
        JsonNode jsonNode = objectMapper.readTree(cl.getValue());

            for(JsonNode name : jsonNode.get("results").get("bindings")){
            	Attrs ind = new Attrs();
            	ind.setName(cl.getName());
            	ind.setValue(stringParser(name.get("list").get("value").asText()));
                individuals.add(ind);
            }
        }
        return individuals;


    }

    private static String stringParser(String string) {
        String[] nameparts = string.split("#", 2);
        return nameparts[1];
    }

    private static String findAttributes (String name) throws IOException {
        String BASE_URL = System.getenv("ENV_SAR_URL");
        if(BASE_URL==null){
            PropertyManager prop = new PropertyManager();
            BASE_URL = prop.getPropValues("base.url");
        }
        final String uri = BASE_URL + "/assets/"+name+"/attributes";

        RestTemplate restTemplate = new RestTemplate();

        String result = restTemplate.getForObject(uri, String.class);

        return result;


    }

    public static List<Individual> resourceList () throws IOException {

        List<Individual> resourceList = new ArrayList<Individual>();

        for (Attrs name : getIndividuals() ){
            Individual res = new Individual();
            res.setName(name.getValue());
            res.setClassName(name.getName());
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(findAttributes(name.getValue()));
            List<Attrs> attributes = new ArrayList<Attrs>();

            if(!jsonNode.isNull()) {
                for (JsonNode attrs : jsonNode) {
                    Attrs el = new Attrs();
                    el.setName(attrs.get("normalizedName").asText());
                    el.setValue(attrs.get("propertyValue").asText());
                    attributes.add(el);
                }
            }
            res.setAttr(attributes);
            resourceList.add(res);

        }
        
        return resourceList;
    }

    public static void delete(String name) throws IOException {
        String BASE_URL = System.getenv("ENV_SAR_URL");
        if(BASE_URL==null){
            PropertyManager prop = new PropertyManager();
            BASE_URL = prop.getPropValues("base.url");
        }
        final String uri = BASE_URL + "/assets/"+name;
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(uri);
    }
    
    public static void createAttr(String body) throws IOException {
        String BASE_URL = System.getenv("ENV_SAR_URL");
        if(BASE_URL==null){
            PropertyManager prop = new PropertyManager();
            BASE_URL = prop.getPropValues("base.url");
        }



        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(workUnitTemplate(body));

        final String uri = BASE_URL + "/models/"+jsonNode.get("assetName").asText()+"/attributes/";

        for (JsonNode attr: jsonNode.get("attribute") ) {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(attr.toString(),headers);
            try
            {
            	String answer = restTemplate.postForObject(uri, entity, String.class);
            	System.out.println(answer);
            }
            catch (Throwable t)
                {
            	
                }
            
        }
        
        try
        {
    		 VARInstance.updateInstance(workUnitTemplate(body));
        }
        catch (Throwable t)
            {
        		
            }

    }


}
