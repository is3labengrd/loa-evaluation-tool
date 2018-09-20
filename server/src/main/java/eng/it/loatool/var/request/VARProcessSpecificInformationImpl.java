package eng.it.loatool.var.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

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

        if (checkInstance.get(0) == null)
            VARInstance.createInstance(ProcessSpecificInformationTemplate(json));
        else
            VARInstance.updateInstance(ProcessSpecificInformationTemplate(json));



    }

}
