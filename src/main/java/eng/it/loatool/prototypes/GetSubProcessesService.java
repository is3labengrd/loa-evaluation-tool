package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.entities.TbAceSubProLev;

@Service
public class GetSubProcessesService {

    @Transactional
    Iterable<TbAceSubProLev> getSubProcesses() {
        return tbAceSubProLevRepository.findAll();
    }

    @Autowired private TbAceSubProLevRepository tbAceSubProLevRepository;

}
