package eng.it.loatool.scenario_resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;

@Service
public class PersistScenarioResourceService {

    @Transactional
    public Optional<ScenarioResource> create(ScenarioResource scenarioResource) {
        Optional<ScenarioResource> optional = generalCRUDService
            .create(scenarioResourceRepository, scenarioResource);
        optional.ifPresent((currentScenarioResource) -> {
            getScenarioResourceService.attachReferencedEntites(currentScenarioResource);
        });
        return optional;
    }

    @Transactional
    public Optional<ScenarioResource> update(Integer id ,ScenarioResource scenarioResource) {
        Optional<ScenarioResource> optional = generalCRUDService
            .update(scenarioResourceRepository, id, scenarioResource);
        optional.ifPresent((currentScenarioResource) -> {
            getScenarioResourceService.attachReferencedEntites(currentScenarioResource);
        });
        return optional;
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private GetScenarioResourceService getScenarioResourceService;
    @Autowired private ScenarioResourceRepository scenarioResourceRepository;

}
