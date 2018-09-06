package eng.it.loatool.prototypes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.entities.TbAceProSeq;

@Service
public class GetProcessService {

    @Transactional
    public Optional<TbAceProSeq> getProcess(Integer processId) {
        return tbAceProSeqRepository.findById(processId);
    }

    @Autowired private TbAceProSeqRepository tbAceProSeqRepository;

}
