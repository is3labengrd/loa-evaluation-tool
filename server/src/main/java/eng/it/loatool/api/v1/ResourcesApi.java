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
import eng.it.loatool.resource.Resource;
import eng.it.loatool.resource.ResourceRepository;

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
            generalCRUDService.update(resourceRepository, id, body)
        );
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ResourceRepository resourceRepository;

}
