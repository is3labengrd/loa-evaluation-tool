package eng.it.loatool.api.v1.scenarios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.scenario.Scenario;
import eng.it.loatool.scenario.ScenarioRepository;

@Service
public class GetScenariosService {

    @Transactional
    Iterable<Scenario> getScenarios() {
        return scenarioRepository.findAll();
    }

    @Autowired private ScenarioRepository scenarioRepository;

}
