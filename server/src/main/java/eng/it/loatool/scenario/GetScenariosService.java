package eng.it.loatool.scenario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetScenariosService {

    @Transactional public Iterable<Scenario> getScenarios() {
        return scenarioRepository.findAll();
    }

    @Autowired private ScenarioRepository scenarioRepository;

}
