package eng.it.loatool.subprocess_level_resource;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.resource.Resource;
import eng.it.loatool.resource.ResourceRepository;
import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class PersistSubProcessLevelResourceService {

    @Transactional
    public Optional create(
            SubProcessLevelResource entity
    ) {
        if (
            entity.getPrimaryKey() == null ||
            !subProcessLevelResourceRepository.existsById(entity.getPkTbId())
        ) {
            entity = manageRelatedObjects(entity);
            return Optional.of(subProcessLevelResourceRepository.save(entity));
        }
        return Optional.empty();
    }

    @Transactional
    public Optional update(
        Integer primaryKey,
        SubProcessLevelResource entity
    ) {
        entity.setPrimaryKey(primaryKey);
        entity = manageRelatedObjects(entity);
        if (primaryKey == null || !subProcessLevelResourceRepository.existsById(primaryKey)) {
            return Optional.empty();
        }
        return Optional.of(subProcessLevelResourceRepository.save(entity));
    }

    private SubProcessLevelResource manageRelatedObjects(SubProcessLevelResource element) {
        Optional<SubProcessLevel> subprocessMaybe = subProcessLevelRepository
            .findById(zeroIfNull(element.getFkTbAceSubProLev()));
        Optional<Resource> resourceMaybe =resourceRepository
            .findById(zeroIfNull(element.getFkTbAceRes()));
        if (subprocessMaybe.isPresent()) {
            element.setSubprocessLevel(subprocessMaybe.get());
        }
        if (resourceMaybe.isPresent()) {
            element.setResource(resourceMaybe.get());
        }
        return element;
    }

    private Integer zeroIfNull(Integer x) {
        if (x == null) {
            return 0;
        }
        return x;
    }

    @Autowired private SubProcessLevelResourceRepository subProcessLevelResourceRepository;
    @Autowired private SubProcessLevelRepository subProcessLevelRepository;
    @Autowired private ResourceRepository resourceRepository;

}
