package eng.it.loatool.subprocess_level;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateSubProcessLevelService {

    @Transactional
    public Optional<SubProcessLevel> createSubProcessLevel(SubProcessLevel tbAceSubProLev) {
        if (
            tbAceSubProLev.getPkTbId() == null ||
            !tbAceSubProLevRepository.existsById(tbAceSubProLev.getPkTbId())
        ) {
            return Optional.of(tbAceSubProLevRepository.save(tbAceSubProLev));
        }
        return Optional.empty();
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
