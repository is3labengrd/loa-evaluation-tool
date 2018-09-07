package eng.it.loatool.prototypes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class CreateSubProcessService {

    @Transactional
    public Optional<SubProcessLevel> createSubProcess(SubProcessLevel tbAceSubProLev) {
        if (
            tbAceSubProLev.getPkTbId() == null ||
            !tbAceSubProLevRepository.existsById(tbAceSubProLev.getPkTbId())
        ) {
            tbAceSubProLevRepository.save(tbAceSubProLev);
            return Optional.of(tbAceSubProLev);
        }
        return Optional.empty();
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
