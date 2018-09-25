package eng.it.loatool.limbo;

import eng.it.loatool.process_specific_info.ProcessesSpecificInformation;
import eng.it.loatool.product_planning.ProductPlanning;
import eng.it.loatool.resource.Resource;
import eng.it.loatool.subscenario.SubScenario;

public class SubScenariosMatrixCalculationProcedure {

    /* Type should be determined while implementing this method */
    public void executeCalculation() {
        // TODO: Implement calculation
    }

    public SubScenariosMatrixCalculationProcedure(
        ProductPlanning productPlanning,
        ProcessesSpecificInformation processInformation,
        SubScenario subScenario,
        Resource resource
    ) {
        super();
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
