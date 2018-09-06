package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.entities.TbAceProSeq;

@Service
public class GetProcessesService {

    @Transactional
    Iterable<TbAceProSeq> getProcesses() {
        return tbAceProSeqRepository.findAll();
    }

    @Autowired private TbAceProSeqRepository tbAceProSeqRepository;

}
