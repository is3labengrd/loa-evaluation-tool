package eng.it.loatool.process_segment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> createProcessSegment(ProcessSegment tbAceProSeq) {
        if (
            tbAceProSeq.getPkTbId() == null ||
            !tbAceProSeqRepository.existsById(tbAceProSeq.getPkTbId())
        ) {
            return Optional.of(tbAceProSeqRepository.save(tbAceProSeq));
        }
        return Optional.empty();
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;

}
