package eng.it.loatool.process_segment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> updateProcessSegment(Integer processId, ProcessSegment tbAceProSeq) {
        tbAceProSeq.setPkTbId(processId);
        if (processId == null || !tbAceProSeqRepository.existsById(processId)) {
            return Optional.empty();
        }
        return Optional.of(tbAceProSeqRepository.save(tbAceProSeq));
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;

}
