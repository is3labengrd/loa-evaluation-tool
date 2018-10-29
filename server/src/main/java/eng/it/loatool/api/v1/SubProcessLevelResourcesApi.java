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

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.subprocess_level_resource.SubProcessLevelResource;
import eng.it.loatool.subprocess_level_resource.SubProcessLevelResourceRepository;

@Controller
public class SubProcessLevelResourcesApi {

    @GetMapping("/v1/subprocess-level-resources")
    public ResponseEntity<?> getEverySubProcessLevelResource() {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(subProcessLevelResourceRepository)
        );
    }

    @GetMapping("/v1/subprocess-level-resources/{id}")
    public ResponseEntity<?> getSubProcessLevelResource(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(subProcessLevelResourceRepository, id)
        );
    }

    @PostMapping("/v1/subprocess-level-resources")
    public ResponseEntity<?> createSubProcessLevelResource(@RequestBody SubProcessLevelResource body) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.create(subProcessLevelResourceRepository, body)
        );
    }

    @PutMapping("/v1/subprocess-level-resources/{id}")
    public ResponseEntity<?> updateSubProcessLevelResource(
        @PathVariable("id") Integer id,
        @RequestBody SubProcessLevelResource body
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.update(subProcessLevelResourceRepository, id, body)
        );
    }

    @DeleteMapping("/v1/subprocess-level-resources/{id}")
    public ResponseEntity<?> deleteSubProcessLevelResource(
        @PathVariable("id") Integer id
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.delete(subProcessLevelResourceRepository, id)
        );
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private SubProcessLevelResourceRepository subProcessLevelResourceRepository;

}
