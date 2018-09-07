package eng.it.loatool.api.v1.process_sequences;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_sequence.ProcessSequence;
import eng.it.loatool.process_sequence.ProcessSequenceRepository;

@Service
public class GetProcessService {

    @Transactional
    public Optional<ProcessSequence> getProcess(Integer processId) {
        return tbAceProSeqRepository.findById(processId);
    }

    @Autowired private ProcessSequenceRepository tbAceProSeqRepository;

}
