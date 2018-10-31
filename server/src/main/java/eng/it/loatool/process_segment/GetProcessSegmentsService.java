package eng.it.loatool.process_segment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetProcessSegmentsService {

    @Transactional public Iterable<ProcessSegment> getProcessSegments() {
        return tbAceProSeqRepository.findAll();
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;

}
