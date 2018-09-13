package eng.it.loatool.api.v1.cognitive_loa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.cognitive_loa.CognitiveLOA;
import eng.it.loatool.cognitive_loa.CognitiveLOARepository;

@Service
public class UpdateCognitiveLOAService {

    @Transactional
    public Optional<CognitiveLOA> updateCognitiveLOA(Integer id, CognitiveLOA cognitiveLOA) {
        cognitiveLOA.setPkTbId(id);
        if (id == null || !cognitiveLOARepository.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(cognitiveLOARepository.save(cognitiveLOA));
    }

    @Autowired private CognitiveLOARepository cognitiveLOARepository;

}
