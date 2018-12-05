package eng.it.loatool.subprocess_level;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_loa_info.ProcessLOAInformationRepository;

@Service
public class GetSubProcessLevelService {

    @Transactional
    public Optional<SubProcessLevel> getSubProcessLevel(Integer subprocessId) {
        Optional<SubProcessLevel> maybeSubprocessLevel = tbAceSubProLevRepository.findById(subprocessId);
        maybeSubprocessLevel
            .ifPresent((subprocessLevel) -> {
                processLOAInformationRepository
                    .getPhysicalProcessLOAInformationBySubprocessId(subprocessId)
                    .ifPresent((info) -> {
                        subprocessLevel.setLoaInfo(info);
                    });
            });
        return maybeSubprocessLevel;
    }

    @Transactional
    public void addInfoToSubprocessLevel(SubProcessLevel subprocessLevel) {
        Integer subprocessId = subprocessLevel.getPkTbId();
        processLOAInformationRepository
            .getPhysicalProcessLOAInformationBySubprocessId(subprocessId)
            .ifPresent((info) -> {
                subprocessLevel.setLoaInfo(info);
            });
    }

    @Autowired private ProcessLOAInformationRepository processLOAInformationRepository;
    @Autowired private SubProcessLevelRepository tbAceSubProLevRepository;

}
