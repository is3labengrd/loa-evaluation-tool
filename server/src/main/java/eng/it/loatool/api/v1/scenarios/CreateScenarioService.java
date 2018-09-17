package eng.it.loatool.api.v1.scenarios;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.scenario.Scenario;
import eng.it.loatool.scenario.ScenarioRepository;

@Service
public class CreateScenarioService {

    @Transactional
    public Optional<Scenario> createScenario(Scenario scenario) {
        if (
            scenario.getPkTbId() == null ||
            !scenarioRepository.existsById(scenario.getPkTbId())
        ) {
            return Optional.of(scenarioRepository.save(scenario));
        }
        return Optional.empty();
    }

    @Autowired private ScenarioRepository scenarioRepository;

}
