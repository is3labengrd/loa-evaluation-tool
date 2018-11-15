package eng.it.loatool.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.resource.GetResourceListItemService;
import eng.it.loatool.resource.Resource;
import eng.it.loatool.resource.ResourceRepository;
import eng.it.loatool.resource.UpdateResourceService;

@Controller
public class ResourcesApi {

    @GetMapping("/v1/resources")
    public ResponseEntity<?> getEveryResource(
        @RequestParam(value="page", defaultValue="-1") int page,
        @RequestParam(value="size", defaultValue="-1") int size
    ) {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(resourceRepository, page, size)
        );
    }

    @GetMapping("/v1/resource-items-by-subprocess-id/{id}")
    public ResponseEntity<?> getResourceItemsByResourceId(
        @PathVariable("id") Integer id,
        @RequestParam(value="page", defaultValue="-1") int page,
        @RequestParam(value="size", defaultValue="-1") int size
    ) {
        return ResponseEntityTransformer.transformOk(
            getResourceListItemService.getAllBySubprocessId(id, page, size)
        );
    }

    @GetMapping("/v1/resource-items-by-subprocess-id/{id}/like/{searchTerm}")
    public ResponseEntity<?> getResourceItemsByResourceId(
        @PathVariable("id") Integer id,
        @PathVariable("searchTerm") String searchTerm,
        @RequestParam(value="page", defaultValue="-1") int page,
        @RequestParam(value="size", defaultValue="-1") int size
    ) {
        return ResponseEntityTransformer.transformOk(
            getResourceListItemService
                .getAllBySubprocessIdAndResourceSearchTerm(id, searchTerm, page, size)
        );
    }

    @GetMapping("/v1/resources/{id}")
    public ResponseEntity<?> getResource(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(resourceRepository, id)
        );
    }

    @PostMapping("/v1/resources")
    public ResponseEntity<?> createResource(@RequestBody Resource body) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.create(resourceRepository, body)
        );
    }

    @PutMapping("/v1/resources/{id}")
    public ResponseEntity<?> updateResource(
        @PathVariable("id") Integer id,
        @RequestBody Resource body
    ) {
        return ResponseEntityTransformer.transform(
            updateResourceService.update(id, body)
        );
    }

    @Autowired private UpdateResourceService updateResourceService;
    @Autowired private GetResourceListItemService getResourceListItemService;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ResourceRepository resourceRepository;

}
