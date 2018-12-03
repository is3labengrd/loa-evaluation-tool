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
import eng.it.loatool.minimal_satisfaction.GetMinimalSatisfactionService;
import eng.it.loatool.minimal_satisfaction.MinimalSatisfaction;
import eng.it.loatool.minimal_satisfaction.MinimalSatisfactionRepository;

@Controller
public class MinimalSatisfactionsApi {

    @GetMapping("/v1/minimal-satisfactions")
    public ResponseEntity<?> getAllMinimalSatisfactions() {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(minimalSatisfactionRepository)
        );
    }

    @GetMapping("/v1/minimal-satisfactions/{id}")
    public ResponseEntity<?> getSingleMinimalSatisfaction(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(minimalSatisfactionRepository, id)
        );
    }

    @GetMapping("/v1/minimal-satisfactions-by-process-id/{id}")
    public ResponseEntity<?> getSingleMinimalSatisfactionByProcessId(
        @PathVariable("id") Integer processId
    ) {
        return ResponseEntityTransformer.transform(
            getMinimalSatisfactionService.getByProcessId(processId)
        );
    }

    @PostMapping("/v1/minimal-satisfactions")
    public ResponseEntity<?> createMinimalSatisfaction(@RequestBody MinimalSatisfaction body) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.create(minimalSatisfactionRepository, body)
        );
    }

    @PutMapping("/v1/minimal-satisfactions/{id}")
    public ResponseEntity<?> putMinimalSatisfaction(
        @PathVariable("id") Integer id,
        @RequestBody MinimalSatisfaction body
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.update(minimalSatisfactionRepository, id, body)
        );
    }

    @Autowired private GetMinimalSatisfactionService getMinimalSatisfactionService;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private MinimalSatisfactionRepository minimalSatisfactionRepository;

}
