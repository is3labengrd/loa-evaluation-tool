package eng.it.loatool.process_segment_list_element;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.subprocess_level.DeleteSubProcessLevelService;

@Service
public class DeleteProcessSegmentListElementService {

    @Transactional
    public Optional<ProcessSegmentListElement> deleteProcessSegmentListElement(Integer id) {
        Optional<ProcessSegmentListElement> maybeElement = generalCRUDService
            .getOne(processSegmentListElementRepository, id);
        maybeElement
            .ifPresent((element) -> {
                deleteSubProcessLevelService.delete(element.getSubProcessLevel1());
                deleteSubProcessLevelService.delete(element.getSubProcessLevel2());
                deleteSubProcessLevelService.delete(element.getSubProcessLevel3());
                deleteSubProcessLevelService.delete(element.getSubProcessLevel4());
                deleteSubProcessLevelService.delete(element.getSubProcessLevel5());
            });
        return generalCRUDService.delete(processSegmentListElementRepository, id);
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ProcessSegmentListElementRepository processSegmentListElementRepository;
    @Autowired private DeleteSubProcessLevelService deleteSubProcessLevelService;

}
