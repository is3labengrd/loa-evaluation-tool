package eng.it.loatool.prototypes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import entities.TbAceProSeq;
import entities.TbAceSubProLev;

@Controller
public class WebApi {

    @GetMapping("/proto/process-list")
    public ResponseEntity<ProcessList> getProcessList() {
        return ResponseEntity.ok(ProcessList.getExample());
    }

    @GetMapping("/proto/processes/{processId}")
    public ResponseEntity<Void> getProcess(@PathVariable("processId") String processId) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/proto/processes")
    public ResponseEntity<Void> createProcess(
        @PathVariable("processId") String processId,
        @RequestBody TbAceProSeq body
    ) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/proto/processes/{processId}")
    public ResponseEntity<Void> updateProcess(
        @PathVariable("processId") String processId,
        @RequestBody TbAceProSeq body
    ) {
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/proto/sub-processes/{subprocessId}")
    public ResponseEntity<Void> getSubProcess(@PathVariable("processId") String processId) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/proto/sub-processes")
    public ResponseEntity<Void> createSubProcess(
        @PathVariable("processId") String processId,
        @RequestBody TbAceSubProLev body
    ) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/proto/sub-processes/{subprocessId}")
    public ResponseEntity<Void> updateSubProcess(
        @PathVariable("processId") String processId,
        @RequestBody TbAceSubProLev body
    ) {
        return ResponseEntity.notFound().build();
    }

}
