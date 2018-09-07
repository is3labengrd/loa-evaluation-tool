package eng.it.loatool.api.v1.process_sequences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.process_sequence.ProcessSequence;
import eng.it.util.ResponseEntityTransformer;

@Controller
public class ProcessSequencesApi {

    @GetMapping("/v1/process-sequences")
    public ResponseEntity<?> getProcesses() {
        return ResponseEntityTransformer.transformOk(
            getProcessesService.getProcesses()
        );
    }

    @GetMapping("/v1/process-sequences/{processId}")
    public ResponseEntity<?> getProcess(@PathVariable("processId") Integer processId) {
        return ResponseEntityTransformer.transform(
            getProcessService.getProcess(processId)
        );
    }

    @PostMapping("/v1/process-sequences")
    public ResponseEntity<?> createProcess(@RequestBody ProcessSequence body) {
        return ResponseEntityTransformer.transform(
            createProcessService.createProcess(body)
        );
    }

    @PutMapping("/v1/process-sequences/{processId}")
    public ResponseEntity<?> updateProcess(
        @PathVariable("processId") Integer processId,
        @RequestBody ProcessSequence body
    ) {
        return ResponseEntityTransformer.transform(
            updateProcessService.updateProcess(processId, body)
        );
    }

    @Autowired private UpdateProcessSequenceService updateProcessService;
    @Autowired private CreateProcessSequenceService createProcessService;
    @Autowired private GetProcessSequencesService getProcessesService;
    @Autowired private GetProcessSequenceService getProcessService;

}
