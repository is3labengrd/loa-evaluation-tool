package eng.it.loatool.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.product_planning.GetProductPlanningByProcessSegmentId;
import eng.it.loatool.product_planning.ProductPlanning;
import eng.it.loatool.product_planning.ProductPlanningRepository;
import eng.it.loatool.product_planning.ProductPlanningService;

@Controller
public class ProductPlanningApi {

    @GetMapping("/v1/product-planning")
    public ResponseEntity<?> getEveryProductPlanning() {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(productPlanningRepository)
        );
    }

    @GetMapping("/v1/product-planning/{id}")
    public ResponseEntity<?> getProductPlanning(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(productPlanningRepository, id)
        );
    }

    @GetMapping("/v1/product-planning-by-process-segment-id/{processSegmentId}")
    public ResponseEntity<?> getProductPlanningByProcessSegmentId(
        @PathVariable("processSegmentId") Integer processSegmentId
    ) {
        return ResponseEntityTransformer.transform(
            getProductPlanningByProcessSegmentId.getOne(processSegmentId)
        );
    }


    @PostMapping("/v1/product-planning")
    public ResponseEntity<?> createProductPlanning(@RequestBody ProductPlanning body) {
        return ResponseEntityTransformer.transform(
        	productPlanningService.create(body)
        );
    }
    private ResponseEntity createError = new ResponseEntity<>(HttpStatus.BAD_REQUEST);

    @PutMapping("/v1/product-planning/{id}")
    public ResponseEntity<?> updateProductPlanning(
        @PathVariable("id") Integer id,
        @RequestBody ProductPlanning body
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.update(productPlanningRepository, id, body)
        );
    }

    @Autowired private GetProductPlanningByProcessSegmentId getProductPlanningByProcessSegmentId;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ProductPlanningRepository productPlanningRepository;
    @Autowired private ProductPlanningService productPlanningService;

}
