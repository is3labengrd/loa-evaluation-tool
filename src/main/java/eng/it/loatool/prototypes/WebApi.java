package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.entities.TbAceProSeq;
import eng.it.loatool.entities.TbAceSubProLev;

@Controller
public class WebApi {

    @GetMapping("/proto/process-list")
    public ResponseEntity<ProcessList> getProcessList() {
        return ResponseEntity.ok(ProcessList.getExample());
    }

    @GetMapping("/proto/processes")
    public ResponseEntity<?> getProcesses() {
        return ResponseEntity.ok(getProcessesService.getProcesses());
    }

    @GetMapping("/proto/processes/{processId}")
    public ResponseEntity<?> getProcess(@PathVariable("processId") Integer processId) {
        return ResponseEntity.ok(getProcessService.getProcess(processId));
    }

    @PostMapping("/proto/processes")
    public ResponseEntity<Void> createProcess(@RequestBody TbAceProSeq body) {
        createProcessService.createProcess(body);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/proto/processes/{processId}")
    public ResponseEntity<Void> updateProcess(
        @PathVariable("processId") Integer processId,
        @RequestBody TbAceProSeq body
    ) {
        updateProcessService.updateProcess(processId, body);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/proto/sub-processes/{subprocessId}")
    public ResponseEntity<Void> getSubProcess(@PathVariable("processId") String processId) {
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/proto/sub-processes")
    public ResponseEntity<Void> createSubProcess(
        @PathVariable("processId") String processId, @RequestBody TbAceSubProLev body
    ) {
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/proto/sub-processes/{subprocessId}")
    public ResponseEntity<Void> updateSubProcess(
        @PathVariable("processId") String processId, @RequestBody TbAceSubProLev body
    ) {
        return ResponseEntity.notFound().build();
    }

    @Autowired private UpdateProcessService updateProcessService;
    @Autowired private CreateProcessService createProcessService;
    @Autowired private GetProcessesService getProcessesService;
    @Autowired private GetProcessService getProcessService;

}
