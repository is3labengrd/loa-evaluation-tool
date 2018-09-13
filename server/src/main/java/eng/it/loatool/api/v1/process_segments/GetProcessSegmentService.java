package eng.it.loatool.api.v1.process_segments;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_segment.ProcessSegment;
import eng.it.loatool.process_segment.ProcessSegmentRepository;

@Service
public class GetProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> getProcessSegment(Integer processId) {
        return tbAceProSeqRepository.findById(processId);
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;

}
