package eng.it.loatool.api.v1.process_sequences;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_sequence.ProcessSequence;
import eng.it.loatool.process_sequence.ProcessSequenceRepository;

@Service
public class GetProcessSequencesService {

    @Transactional
    Iterable<ProcessSequence> getProcessSequences() {
        return tbAceProSeqRepository.findAll();
    }

    @Autowired private ProcessSequenceRepository tbAceProSeqRepository;

}
