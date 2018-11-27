package eng.it.loatool.var.store_resources_from_var;

import java.util.HashMap;

import eng.it.loatool.resource.Resource;
import eng.it.loatool.var.bean.Individual;

public class VarToNativeResourceTransformer {

    public Resource transform(Individual individual) {
        individual.getAttr().forEach((attr) -> {
            propertyValues.put(attr.getName(), attr.getValue());
        });
        return new Resource(
            individual.getName(),
            Integer.valueOf(propertyValues.get("loaPhysical")),
            Integer.valueOf(propertyValues.get("loaCognitive")),
            Double.valueOf(propertyValues.get("lcNOperMachine")),
            Double.valueOf(propertyValues.get("mcAMaintCosts")),
            Float.valueOf(propertyValues.get("mcAMaintCostsPerc")),
            Double.valueOf(propertyValues.get("rcInstSurface")),
            Double.valueOf(propertyValues.get("rcCostsMMonth")),
            Double.valueOf(propertyValues.get("idMacPurhValue")),
            Double.valueOf(propertyValues.get("idMacSalesValue")),
            Integer.valueOf(propertyValues.get("idEcoUsefullLife")),
            Float.valueOf(propertyValues.get("icInterRate")),
            Integer.valueOf(propertyValues.get("ecAEleConsumFun")),
            Integer.valueOf(propertyValues.get("ecAEleConsumSb")),
            Double.valueOf(propertyValues.get("ecElePrice")),
            null,
            null
        );
    }

    private HashMap<String, String> propertyValues = new HashMap<>(20);

}
