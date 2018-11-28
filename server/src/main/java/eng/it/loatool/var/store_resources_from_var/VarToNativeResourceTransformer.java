package eng.it.loatool.var.store_resources_from_var;

import java.util.HashMap;
import java.util.function.Supplier;

import eng.it.loatool.resource.Resource;
import eng.it.loatool.var.bean.Individual;

public class VarToNativeResourceTransformer {

    public Resource transform(Individual individual) {
        individual.getAttr().forEach((attr) -> {
            propertyValues.put(attr.getName(), attr.getValue());
        });
        return new Resource(
            individual.getName(),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("loaPhysical"))),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("loaCognitive"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("lcNOperMachine"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("mcAMaintCosts"))),
            zeroIfInvalidFloat(() -> Float.valueOf(propertyValues.get("mcAMaintCostsPerc"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("rcInstSurface"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("rcCostsMMonth"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("idMacPurhValue"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("idMacSalesValue"))),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("idEcoUsefullLife"))),
            zeroIfInvalidFloat(() -> Float.valueOf(propertyValues.get("icInterRate"))),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("ecAEleConsumFun"))),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("ecAEleConsumSb"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("ecElePrice"))),
            null,
            null
        );
    }



    private Integer zeroIfInvalidInteger(Supplier<Integer> supplier) {
        Integer result;
        try {
            result = supplier.get();
            return result == null? 0 : result;
        } catch (Exception e) {
            return 0;
        }
    }

    private Float zeroIfInvalidFloat(Supplier<Float> supplier) {
        Float result;
        try {
            result = supplier.get();
            return result == null? 0f : result;
        } catch (Exception e) {
            return 0f;
        }
    }

    private Double zeroIfInvalidDouble(Supplier<Double> supplier) {
        Double result;
        try {
            result = supplier.get();
            return result == null? 0d : result;
        } catch (Exception e) {
            return 0d;
        }
    }

    private HashMap<String, String> propertyValues = new HashMap<>(20);

}
