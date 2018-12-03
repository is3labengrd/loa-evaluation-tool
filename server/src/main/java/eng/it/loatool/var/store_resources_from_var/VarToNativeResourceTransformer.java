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
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("loAPhysical"))),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("loACognitive"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("numberOfOperators"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("annualMaintenanceCost"))),
            zeroIfInvalidFloat(() -> Float.valueOf(propertyValues.get("annualMaintenanceCostPercent"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("installationSurface"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("costPerSurfacePerMonth"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("machinePurchaseValue"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("machineSalesValue"))),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("economicUsefulLife"))),
            zeroIfInvalidFloat(() -> Float.valueOf(propertyValues.get("interestRate"))),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("annualElectricityConsumptionWhileWorking"))),
            zeroIfInvalidInteger(() -> Integer.valueOf(propertyValues.get("annualElectricityConsumptionStandBy"))),
            zeroIfInvalidDouble(() -> Double.valueOf(propertyValues.get("electricityPrice"))),
            true,
            individual.getClassName(),
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
