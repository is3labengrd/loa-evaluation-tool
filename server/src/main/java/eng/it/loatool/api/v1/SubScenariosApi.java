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
import eng.it.loatool.subscenario.SubScenario;
import eng.it.loatool.subscenario.SubScenarioRepository;

@Controller
public class SubScenariosApi {

    @GetMapping("/v1/subscenarios")
    public ResponseEntity<?> getEveryResource() {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(subScenarioRepository)
        );
    }

    @GetMapping("/v1/subscenarios/{id}")
    public ResponseEntity<?> getResource(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(subScenarioRepository, id)
        );
    }

    @PostMapping("/v1/subscenarios")
    public ResponseEntity<?> createResource(@RequestBody SubScenario body) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.create(subScenarioRepository, body)
        );
    }

    @PutMapping("/v1/subscenarios/{id}")
    public ResponseEntity<?> updateResource(
        @PathVariable("id") Integer id,
        @RequestBody SubScenario body
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.update(subScenarioRepository, id, body)
        );
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private SubScenarioRepository subScenarioRepository;

}