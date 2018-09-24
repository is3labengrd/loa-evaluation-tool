package eng.it.loatool.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.resource.Resource;
import eng.it.loatool.resource.ResourceRepository;

@Controller
public class ResourcesApi {

    @GetMapping("/v1/resources")
    public ResponseEntity<?> getEveryProductPlanning() {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(resourceRepository)
        );
    }

    @GetMapping("/v1/resources/{id}")
    public ResponseEntity<?> getProductPlanning(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(resourceRepository, id)
        );
    }

    @PostMapping("/v1/resources")
    public ResponseEntity<?> createProductPlanning(@RequestBody Resource body) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.create(resourceRepository, body)
        );
    }

    @PutMapping("/v1/resources/{id}")
    public ResponseEntity<?> updateProductPlanning(
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
