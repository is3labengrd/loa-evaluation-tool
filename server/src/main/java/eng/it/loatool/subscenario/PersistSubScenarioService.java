package eng.it.loatool.subscenario;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.resource.Resource;
import eng.it.loatool.resource.ResourceRepository;
import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class PersistSubScenarioService {

    @Transactional
    public Optional create(
        SubScenario entity
    ) {
        if (
            entity.getPrimaryKey() == null ||
            !subScenarioRepository.existsById(entity.getPkTbId())
        ) {
            entity = manageRelatedObjects(entity);
            return Optional.of(subScenarioRepository.save(entity));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional update(
        Integer primaryKey,
        SubScenario entity
    ) {
        entity.setPrimaryKey(primaryKey);
        entity = manageRelatedObjects(entity);
        if (primaryKey == null || !subScenarioRepository.existsById(primaryKey)) {
            return Optional.empty();
        }
        return Optional.of(subScenarioRepository.save(entity));
    }

    private SubScenario manageRelatedObjects(SubScenario entity) {
        Optional<SubProcessLevel> subprocessMaybe = subProcessLevelRepository
            .findById(zeroIfNull(entity.getFkTbAceSubProLev()));
        Optional<Resource> resourceMaybe = resourceRepository
            .findById(zeroIfNull(entity.getFkTbAceRes()));
        if (subprocessMaybe.isPresent()) {
            entity.setSubprocessLevel(subprocessMaybe.get());
        }
        if (resourceMaybe.isPresent()) {
            entity.setResource(resourceMaybe.get());
        }
        return entity;
    }

    private Integer zeroIfNull(Integer x) {
        if (x == null) {
            return 0;
        }
        return x;
    }

    @Autowired private SubScenarioRepository subScenarioRepository;
    @Autowired private SubProcessLevelRepository subProcessLevelRepository;
    @Autowired private ResourceRepository resourceRepository;

}
