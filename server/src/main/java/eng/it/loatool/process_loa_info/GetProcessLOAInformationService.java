package eng.it.loatool.process_loa_info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetProcessLOAInformationService {

    @Transactional
    public Optional<ProcessLOAInformation> getInformation(Integer processId) {
        return processLOAInformationRepository.findById(processId);
    }

    @Transactional
    public Optional<ProcessLOAInformation> getInformationBySubprocessId(Integer subprocessId) {
        return processLOAInformationRepository.getProcessLOAInformationBySubprocessId(subprocessId);
    }

    @Transactional
    public Iterable<ProcessLOAInformation> getAllInformation() {
        return processLOAInformationRepository.findAll();
    }

    @Autowired private ProcessLOAInformationRepository processLOAInformationRepository;

}
