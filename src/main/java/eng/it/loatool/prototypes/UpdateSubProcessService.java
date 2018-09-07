package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class UpdateSubProcessService {

    @Transactional
    public SubProcessLevel updateSubProcess(Integer processId, SubProcessLevel tbAceSubProLev) {
        tbAceSubProLev.setPkTbId(processId);
        tbAceSubProLevRepository.save(tbAceSubProLev);
        return tbAceSubProLev;
    }

    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
