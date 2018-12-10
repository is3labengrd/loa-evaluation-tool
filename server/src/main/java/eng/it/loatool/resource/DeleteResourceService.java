package eng.it.loatool.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.scenario_resource.DeleteScenarioResourceService;
import eng.it.loatool.subprocess_level_resource.SubProcessLevelResourceRepository;
import eng.it.loatool.subscenario.SubScenario;
import eng.it.loatool.subscenario.SubScenarioRepository;
import eng.it.loatool.var.request.VARWorkUnitImpl;

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
                deleteScenarioResourceService
                    .deleteByResourceId(resourceId);
                if (!resource.getVarRes()) {
                    try {
                        VARWorkUnitImpl.delete(resource.getName());
                    } catch (Exception e) {
                        throw new RuntimeException("Something went wrong when deleting resource named " + resource.getName());
                    }
                }
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
    @Autowired private SubScenarioRepository subScenarioRepository;
    @Autowired private DeleteScenarioResourceService deleteScenarioResourceService;
    @Autowired private SubProcessLevelResourceRepository subProcessLevelResourceRepository;

}
