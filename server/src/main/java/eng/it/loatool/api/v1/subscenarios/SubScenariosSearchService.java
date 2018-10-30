package eng.it.loatool.api.v1.subscenarios;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import eng.it.loatool.subscenario.SubScenario;
import eng.it.loatool.subscenario.SubScenarioRepository;

@Service
public class SubScenariosSearchService {

    public Collection<SubScenario> search(SubScenario example) {
        return subScenarioRepository.findAll(Example.<SubScenario>of(example));
    }

    @Autowired SubScenarioRepository subScenarioRepository;

}
