package eng.it.loatool.scenario_resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.scenario.ScenarioRepository;

@Service
public class DeleteScenarioResourceService {

    @Transactional
    public void deleteByResourceId(Integer id) {
        scenarioResourceRepository
            .getScenarioResourcesbyResourceId(id)
            .forEach((scenarioResource) -> {
                scenarioRepository.deleteById(
                    scenarioResource.getFkTbAceScenarios()
                );
                scenarioResourceRepository.delete(scenarioResource);
            });
    }

    @Autowired private ScenarioResourceRepository scenarioResourceRepository;
    @Autowired private ScenarioRepository scenarioRepository;

}
