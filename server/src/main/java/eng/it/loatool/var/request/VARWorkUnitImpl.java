package eng.it.loatool.var.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;


public class VARWorkUnitImpl {

    public static String workUnitTemplate (String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        String workUnitTemplate = "{" +
                "\"assetName\": " + jsonNode.get("assetName") + "," +
                "\"className\":\"WorkUnit\"," +
                "\"domainName\":\"\"," +
                "\"orionConfigId\": \"\"," +
                "\"attribute\":" +
                "[" +
                "{\"name\": \"loAPhysical\",\"value\":" + jsonNode.get("loAPhysical") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"loACognitive\",\"value\":"+ jsonNode.get("loACognitive") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"numberOfOperators\",\"value\":"+ jsonNode.get("numberOfOperators") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"annualMaintenanceCost\",\"value\": "+ jsonNode.get("annualMaintenanceCost") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"installationSurface\",\"value\": "+ jsonNode.get("installationSurface") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"costPerSurfacePerMonth\",\"value\":"+ jsonNode.get("costPerSurfacePerMonth") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"machinePurchaseValue\",\"value\": "+ jsonNode.get("machinePurchaseValue") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"machineSalesValue\",\"value\":"+ jsonNode.get("machineSalesValue") +",\"type\":\"java.lang.Double\"}," +
                "{\"name\": \"economicUsefulLife\",\"value\":"+ jsonNode.get("economicUsefulLife") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"annualElectricityConsumptionWhileWorking\",\"value\":"+ jsonNode.get("annualElectricityConsumptionWhileWorking") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"annualElectricityConsumptionStandBy\",\"value\":"+ jsonNode.get("annualElectricityConsumptionStandBy") +",\"type\":\"java.lang.Integer\"}," +
                "{\"name\": \"equipmentId\",\"value\":"+ jsonNode.get("equipmentId") +",\"type\":\"java.lang.String\"}," +
                "{\"name\": \"equipmentLevel\",\"value\":\"work unit\",\"type\":\"java.lang.String\"}" +
                "]" +
                "}";
        return workUnitTemplate;
    }

    public static String siteTemplate (String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        JsonNode jsonNode = objectMapper.readTree(json);

        String siteTemplate = "{" +
                "\"assetName\": \"RWTH\"," +
                "\"className\":\"Site\"," +
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

        String instance = VARInstance.getInstance("RWTH");
        JsonNode checkInstance = objectMapper.readTree(instance);

        VARInstance.createInstance(workUnitTemplate);
        if (checkInstance.get(0) == null)
            VARInstance.createInstance(siteTemplate);
        else
            VARInstance.updateInstance(siteTemplate);



    }

    public static void updateBody(String json) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        String workUnitTemplate = workUnitTemplate(json);
        String siteTemplate = siteTemplate(json);

        String instance = VARInstance.getInstance("RWTH");
        JsonNode checkInstance = objectMapper.readTree(instance);

        VARInstance.updateInstance(workUnitTemplate);
        if (checkInstance.get(0) == null)
            VARInstance.createInstance(siteTemplate);
        else
            VARInstance.updateInstance(siteTemplate);



    }



}
