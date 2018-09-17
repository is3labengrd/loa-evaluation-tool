package eng.it.loatool.api.v1.scenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.scenario.Scenario;
import eng.it.util.ResponseEntityTransformer;

@Controller
public class ScenariosApi {

    @GetMapping("/v1/scenarios")
    public ResponseEntity<?> getScenarios() {
        return ResponseEntityTransformer.transformOk(
            getScenariosService.getScenarios()
        );
    }

    @GetMapping("/v1/scenarios/{scenarioId}")
    public ResponseEntity<?> getScenario() {
        return null;
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
    public ResponseEntity<?> updateScenario() {
        return null;
    }

    @Autowired private GetScenariosService getScenariosService;
    @Autowired private CreateScenarioService createScenarioService;

}
