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
import eng.it.loatool.subprocess_level_resource.DeleteSubProcessLevelResourceService;
import eng.it.loatool.subprocess_level_resource.GetSubProcessLevelResourcebySubProcessIdService;
import eng.it.loatool.subprocess_level_resource.PersistSubProcessLevelResourceService;
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

    @GetMapping("/v1/subprocess-level-resources-by-subprocess-id/{id}")
    public ResponseEntity<?> getSubProcessLevelResourceBySubProcessId(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transformOk(
            getSubProcessLevelResourcebySubProcessIdService.getBySubProcessId(id)
        );
    }

    @PostMapping("/v1/subprocess-level-resources")
    public ResponseEntity<?> createSubProcessLevelResource(@RequestBody SubProcessLevelResource body) {
        return ResponseEntityTransformer.transform(
            persistSubProcessLevelResourceService.create(body)
        );
    }

    @PutMapping("/v1/subprocess-level-resources/{id}")
    public ResponseEntity<?> updateSubProcessLevelResource(
        @PathVariable("id") Integer id,
        @RequestBody SubProcessLevelResource body
    ) {
        return ResponseEntityTransformer.transform(
            persistSubProcessLevelResourceService.update(id, body)
        );
    }

    @DeleteMapping("/v1/subprocess-level-resources/{id}")
    public ResponseEntity<?> deleteSubProcessLevelResource(
        @PathVariable("id") Integer id
    ) {
        return ResponseEntityTransformer.transform(
            deleteSubProcessLevelResourceService.delete(id)
        );
    }

    @Autowired private GetSubProcessLevelResourcebySubProcessIdService getSubProcessLevelResourcebySubProcessIdService;
    @Autowired private PersistSubProcessLevelResourceService persistSubProcessLevelResourceService;
    @Autowired private DeleteSubProcessLevelResourceService deleteSubProcessLevelResourceService;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private SubProcessLevelResourceRepository subProcessLevelResourceRepository;

}
