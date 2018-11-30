package eng.it.loatool.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.scenario_resource.ScenarioResourceRepository;
import eng.it.loatool.subprocess_level_resource.SubProcessLevelResourceRepository;
import eng.it.loatool.subscenario.SubScenario;
import eng.it.loatool.subscenario.SubScenarioRepository;

@Service
public class DeleteResourceService {

    @Transactional
    public void delete(Integer resourceId) {
        resourceRepository
            .findById(resourceId)
            .ifPresent((resource) -> {
                subProcessLevelResourceRepository
                    .getSubProcessLevelResourcebyResourceId(resourceId)
                    .forEach((subProcessLevelResource) -> {
                        subProcessLevelResourceRepository.delete(subProcessLevelResource);
                    });
                getSubScenariosByResourceId(resourceId)
                    .forEach((subScenario) -> {
                        subScenarioRepository.delete(subScenario);
                    });
                scenarioResourceRepository
                    .getScenarioResourcesbyResourceId(resourceId)
                    .forEach((scenarioResources) -> {
                        scenarioResourceRepository.delete(scenarioResources);
                    });
                resourceRepository.deleteById(resourceId);
            });
    }

    @Transactional
    private List<SubScenario> getSubScenariosByResourceId(Integer resourceId) {
        Resource resourceWithId = new Resource();
        resourceWithId.setPkTbId(resourceId);
        SubScenario subScenario = new SubScenario();
        subScenario.setResource(resourceWithId);
        return subScenarioRepository
            .findAll(Example.of(subScenario));
    }

    @Autowired private ResourceRepository resourceRepository;
    @Autowired private ScenarioResourceRepository scenarioResourceRepository;
    @Autowired private SubScenarioRepository subScenarioRepository;
    @Autowired private SubProcessLevelResourceRepository subProcessLevelResourceRepository;

}
