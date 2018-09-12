package eng.it.loatool.api.v1.physical_loa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.physical_loa.PhysicalLOA;
import eng.it.util.ResponseEntityTransformer;

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

    @PutMapping("/v1/physical-loa/{id}")
    public ResponseEntity<?> putPhysicalLOA(
        @PathVariable("id") Integer id,
        @RequestBody PhysicalLOA body
    ) {
        return ResponseEntityTransformer.transform(
            putPhysicalLOAService.putPhysicalLOA(id, body)
        );
    }

    @Autowired private PutPhysicalLOAService putPhysicalLOAService;
    @Autowired private GetAllPhysicalLOAService getAllPhysicalLOAService;
    @Autowired private GetSinglePhysicalLOAService getSinglePhysicalLOAService;

}
