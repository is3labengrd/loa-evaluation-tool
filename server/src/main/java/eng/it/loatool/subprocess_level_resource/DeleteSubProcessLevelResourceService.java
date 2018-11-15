package eng.it.loatool.subprocess_level_resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.scenario_resource.ScenarioResource;
import eng.it.loatool.scenario_resource.ScenarioResourceRepository;

@Service
public class DeleteSubProcessLevelResourceService {

    @Transactional
    public Optional<SubProcessLevelResource> delete(Integer id) {
        Optional<SubProcessLevelResource> optional = generalCRUDService.delete(subProcessLevelResourceRepository, id);
        optional.ifPresent((subprocessLevelResource) -> {
            ScenarioResource scenarioResource = new ScenarioResource();
            scenarioResource.setFkTbAceRes(subprocessLevelResource.getResource().getPkTbId());
            scenarioResource.setFkTbAceSubProLev(subprocessLevelResource.getSubprocessLevel().getPkTbId());
            Example<ScenarioResource> example = Example.of(scenarioResource);
            scenarioResourceRepository.findAll(example).forEach((innerScenarioResource) -> {
                scenarioResourceRepository.delete(innerScenarioResource);
            });
        });
        return optional;
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private SubProcessLevelResourceRepository subProcessLevelResourceRepository;
    @Autowired private ScenarioResourceRepository scenarioResourceRepository;

}
