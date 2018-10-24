package eng.it.loatool.api.v1.process_loa_info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.process_loa_info.ProcessLOAInformation;
import eng.it.loatool.process_loa_info.ProcessLOAInformationRepository;

@Service
public class UpdateProcessLOAInformationService {

    public Optional<ProcessLOAInformation> updateInformation(Integer processId, ProcessLOAInformation processLOAInformation) {
        return processLOAInformationRepository
            .findById(processId)
            .map(
                (info) -> {
                    processLOAInformation.setPkTbId(processId);
                    processLOAInformation.setSubProcessLevel(
                        info.getSubProcessLevel()
                    );
                    return processLOAInformationRepository
                        .save(processLOAInformation);
                }
            );
    }

    @Autowired private ProcessLOAInformationRepository processLOAInformationRepository;

}
