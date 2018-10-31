package eng.it.loatool.subprocess_level;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetSubProcessLevelsService {

    @Transactional
    public Iterable<SubProcessLevel> getSubProcessLevels(int page, int size) {
        if (page < 0 || size < 1) {
            return tbAceSubProLevRepository.findAll();
        }
        return tbAceSubProLevRepository.findAll(PageRequest.of(page, size));
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
