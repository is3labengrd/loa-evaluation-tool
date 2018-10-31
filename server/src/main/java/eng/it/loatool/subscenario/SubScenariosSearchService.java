package eng.it.loatool.subscenario;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class SubScenariosSearchService {

    public Collection<SubScenario> search(SubScenario example) {
        return subScenarioRepository.findAll(Example.<SubScenario>of(example));
    }

    @Autowired SubScenarioRepository subScenarioRepository;

}
