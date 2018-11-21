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

import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.process_segment.CreateProcessSegmentService;
import eng.it.loatool.process_segment.DeleteProcessSegmentService;
import eng.it.loatool.process_segment.GetProcessSegmentService;
import eng.it.loatool.process_segment.GetProcessSegmentsService;
import eng.it.loatool.process_segment.ProcessSegment;
import eng.it.loatool.process_segment.UpdateProcessSegmentService;

@Controller
public class ProcessSegmentsApi {

    @GetMapping("/v1/process-segments")
    public ResponseEntity<?> getProcesses() {
        return ResponseEntityTransformer.transformOk(
            getProcessSegmentsService.getProcessSegments()
        );
    }

    @GetMapping("/v1/process-segments/{processId}")
    public ResponseEntity<?> getProcess(@PathVariable("processId") Integer processId) {
        return ResponseEntityTransformer.transform(
            getProcessSegmentService.getProcessSegment(processId)
        );
    }

    @PostMapping("/v1/process-segments")
    public ResponseEntity<?> createProcess(@RequestBody ProcessSegment body) {
        return ResponseEntityTransformer.transform(
            createProcessSegmentService.createProcessSegment(body)
        );
    }

    @PutMapping("/v1/process-segments/{processId}")
    public ResponseEntity<?> updateProcess(
        @PathVariable("processId") Integer processId,
        @RequestBody ProcessSegment body
    ) {
        return ResponseEntityTransformer.transform(
            updateProcessSegmentService.updateProcessSegment(processId, body)
        );
    }

    @DeleteMapping("/v1/process-segments/{processId}")
    public ResponseEntity<?> deleteProcess(
        @PathVariable("processId") Integer processId
    ) {
        return ResponseEntityTransformer.transform(
            deleteProcessSegmentService.deletebyId(processId)
        );
    }

    @Autowired private DeleteProcessSegmentService deleteProcessSegmentService;
    @Autowired private UpdateProcessSegmentService updateProcessSegmentService;
    @Autowired private CreateProcessSegmentService createProcessSegmentService;
    @Autowired private GetProcessSegmentsService getProcessSegmentsService;
    @Autowired private GetProcessSegmentService getProcessSegmentService;

}
