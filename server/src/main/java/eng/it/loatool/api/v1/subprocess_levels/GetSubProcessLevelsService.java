package eng.it.loatool.api.v1.subprocess_levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class GetSubProcessLevelsService {

    @Transactional public Iterable<SubProcessLevel> getSubProcessLevels() {
        return tbAceSubProLevRepository.findAll();
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
