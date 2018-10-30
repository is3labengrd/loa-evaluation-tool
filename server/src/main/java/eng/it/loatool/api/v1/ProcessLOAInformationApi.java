package eng.it.loatool.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.api.v1.process_loa_info.CreateProcessLOAInformationService;
import eng.it.loatool.api.v1.process_loa_info.GetProcessLOAInformationService;
import eng.it.loatool.api.v1.process_loa_info.UpdateProcessLOAInformationService;
import eng.it.loatool.process_loa_info.ProcessLOAInformation;

@Controller
public class ProcessLOAInformationApi {

    @GetMapping("/v1/process-loa-info")
    public ResponseEntity<?> getProcesses() {
        return ResponseEntityTransformer.transformOk(
            getProcessLOAInformationService.getAllInformation()
        );
    }

    @GetMapping("/v1/process-loa-info/{processId}")
    public ResponseEntity<?> getProcess(@PathVariable("processId") Integer processId) {
        return ResponseEntityTransformer.transform(
            getProcessLOAInformationService.getInformation(processId)
        );
    }

    @GetMapping("/v1/process-loa-info-by-subprocess-id/{subprocessId}")
    public ResponseEntity<?> getProcessBySubprocessId(@PathVariable("subprocessId") Integer subprocessId) {
        return ResponseEntityTransformer.transform(
            getProcessLOAInformationService.getInformationBySubprocessId(subprocessId)
        );
    }

    @PostMapping("/v1/process-loa-info")
    public ResponseEntity<?> createProcess(@RequestBody ProcessLOAInformation body) {
        return ResponseEntityTransformer.transform(
            createProcessLOAInformationService.createInformation(body)
        );
    }

    @PutMapping("/v1/process-loa-info/{processId}")
    public ResponseEntity<?> updateProcess(
        @PathVariable("processId") Integer processId,
        @RequestBody ProcessLOAInformation body
    ) {
        return ResponseEntityTransformer.transform(
            updateProcessLOAInformationService.updateInformation(processId, body)
        );
    }

    @Autowired private UpdateProcessLOAInformationService updateProcessLOAInformationService;
    @Autowired private CreateProcessLOAInformationService createProcessLOAInformationService;
    @Autowired private GetProcessLOAInformationService getProcessLOAInformationService;

}
