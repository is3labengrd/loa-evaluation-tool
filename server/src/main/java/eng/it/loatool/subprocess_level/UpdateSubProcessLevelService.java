package eng.it.loatool.subprocess_level;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateSubProcessLevelService {

    @Transactional
    public Optional<SubProcessLevel> updateSubProcessLevel(Integer processId, SubProcessLevel tbAceSubProLev) {
        tbAceSubProLev.setPkTbId(processId);
        if (processId == null || !tbAceSubProLevRepository.existsById(processId)) {
            return Optional.empty();
        }
        return Optional.of(tbAceSubProLevRepository.save(tbAceSubProLev));
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
