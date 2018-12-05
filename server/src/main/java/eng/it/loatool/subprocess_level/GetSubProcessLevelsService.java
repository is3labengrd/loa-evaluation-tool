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
            return addInfoToSubprocessLevels(tbAceSubProLevRepository.findAll());
        }
        return addInfoToSubprocessLevels(
            tbAceSubProLevRepository.findAll(PageRequest.of(page, size))
        );
    }

    @Transactional
    public Iterable<SubProcessLevel> addInfoToSubprocessLevels(
        Iterable<SubProcessLevel> subprocessLevels
    ) {
        subprocessLevels.forEach((subprocessLevel) -> {
            getSubProcessLevelService.addInfoToSubprocessLevel(subprocessLevel);
        });
        return subprocessLevels;
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;
    @Autowired private GetSubProcessLevelService getSubProcessLevelService;

}
