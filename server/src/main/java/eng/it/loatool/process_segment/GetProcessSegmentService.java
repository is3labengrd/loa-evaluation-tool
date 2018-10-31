package eng.it.loatool.process_segment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> getProcessSegment(Integer processId) {
        return tbAceProSeqRepository.findById(processId);
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;

}
