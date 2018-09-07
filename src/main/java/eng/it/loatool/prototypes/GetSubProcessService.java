package eng.it.loatool.prototypes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class GetSubProcessService {

    @Transactional
    public Optional<SubProcessLevel> getSubProcess(Integer processId) {
        return tbAceSubProLevRepository.findById(processId);
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
