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
import eng.it.loatool.subscenario.PersistSubScenarioService;
import eng.it.loatool.subscenario.SubScenario;
import eng.it.loatool.subscenario.SubScenarioRepository;
import eng.it.loatool.subscenario.SubScenariosSearchService;

@Controller
public class SubScenariosApi {

    @GetMapping("/v1/subscenarios")
    public ResponseEntity<?> getEverySubScenario() {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(subScenarioRepository)
        );
    }

    @GetMapping("/v1/subscenarios/{id}")
    public ResponseEntity<?> getSubScenario(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(subScenarioRepository, id)
        );
    }

    @PostMapping("/v1/subscenarios/search")
    public ResponseEntity<?> searchSubScenario(@RequestBody SubScenario body) {
        return ResponseEntityTransformer.transform(
            subScenariosSearchService.search(body)
        );
    }

    @PostMapping("/v1/subscenarios")
    public ResponseEntity<?> createSubScenario(@RequestBody SubScenario body) {
        return ResponseEntityTransformer.transform(
            persistSubScenarioService.create(body)
        );
    }

    @PutMapping("/v1/subscenarios/{id}")
    public ResponseEntity<?> updateSubScenario(
        @PathVariable("id") Integer id,
        @RequestBody SubScenario body
    ) {
        return ResponseEntityTransformer.transform(
            persistSubScenarioService.update(id, body)
        );
    }

    @Autowired private PersistSubScenarioService persistSubScenarioService;
    @Autowired private SubScenariosSearchService subScenariosSearchService;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private SubScenarioRepository subScenarioRepository;

}
