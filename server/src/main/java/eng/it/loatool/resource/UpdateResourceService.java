package eng.it.loatool.resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.scenario.Scenario;
import eng.it.loatool.scenario.ScenarioRepository;
import eng.it.loatool.scenario_resource.ScenarioResource;
import eng.it.loatool.scenario_resource.ScenarioResourceRepository;
import eng.it.loatool.subscenario.SubScenario;
import eng.it.loatool.subscenario.SubScenarioRepository;

@Service
public class UpdateResourceService {

    @Transactional
    public Optional<Resource> update(Integer id, Resource resource) {
        Optional<Resource> updateResult = generalCRUDService
            .update(resourceRepository, id, resource);
        updateResult.ifPresent((unimportantArgument) -> {
            SubScenario subscenario = new SubScenario();
            Resource idHolder = new Resource();
            idHolder.setPkTbId(resource.getPkTbId());
            subscenario.setResource(idHolder);
            Example<SubScenario> subscenarioSearchCriteria = Example.of(subscenario);
            subScenarioRepository.findAll(subscenarioSearchCriteria).forEach(
                (foundSubscenario) -> {
                    foundSubscenario.setResRecal(true);
                    subScenarioRepository.save(foundSubscenario);
                }
            );
            ScenarioResource scenarioResourceCriteria = new ScenarioResource();
            scenarioResourceCriteria.setFkTbAceRes(resource.getPkTbId());
            scenarioResourceRepository
                .findAll(Example.of(scenarioResourceCriteria))
                .forEach((foundScenarioResource) -> {
                    Optional<Scenario> maybeScenario = scenarioRepository.findById(
                        foundScenarioResource.getFkTbAceScenarios()
                    );
                    maybeScenario.ifPresent((scenario) -> {
                        scenario.setResRecal(true);
                        scenarioRepository.save(scenario);
                    });
                });
        });
        return updateResult;
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ResourceRepository resourceRepository;
    @Autowired private SubScenarioRepository subScenarioRepository;
    @Autowired private ScenarioResourceRepository scenarioResourceRepository;
    @Autowired private ScenarioRepository scenarioRepository;

}
