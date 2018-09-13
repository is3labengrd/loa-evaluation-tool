package eng.it.loatool.api.v1.process_segments;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_sequence.ProcessSegment;
import eng.it.loatool.process_sequence.ProcessSegmentRepository;

@Service
public class CreateProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> createProcessSegment(ProcessSegment tbAceProSeq) {
        if (
            tbAceProSeq.getPkTbId() == null ||
            !tbAceProSeqRepository.existsById(tbAceProSeq.getPkTbId())
        ) {
            tbAceProSeqRepository.save(tbAceProSeq);
            return Optional.of(tbAceProSeq);
        }
        return Optional.empty();
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;

}