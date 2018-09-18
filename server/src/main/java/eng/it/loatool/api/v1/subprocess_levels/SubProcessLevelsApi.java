package eng.it.loatool.api.v1.subprocess_levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.subprocess_level.SubProcessLevel;

@Controller
public class SubProcessLevelsApi {

    @GetMapping("/v1/subprocess-levels")
    public ResponseEntity<?> getSubProcessLevels() {
        return ResponseEntityTransformer.transformOk(
            getSubProcessesService.getSubProcessLevels()
        );
    }

    @GetMapping("/v1/subprocess-levels/{subprocessId}")
    public ResponseEntity<?> getSubProcessLevel(@PathVariable("subprocessId") Integer processId) {
        return ResponseEntityTransformer.transform(
            getSubProcessService.getSubProcessLevel(processId)
        );
    }

    @PostMapping("/v1/subprocess-levels")
    public ResponseEntity<?> createSubProcessLevel(
        @RequestBody SubProcessLevel body
    ) {
        return ResponseEntityTransformer.transform(
            createSubProcessService.createSubProcessLevel(body)
        );
    }

    @PutMapping("/v1/subprocess-levels/{subprocessId}")
    public ResponseEntity<?> updateSubProcessLevel(
        @PathVariable("subprocessId") Integer processId,
        @RequestBody SubProcessLevel body
    ) {
        return ResponseEntityTransformer.transform(
            updateSubProcessService.updateSubProcessLevel(processId, body)
        );
    }

    @Autowired private UpdateSubProcessLevelService updateSubProcessService;
    @Autowired private CreateSubProcessLevelService createSubProcessService;
    @Autowired private GetSubProcessLevelsService getSubProcessesService;
    @Autowired private GetSubProcessLevelService getSubProcessService;

}
