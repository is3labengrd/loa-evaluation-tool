package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.entities.TbAceProSeq;

@Service
public class GetProcessesService {

    Iterable<TbAceProSeq> getProcesses() {
        return tbAceProSeqRepository.findAll();
    }

    @Autowired
    private TbAceProSeqRepository tbAceProSeqRepository;

}
