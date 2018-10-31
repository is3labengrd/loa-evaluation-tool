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
import eng.it.loatool.subprocess_level.CreateSubProcessLevelService;
import eng.it.loatool.subprocess_level.GetSubProcessLevelService;
import eng.it.loatool.subprocess_level.GetSubProcessLevelsService;
import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;
import eng.it.loatool.subprocess_level.UpdateSubProcessLevelService;

@Controller
public class SubProcessLevelsApi {

    @GetMapping("/v1/subprocess-levels")
    public ResponseEntity<?> getSubProcessLevels(
		@RequestParam(value="page", defaultValue="-1") int page,
		@RequestParam(value="size", defaultValue="-1") int size
	) {
        return ResponseEntityTransformer.transformOk(
            getSubProcessesService.getSubProcessLevels(page, size)
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

    @DeleteMapping("/v1/subprocess-levels/{subprocessId}")
    public ResponseEntity<?> deleteSubProcessLevel(
        @PathVariable("subprocessId") Integer processId
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.delete(subProcessLevelRepository, processId)
        );
    }

    @Autowired private UpdateSubProcessLevelService updateSubProcessService;
    @Autowired private CreateSubProcessLevelService createSubProcessService;
    @Autowired private GetSubProcessLevelsService getSubProcessesService;
    @Autowired private GetSubProcessLevelService getSubProcessService;
    @Autowired private SubProcessLevelRepository subProcessLevelRepository;
    @Autowired private GeneralCRUDService generalCRUDService;

}
