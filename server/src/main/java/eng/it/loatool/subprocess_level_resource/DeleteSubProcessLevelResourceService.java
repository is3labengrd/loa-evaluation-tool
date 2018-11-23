package eng.it.loatool.subprocess_level_resource;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.scenario_resource.ScenarioResource;
import eng.it.loatool.scenario_resource.ScenarioResourceRepository;
import eng.it.loatool.subscenario.SubScenario;
import eng.it.loatool.subscenario.SubScenarioRepository;

@Service
public class DeleteSubProcessLevelResourceService {

    @Transactional
    public Optional<SubProcessLevelResource> delete(Integer id) {
        Optional<SubProcessLevelResource> optional = generalCRUDService.delete(subProcessLevelResourceRepository, id);
        optional.ifPresent((subprocessLevelResource) -> {
            Example<ScenarioResource> scenarioResourceCriteria = buildScenarioResourceSearchCritera(subprocessLevelResource);
            scenarioResourceRepository
                .findAll(scenarioResourceCriteria)
                .forEach((innerScenarioResource) -> {
                    scenarioResourceRepository.delete(innerScenarioResource);
                });
            Example<SubScenario> subScenarioCriteria = buildSubScenarioSearchCritera(subprocessLevelResource);
            subScenarioRepository
                .findAll(subScenarioCriteria)
                .forEach((innerSubScenario) -> {
                    subScenarioRepository.delete(innerSubScenario);
                });
        });
        return optional;
    }

    private Example<ScenarioResource> buildScenarioResourceSearchCritera(
        SubProcessLevelResource subprocessLevelResource
    ) {
        ScenarioResource scenarioResource = new ScenarioResource();
        scenarioResource.setFkTbAceRes(subprocessLevelResource.getResource().getPkTbId());
        scenarioResource.setFkTbAceSubProLev(subprocessLevelResource.getSubprocessLevel().getPkTbId());
        Example<ScenarioResource> example = Example.of(scenarioResource);
        return example;
    }

    private Example<SubScenario> buildSubScenarioSearchCritera(
        SubProcessLevelResource subprocessLevelResource
    ) {
        SubScenario subScenario = new SubScenario();
        subScenario.setFkTbAceRes(subprocessLevelResource.getResource().getPkTbId());
        subScenario.setFkTbAceSubProLev(subprocessLevelResource.getSubprocessLevel().getPkTbId());
        Example<SubScenario> example = Example.of(subScenario);
        return example;
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private SubProcessLevelResourceRepository subProcessLevelResourceRepository;
    @Autowired private ScenarioResourceRepository scenarioResourceRepository;
    @Autowired private SubScenarioRepository subScenarioRepository;

}
