package eng.it.loatool.var.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

public class VARProductDefinitionImpl {

    public static String productDefinitionTemplate (String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        String productDefinitionTemplate = "{" +
                "\"assetName\": " + jsonNode.get("assetName") + "," +
                "\"className\":\"ProductDefinition\"," +
                "\"domainName\":\"\"," +
                "\"orionConfigId\": \"\"," +
                "\"attribute\":" +
                "[" +
                "{\"name\": \"productionVolume\",\"value\":" + jsonNode.get("productionVolume") +",\"type\":\"java.lang.Integer\"}" +
                "]" +
                "}";
        return productDefinitionTemplate;
    }


    public static void createBody(String json) throws IOException {


        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(json);

        String instance = VARInstance.getInstance(jsonNode.get("assetName").asText());
        JsonNode checkInstance = objectMapper.readTree(instance);

        if (checkInstance.get(0) == null)
            VARInstance.createInstance(productDefinitionTemplate(json));
        else
            VARInstance.updateInstance(productDefinitionTemplate(json));

    }
}
