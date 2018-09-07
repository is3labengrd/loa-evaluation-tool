package eng.it.loatool.api.v1.subprocess_levels;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class GetSubProcessLevelService {

    @Transactional
    public Optional<SubProcessLevel> getSubProcessLevel(Integer processId) {
        return tbAceSubProLevRepository.findById(processId);
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
