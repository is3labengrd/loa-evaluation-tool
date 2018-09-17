package eng.it.loatool.api.v1.process_segments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_segment.ProcessSegment;
import eng.it.loatool.process_segment.ProcessSegmentRepository;

@Service
public class GetProcessSegmentsService {

    @Transactional
    Iterable<ProcessSegment> getProcessSegments() {
        return tbAceProSeqRepository.findAll();
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;

}
