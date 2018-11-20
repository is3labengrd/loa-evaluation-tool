package eng.it.loatool.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.process_segment_list_element.DeleteProcessSegmentListElementService;
import eng.it.loatool.process_segment_list_element.PersistProcessSegmentListElementService;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElement;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElementPaginationService;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElementRepository;

@Controller
public class ProcessSegmentListElementsApi {

    @GetMapping("/v1/process-segment-list-elements")
    public ResponseEntity<?> getProcessSegmentList(
        @RequestParam(value="page", defaultValue="-1") int page,
        @RequestParam(value="size", defaultValue="-1") int size
    ) {
        return ResponseEntityTransformer.transformOk(
            processSegmentListElementPaginationService.getAll(page, size)
        );
    }

    @GetMapping("/v1/process-segment-list-elements-like/{namePiece}")
    public ResponseEntity<?> getProcessSegmentListElementLike(
        @RequestParam(value="page", defaultValue="-1") int page,
        @RequestParam(value="size", defaultValue="-1") int size,
        @PathVariable("namePiece") String namePiece
    ) {
        return ResponseEntityTransformer.transformOk(
            processSegmentListElementPaginationService.getAllLike(namePiece, page, size)
        );
    }

    @GetMapping("/v1/process-segment-list-elements/{id}")
    public ResponseEntity<?> getProcessSegmentListElement(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(processSegmentListElementRepository, id)
        );
    }

    @PostMapping("/v1/process-segment-list-elements")
    public ResponseEntity<?> createProcessSegmentListElement(@RequestBody ProcessSegmentListElement body) {
        return ResponseEntityTransformer.transform(
            persistProcessSegmentListElementService.createProcessSegmentListElement(body)
        );
    }

    @PutMapping("/v1/process-segment-list-elements/{id}")
    public ResponseEntity<?> updateProcessSegmentListElement(
        @PathVariable("id") Integer id,
        @RequestBody ProcessSegmentListElement body
    ) {
        return ResponseEntityTransformer.transform(
            persistProcessSegmentListElementService.updateProcessSegmentListElement(id, body)
        );
    }

    @DeleteMapping("/v1/process-segment-list-elements/{id}")
    public ResponseEntity<?> deleteProcessSegmentListElement(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            deleteProcessSegmentListElementService.deleteProcessSegmentListElement(id)
        );
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private PersistProcessSegmentListElementService persistProcessSegmentListElementService;
    @Autowired private ProcessSegmentListElementPaginationService processSegmentListElementPaginationService;
    @Autowired private DeleteProcessSegmentListElementService deleteProcessSegmentListElementService;
    @Autowired private ProcessSegmentListElementRepository processSegmentListElementRepository;

}
