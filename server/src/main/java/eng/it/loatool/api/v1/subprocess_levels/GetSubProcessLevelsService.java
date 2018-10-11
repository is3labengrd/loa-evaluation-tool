package eng.it.loatool.api.v1.subprocess_levels;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

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
