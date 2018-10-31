package eng.it.loatool.process_loa_info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class CreateProcessLOAInformationService {

    @Transactional
    public Optional<ProcessLOAInformation> createInformation(ProcessLOAInformation processLOAInformation) {
        if (
            processLOAInformation.getPkTbId() == null ||
            !processLOAInformationRepository.existsById(processLOAInformation.getPkTbId())
        ) {
            subProcessLevelRepository
                .findById(processLOAInformation.getFkTbAceSubProLev())
                .ifPresent(
                    (subprocessLevel) -> {
                        processLOAInformation.setSubProcessLevel(subprocessLevel);
                    }
                );
            return Optional.of(processLOAInformationRepository.save(processLOAInformation));
        }
        return Optional.empty();
    }

    @Autowired private ProcessLOAInformationRepository processLOAInformationRepository;
    @Autowired private SubProcessLevelRepository subProcessLevelRepository;

}
