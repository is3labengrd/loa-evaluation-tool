package eng.it.loatool.api.v1.physical_loa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.physical_loa.PhysicalLOA;

@Controller
public class PhysicalLOAApi {

    @GetMapping("/v1/physical-loa")
    public ResponseEntity<?> getAllPhysicalLOA() {
        return ResponseEntityTransformer.transformOk(
            getAllPhysicalLOAService.getAllPhysicalLOA()
        );
    }

    @GetMapping("/v1/physical-loa/{id}")
    public ResponseEntity<?> getSinglePhysicalLOA(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            getSinglePhysicalLOAService.getSinglePhysicalLOA(id)
        );
    }

    @PostMapping("/v1/physical-loa")
    public ResponseEntity<?> createPhysicalLOA(@RequestBody PhysicalLOA body) {
        return ResponseEntityTransformer.transform(
            createPhysicalLOAService.createPhysicalLOA(body)
        );
    }

    @PutMapping("/v1/physical-loa/{id}")
    public ResponseEntity<?> updatePhysicalLOA(
        @PathVariable("id") Integer id,
        @RequestBody PhysicalLOA body
    ) {
        return ResponseEntityTransformer.transform(
            putPhysicalLOAService.updatePhysicalLOA(id, body)
        );
    }

    @Autowired private UpdatePhysicalLOAService putPhysicalLOAService;
    @Autowired private CreatePhysicalLOAService createPhysicalLOAService;
    @Autowired private GetAllPhysicalLOAService getAllPhysicalLOAService;
    @Autowired private GetSinglePhysicalLOAService getSinglePhysicalLOAService;

}
