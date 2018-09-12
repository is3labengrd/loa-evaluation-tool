package eng.it.loatool.api.v1.process_loa_info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_loa_info.ProcessLOAInformation;
import eng.it.loatool.process_loa_info.ProcessLOAInformationRepository;

@Service
public class CreateProcessLOAInformationService {

    @Transactional
    public Optional<ProcessLOAInformation> createInformation(ProcessLOAInformation processLOAInformation) {
        if (
            processLOAInformation.getPkTbId() == null ||
            !processLOAInformationRepository.existsById(processLOAInformation.getPkTbId())
        ) {
            processLOAInformationRepository.save(processLOAInformation);
            return Optional.of(processLOAInformation);
        }
        return Optional.empty();
    }

    @Autowired private ProcessLOAInformationRepository processLOAInformationRepository;

}
