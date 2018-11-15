package eng.it.loatool.scenario_resource;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.resource.ResourceRepository;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class GetScenarioResourceService {

    @Transactional
    public Iterable getScenarioResources() {
        Iterable<ScenarioResource> scenarioResources = generalCRUDService
            .getAll(scenarioResourceRepository);
        for (ScenarioResource scenarioResource: scenarioResources) {
            attachReferencedEntites(scenarioResource);
        }
        return scenarioResources;
    }

    @Transactional
    public Optional getScenarioResourcebyId(Integer id) {
        Optional<ScenarioResource> optional = generalCRUDService
            .getOne(scenarioResourceRepository, id);
        optional.ifPresent((scenarioResource) -> {
            attachReferencedEntites(scenarioResource);
        });
        return optional;
    }

    public ScenarioResource attachReferencedEntites(ScenarioResource scenarioResource) {
        resourceRepository
            .findById(scenarioResource.getFkTbAceRes())
            .ifPresent((resource) -> {
                scenarioResource.setResource(resource);
            });
        subProcessLevelRepository
            .findById(scenarioResource.getFkTbAceSubProLev())
            .ifPresent((subprocessLevel) -> {
                scenarioResource.setSubprocessLevel(subprocessLevel);
            });
        return scenarioResource;
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ScenarioResourceRepository scenarioResourceRepository;
    @Autowired private SubProcessLevelRepository subProcessLevelRepository;
    @Autowired private ResourceRepository resourceRepository;

}
