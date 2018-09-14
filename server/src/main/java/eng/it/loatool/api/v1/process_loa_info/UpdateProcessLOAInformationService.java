package eng.it.loatool.api.v1.process_loa_info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.process_loa_info.ProcessLOAInformation;
import eng.it.loatool.process_loa_info.ProcessLOAInformationRepository;

@Service
public class UpdateProcessLOAInformationService {

    public Optional<ProcessLOAInformation> updateInformation(Integer processId, ProcessLOAInformation processLOAInformation) {
        processLOAInformation.setPkTbId(processId);
        if (processId == null || !processLOAInformationRepository.existsById(processId)) {
            return Optional.empty();
        }
        return Optional.of(processLOAInformationRepository.save(processLOAInformation));
    }

    @Autowired private ProcessLOAInformationRepository processLOAInformationRepository;

}
