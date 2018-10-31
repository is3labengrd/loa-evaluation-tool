package eng.it.loatool.subprocess_level;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetSubProcessLevelService {

    @Transactional
    public Optional<SubProcessLevel> getSubProcessLevel(Integer processId) {
        return tbAceSubProLevRepository.findById(processId);
    }



    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
