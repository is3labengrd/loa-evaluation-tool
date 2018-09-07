package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class GetSubProcessesService {

    @Transactional
    Iterable<SubProcessLevel> getSubProcesses() {
        return tbAceSubProLevRepository.findAll();
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
