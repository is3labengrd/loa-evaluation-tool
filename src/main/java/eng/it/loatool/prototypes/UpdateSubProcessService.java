package eng.it.loatool.prototypes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class UpdateSubProcessService {

    @Transactional
    public Optional<SubProcessLevel> updateSubProcess(Integer processId, SubProcessLevel tbAceSubProLev) {
        tbAceSubProLev.setPkTbId(processId);
        if (processId == null || !tbAceSubProLevRepository.existsById(processId)) {
            return Optional.empty();
        }
        tbAceSubProLevRepository.save(tbAceSubProLev);
        return Optional.of(tbAceSubProLev);
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
