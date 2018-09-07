package eng.it.loatool.api.v1.process_sequences;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_sequence.ProcessSequence;
import eng.it.loatool.process_sequence.ProcessSequenceRepository;

@Service
public class UpdateProcessSequenceService {

    @Transactional
    public Optional<ProcessSequence> updateProcessSequence(Integer processId, ProcessSequence tbAceProSeq) {
        tbAceProSeq.setPkTbId(processId);
        if (processId == null || !tbAceProSeqRepository.existsById(processId)) {
            return Optional.empty();
        }
        tbAceProSeqRepository.save(tbAceProSeq);
        return Optional.of(tbAceProSeq);
    }

    @Autowired private ProcessSequenceRepository tbAceProSeqRepository;

}
