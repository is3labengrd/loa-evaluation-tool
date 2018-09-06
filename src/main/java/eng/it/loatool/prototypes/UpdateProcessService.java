package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.entities.TbAceProSeq;

@Service
public class UpdateProcessService {

    @Transactional
    public TbAceProSeq updateProcess(Integer processId, TbAceProSeq tbAceProSeq) {
        tbAceProSeq.setPkTbId(processId);
        tbAceProSeqRepository.save(tbAceProSeq);
        return tbAceProSeq;
    }

    @Autowired private TbAceProSeqRepository tbAceProSeqRepository;

}
