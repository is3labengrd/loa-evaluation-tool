package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.subprocess_level.SubProcessLevel;

@Controller
public class WebApi {

    @GetMapping("/proto/process-list")
    public ResponseEntity<?> getProcessList() {
        return ResponseEntity.ok(getProcessListService.getProcessList());
    }

    @GetMapping("/proto/sub-processes")
    public ResponseEntity<?> getSubProcesses() {
        return ResponseEntity.ok(getSubProcessesService.getSubProcesses());
    }

    @GetMapping("/proto/sub-processes/{subprocessId}")
    public ResponseEntity<?> getSubProcess(@PathVariable("subprocessId") Integer processId) {
        return ResponseEntity.ok(getSubProcessService.getSubProcess(processId));
    }

    @PostMapping("/proto/sub-processes")
    public ResponseEntity<Void> createSubProcess(
        @RequestBody SubProcessLevel body
    ) {
        createSubProcessService.createSubProcess(body);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/proto/sub-processes/{subprocessId}")
    public ResponseEntity<Void> updateSubProcess(
        @PathVariable("subprocessId") Integer processId,
        @RequestBody SubProcessLevel body
    ) {
        updateSubProcessService.updateSubProcess(processId, body);
        return ResponseEntity.ok().build();
    }

    @Autowired private UpdateSubProcessService updateSubProcessService;
    @Autowired private CreateSubProcessService createSubProcessService;
    @Autowired private GetSubProcessesService getSubProcessesService;
    @Autowired private GetSubProcessService getSubProcessService;
    @Autowired private GetProcessListService getProcessListService;

}
