package eng.it.loatool.process_segment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.minimal_satisfaction.MinimalSatisfactionRepository;
import eng.it.loatool.process_segment_list_element.DeleteProcessSegmentListElementService;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElement;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElementRepository;
import eng.it.loatool.product_planning.GetProductPlanningByProcessSegmentId;
import eng.it.loatool.product_planning.ProductPlanningRepository;
import eng.it.loatool.scenario.ScenarioRepository;

@Service
public class DeleteProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> deletebyId(Integer id) {
        Optional<ProcessSegment> maybeProcessSegment = generalCRUDService
            .getOne(processSegmentRepository, id);
        maybeProcessSegment
            .ifPresent((processSegment) -> {
                getProductPlanningByProcessSegmentId
                    .getOne(id)
                    .ifPresent((productPlanning) -> {
                        productPlanningRepository.delete(productPlanning);
                    });
                scenarioRepository
                    .getScenariosbyProcessSegmentId(id)
                    .forEach((scenario) -> {
                        scenarioRepository.delete(scenario);
                    });
                Iterable<ProcessSegmentListElement> elements = processSegmentListElementRepository
                    .getProcessSegmentListElementsByProcessId(id);
                for (ProcessSegmentListElement element :elements) {
                    deleteProcessSegmentListElementService
                        .deleteProcessSegmentListElement(element.getPkTbId());
                }
                processSegmentRepository.delete(processSegment);
                minimalSatisfactionRepository
                    .getByProcessId(id)
                    .ifPresent((minSat) -> {
                        minimalSatisfactionRepository.delete(minSat);
                    });
                processSegment.setSubprocessLevels(null);
            });
        return maybeProcessSegment;
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ProcessSegmentRepository processSegmentRepository;
    @Autowired private DeleteProcessSegmentListElementService deleteProcessSegmentListElementService;
    @Autowired private ProcessSegmentListElementRepository processSegmentListElementRepository;
    @Autowired private ScenarioRepository scenarioRepository;
    @Autowired private GetProductPlanningByProcessSegmentId getProductPlanningByProcessSegmentId;
    @Autowired private ProductPlanningRepository productPlanningRepository;
    @Autowired private MinimalSatisfactionRepository minimalSatisfactionRepository;

}
