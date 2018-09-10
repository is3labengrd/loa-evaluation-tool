package eng.it.loatool.api.v1.process_segments;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_sequence.ProcessSegment;
import eng.it.loatool.process_sequence.ProcessSegmentRepository;

@Service
public class UpdateProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> updateProcessSegment(Integer processId, ProcessSegment tbAceProSeq) {
        tbAceProSeq.setPkTbId(processId);
        if (processId == null || !tbAceProSeqRepository.existsById(processId)) {
            return Optional.empty();
        }
        tbAceProSeqRepository.save(tbAceProSeq);
        return Optional.of(tbAceProSeq);
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;

}
