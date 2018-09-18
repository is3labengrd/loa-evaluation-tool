package eng.it.loatool.api.v1.scenarios;

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
import eng.it.loatool.scenario.Scenario;
import eng.it.loatool.scenario.ScenarioRepository;

@Controller
public class ScenariosApi {

    @GetMapping("/v1/scenarios")
    public ResponseEntity<?> getScenarios() {
        return ResponseEntityTransformer.transformOk(
            getScenariosService.getScenarios()
        );
    }

    @GetMapping("/v1/scenarios/{scenarioId}")
    public ResponseEntity<?> getScenario(
        @PathVariable("scenarioId") Integer id
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(scenarioRepository, id)
        );
    }

    @PostMapping("/v1/scenarios")
    public ResponseEntity<?> createScenario(
        @RequestBody Scenario body
    ) {
        return ResponseEntityTransformer.transform(
            createScenarioService.createScenario(body)
        );
    }

    @PutMapping("/v1/scenarios/{scenarioId}")
    public ResponseEntity<?> updateScenario(
        @PathVariable("scenarioId") Integer id,
        @RequestBody Scenario body
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.update(scenarioRepository, id, body)
        );
    }

    @Autowired private GetScenariosService getScenariosService;
    @Autowired private CreateScenarioService createScenarioService;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ScenarioRepository scenarioRepository;

}
