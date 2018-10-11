package eng.it.loatool.api.v1.subprocess_levels;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_loa_info.ProcessLOAInformation;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class GetSubProcessLevelInfoService {

    @Transactional
    public Optional<ProcessLOAInformation> getSubProcessInfo(Integer processId) {
        return tbAceSubProLevRepository.getSubProcessInfo(processId);
    }



    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
