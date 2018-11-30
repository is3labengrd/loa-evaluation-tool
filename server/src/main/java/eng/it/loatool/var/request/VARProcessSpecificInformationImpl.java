package eng.it.loatool.var.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import eng.it.util.PropertyManager;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

public class VARProcessSpecificInformationImpl {

    public static String ProcessSpecificInformationTemplate (String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        String processSpecificInformationTemplate = "{" +
                "\"assetName\": " + jsonNode.get("assetName") + "," +
                "\"className\":\"ProcessSegment\"," +
                "\"domainName\":\"\"," +
                "\"orionConfigId\": \"\"," +
                "\"attribute\":" +
                "[" +
                "{\"name\": \"NumberOfShiftsPerDay\",\"value\":" + jsonNode.get("NumberOfShiftsPerDay") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"HoursPerShift\",\"value\":" + jsonNode.get("HoursPerShift") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"WorkingDaysPerYear\",\"value\":" + jsonNode.get("WorkingDaysPerYear") +",\"type\":\"java.lang.Integer\"}" +
                "]" +
                "}";
        return processSpecificInformationTemplate;
    }


    public static void createBody(String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        String instance = VARInstance.getInstance(jsonNode.get("assetName").asText());
        JsonNode checkInstance = objectMapper.readTree(instance);
        try
        {
            VARInstance.updateInstance(ProcessSpecificInformationTemplate(json));
        }
        catch (Throwable t)
            {
                createAttr(json);
            }



    }

    private static void createAttr(String body) throws IOException {
        String BASE_URL = System.getenv("ENV_SAR_URL");
        if(BASE_URL==null){
            PropertyManager prop = new PropertyManager();
            BASE_URL = prop.getPropValues("base.url");
        }



        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(ProcessSpecificInformationTemplate(body));

        final String uri = BASE_URL + "/models/"+jsonNode.get("assetName").asText()+"/attributes/";

        for (JsonNode attr: jsonNode.get("attribute") ) {
            RestTemplate restTemplate = new RestTemplate();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            HttpEntity<String> entity = new HttpEntity<String>(attr.toString(),headers);
            String answer = restTemplate.postForObject(uri, entity, String.class);
            System.out.println(answer);
        }

    }

}
