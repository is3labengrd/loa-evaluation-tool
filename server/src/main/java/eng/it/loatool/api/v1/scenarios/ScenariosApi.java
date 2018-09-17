package eng.it.loatool.api.v1.scenarios;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ScenariosApi {

    @GetMapping("/v1/scenarios")
    public ResponseEntity<?> getScenarios() {
        return null;
    }

    @GetMapping("/v1/scenarios/{scenarioId}")
    public ResponseEntity<?> getScenario() {
        return null;
    }

    @PostMapping("/v1/scenarios")
    public ResponseEntity<?> createScenario() {
        return null;
    }

    @PutMapping("/v1/scenarios/{scenarioId}")
    public ResponseEntity<?> updateScenario() {
        return null;
    }

}
