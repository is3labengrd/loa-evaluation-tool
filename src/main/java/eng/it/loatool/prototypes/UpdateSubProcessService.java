package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.entities.TbAceSubProLev;

@Service
public class UpdateSubProcessService {

    @Transactional
    public TbAceSubProLev updateSubProcess(Integer processId, TbAceSubProLev tbAceSubProLev) {
        tbAceSubProLev.setPkTbId(processId);
        tbAceSubProLevRepository.save(tbAceSubProLev);
        return tbAceSubProLev;
    }

    @Autowired private TbAceSubProLevRepository tbAceSubProLevRepository;

}
