package eng.it.loatool.api.v1.process_sequences;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_sequence.ProcessSequence;
import eng.it.loatool.process_sequence.ProcessSequenceRepository;

@Service
public class UpdateProcessService {

    @Transactional
    public Optional<ProcessSequence> updateProcess(Integer processId, ProcessSequence tbAceProSeq) {
        if (
            tbAceProSeq.getPkTbId() == null ||
            !tbAceProSeqRepository.existsById(tbAceProSeq.getPkTbId())
        ) {
            return Optional.empty();
        }
        tbAceProSeqRepository.save(tbAceProSeq);
        return Optional.of(tbAceProSeq);
    }

    @Autowired private ProcessSequenceRepository tbAceProSeqRepository;

}
