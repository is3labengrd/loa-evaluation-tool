package eng.it.loatool.process_segment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.process_segment_list_element.DeleteProcessSegmentListElementService;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElement;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElementRepository;

@Service
public class DeleteProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> deletebyId(Integer id) {
        Optional<ProcessSegment> maybeProcessSegment = generalCRUDService
            .getOne(processSegmentRepository, id);
        Iterable<ProcessSegmentListElement> elements = processSegmentListElementRepository
            .getProcessSegmentListElementsByProcessId(id);
        elements
            .forEach((element) -> {
                deleteProcessSegmentListElementService
                    .deleteProcessSegmentListElement(element.getPkTbId());
            });
        Optional<ProcessSegment> result = generalCRUDService.delete(processSegmentRepository, id);
        result
            .ifPresent((process) -> {
                process.setSubprocessLevels(null);
            });
        return result;
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ProcessSegmentRepository processSegmentRepository;
    @Autowired private DeleteProcessSegmentListElementService deleteProcessSegmentListElementService;
    @Autowired private ProcessSegmentListElementRepository processSegmentListElementRepository;

}
