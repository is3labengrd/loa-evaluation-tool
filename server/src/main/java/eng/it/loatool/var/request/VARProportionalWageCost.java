package eng.it.loatool.var.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class VARProportionalWageCost {

    public static String ProportionalWageCostTemplate (String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        String proportionalWageCostTemplate = "{" +
                "\"assetName\": " + jsonNode.get("assetName") + "," +
                "\"className\":\"ProportionalWageCost\"," +
                "\"domainName\":\"\"," +
                "\"orionConfigId\": \"\"," +
                "\"attribute\":" +
                "[" +
                "{\"name\": \"valueString\",\"value\":" + jsonNode.get("valueString") +",\"type\":\"java.lang.String\"}," +
                "{\"name\": \"unitOfMeasure\",\"value\":" + jsonNode.get("unitOfMeasure") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"propertyID\",\"value\":" + jsonNode.get("propertyID") +",\"type\":\"java.lang.String\"}" +
                "]" +
                "}";
        return proportionalWageCostTemplate;
    }


    public static void createBody(String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        String instance = VARInstance.getInstance(jsonNode.get("assetName").asText());
        JsonNode checkInstance = objectMapper.readTree(instance);

        if (checkInstance.get(0) == null)
            VARInstance.createInstance(ProportionalWageCostTemplate(json));
        else
            VARInstance.updateInstance(ProportionalWageCostTemplate(json));

    }

}
