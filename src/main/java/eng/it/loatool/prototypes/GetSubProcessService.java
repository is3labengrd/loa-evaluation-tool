package eng.it.loatool.prototypes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.entities.TbAceSubProLev;

@Service
public class GetSubProcessService {

    @Transactional
    public Optional<TbAceSubProLev> getSubProcess(Integer processId) {
        return tbAceSubProLevRepository.findById(processId);
    }

    @Autowired private TbAceSubProLevRepository tbAceSubProLevRepository;

}
