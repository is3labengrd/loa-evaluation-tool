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
import eng.it.loatool.process_loa_info.CreateProcessLOAInformationService;
import eng.it.loatool.process_loa_info.GetProcessLOAInformationService;
import eng.it.loatool.process_loa_info.ProcessLOAInformation;
import eng.it.loatool.process_loa_info.UpdateProcessLOAInformationService;

@Controller
public class ProcessLOAInformationApi {

    @GetMapping("/v1/process-loa-info")
    public ResponseEntity<?> getAllInfo() {
        return ResponseEntityTransformer.transformOk(
            getProcessLOAInformationService.getAllInformation()
        );
    }

    @GetMapping("/v1/process-loa-info/{processId}")
    public ResponseEntity<?> getInfo(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            getProcessLOAInformationService.getInformation(id)
        );
    }

    @GetMapping("/v1/process-loa-info-by-subprocess-id/{subprocessId}")
    public ResponseEntity<?> getInfoBySubprocessId(@PathVariable("subprocessId") Integer subprocessId) {
        return ResponseEntityTransformer.transform(
            getProcessLOAInformationService.getPhysicalProcessLOAInformationBySubprocessId(subprocessId)
        );
    }

    @GetMapping("/v1/physical-process-loa-info-by-subprocess-id/{subprocessId}")
    public ResponseEntity<?> getPhysicalInfoBySubprocessId(@PathVariable("subprocessId") Integer subprocessId) {
        return ResponseEntityTransformer.transform(
            getProcessLOAInformationService.getPhysicalProcessLOAInformationBySubprocessId(subprocessId)
        );
    }

    @GetMapping("/v1/cognitive-process-loa-info-by-subprocess-id/{subprocessId}")
    public ResponseEntity<?> getCognitiveInfoBySubprocessId(@PathVariable("subprocessId") Integer subprocessId) {
        return ResponseEntityTransformer.transform(
            getProcessLOAInformationService.getCognitiveProcessLOAInformationBySubprocessId(subprocessId)
        );
    }

    @PostMapping("/v1/process-loa-info")
    public ResponseEntity<?> createInfo(@RequestBody ProcessLOAInformation body) {
        return ResponseEntityTransformer.transform(
            createProcessLOAInformationService.createInformation(body)
        );
    }

    @PutMapping("/v1/process-loa-info/{id}")
    public ResponseEntity<?> updateInfo(
        @PathVariable("id") Integer id,
        @RequestBody ProcessLOAInformation body
    ) {
        return ResponseEntityTransformer.transform(
            updateProcessLOAInformationService.updateInformation(id, body)
        );
    }

    @Autowired private UpdateProcessLOAInformationService updateProcessLOAInformationService;
    @Autowired private CreateProcessLOAInformationService createProcessLOAInformationService;
    @Autowired private GetProcessLOAInformationService getProcessLOAInformationService;

}
