package eng.it.loatool.limbo;

import eng.it.loatool.process_specific_info.ProcessesSpecificInformation;
import eng.it.loatool.product_planning.ProductPlanning;
import eng.it.loatool.resource.Resource;
import eng.it.loatool.subscenario.SubScenario;

public class SubScenariosMatrixCalculationProcedure {

    public SubScenarioMatrix executeCalculation() {
        SubScenarioMatrix subScenarioMatrix = new SubScenarioMatrix();
        subScenarioMatrix.loaPhysical = resource.getLoaPhysical();
        subScenarioMatrix.loaCognitive = resource.getLoaCognitive();
        subScenarioMatrix.processTime = subScenario.getProcessTime();
        subScenarioMatrix.numberOfShiftsPerDay = processInformation.getNShiptsDay();
        subScenarioMatrix.hoursPerShift = processInformation.getHoursShift();
        subScenarioMatrix.workingDaysPerYear = processInformation.getWorkingDaysYear();
        subScenarioMatrix.hoursPerYear = (
            subScenarioMatrix.numberOfShiftsPerDay *
            subScenarioMatrix.hoursPerShift *
            subScenarioMatrix.workingDaysPerYear
        );
        subScenarioMatrix.numberOfProducedPiecesInUnitsPerHour = productPlanning.getNProdPiecePerHours();
        subScenarioMatrix.numberOfProducedPiecesinUnitsPerYear = (
            subScenarioMatrix.hoursPerYear *
            subScenarioMatrix.numberOfProducedPiecesInUnitsPerHour
        );
        subScenarioMatrix.proportionalWageCostsPerHour = 69; throw new RuntimeException("Lul, not implemented");
        subScenarioMatrix.numberOfOperatorsPerMachine = resource.getLcNOperMachine();
        subScenarioMatrix.rateOfParticipationPerHour = (
            subScenarioMatrix.numberOfProducedPiecesInUnitsPerHour / 3600 *
            subScenarioMatrix.processTime
        );
        subScenarioMatrix.labourCost = (
            subScenarioMatrix.proportionalWageCostsPerHour *
            subScenarioMatrix.numberOfOperatorsPerMachine *
            subScenarioMatrix.rateOfParticipationPerHour /
            subScenarioMatrix.workingDaysPerYear
        );
        subScenarioMatrix.electricityConsumption = resource.getEcAEleConsumFun();
        subScenarioMatrix.electricityConsumptionOnStandby = resource.getEcAEleConsumSb();
        subScenarioMatrix.electricityPrice = resource.getEcElePrice();
        subScenarioMatrix.energyCost = (
            (
                subScenarioMatrix.electricityConsumption *
                subScenarioMatrix.electricityPrice *
                subScenarioMatrix.processTime
            ) / 3600 + (
                (
                    3600 -
                    subScenarioMatrix.processTime *
                    subScenarioMatrix.numberOfProducedPiecesInUnitsPerHour
                ) / (
                    subScenarioMatrix.numberOfProducedPiecesInUnitsPerHour * 3600
                )
            ) *
            subScenarioMatrix.electricityConsumptionOnStandby *
            subScenarioMatrix.electricityPrice
        );
        subScenarioMatrix.annualMaintenanceCostsDirectEntry = resource.getMcAMaintCosts();
        subScenarioMatrix.annualMaintenanceCostsEstimate = resource.getMcAMaintCostsPerc();
        subScenarioMatrix.machinePurchaseValue =  resource.getIdMacPurhValue();
        subScenarioMatrix.machineSalesValue = resource.getIdMacSalesValue();
        subScenarioMatrix.maintenanceCosts = (
            /* a float cannot == null as the documentation states, and getMcAMaintCosts isn't nullable to begin with */
            (subScenarioMatrix.annualMaintenanceCostsDirectEntry == 0)?
                subScenarioMatrix.annualMaintenanceCostsEstimate * subScenarioMatrix.machinePurchaseValue
            :
                subScenarioMatrix.annualMaintenanceCostsDirectEntry
        );
        subScenarioMatrix.installationSurface = resource.getRcInstSurface();
        subScenarioMatrix.costsOfSquareMetrePerMonth = resource.getRcCostsMMonth();
//        subScenarioMatrix.annualSpaceCosts = (
//            subScenarioMatrix.costsOfSquareMetrePerMonth * 12 *
//            subScenarioMatrix.installationSurface
//        );
        throw new RuntimeException("installationSurface is a string, lol. Will fix.");
        subScenarioMatrix.economicUsefulLife = resource.getIdEcoUsefullLife();
        subScenarioMatrix.imputedDepreciation = (
            (subScenarioMatrix.economicUsefulLife != 0)?
                (
                    subScenarioMatrix.machinePurchaseValue -
                    subScenarioMatrix.machineSalesValue
                ) /
                subScenarioMatrix.economicUsefulLife
            :
                0
        );
        subScenarioMatrix.interestRate = resource.getIcInterRate();
        subScenarioMatrix.accruedInterestCosts = (
            (
                subScenarioMatrix.machinePurchaseValue +
                subScenarioMatrix.machineSalesValue
            ) / 2
            * subScenarioMatrix.interestRate
        );
        subScenarioMatrix.variableCostsTotal = (
            subScenarioMatrix.labourCost +
            subScenarioMatrix.energyCost
        );
        subScenarioMatrix.fixedCostsTotal = (
            (
                subScenarioMatrix.maintenanceCosts +
                subScenarioMatrix.annualSpaceCosts +
                subScenarioMatrix.imputedDepreciation +
                subScenarioMatrix.accruedInterestCosts
            ) / subScenarioMatrix.numberOfProducedPiecesinUnitsPerYear
        );
        subScenarioMatrix.assemblyCostsPerPiece = (
            subScenarioMatrix.variableCostsTotal +
            subScenarioMatrix.fixedCostsTotal
        );
        subScenarioMatrix.assemblyCostsTotal = (
            subScenarioMatrix.assemblyCostsPerPiece *
            subScenarioMatrix.numberOfProducedPiecesinUnitsPerYear
        );
        return subScenarioMatrix;
    }

    public SubScenariosMatrixCalculationProcedure(
        ProductPlanning productPlanning,
        ProcessesSpecificInformation processInformation,
        SubScenario subScenario,
        Resource resource
    ) {
        if (
            productPlanning == null ||
            processInformation == null ||
            subScenario == null ||
            resource == null
        ) {
            throw new IllegalArgumentException("None of the arguments are allowed to be null");
        }
        this.productPlanning = productPlanning;
        this.processInformation = processInformation;
        this.subScenario = subScenario;
        this.resource = resource;
    }

    private ProductPlanning productPlanning;
    private ProcessesSpecificInformation processInformation;
    private SubScenario subScenario;
    private Resource resource;

}
