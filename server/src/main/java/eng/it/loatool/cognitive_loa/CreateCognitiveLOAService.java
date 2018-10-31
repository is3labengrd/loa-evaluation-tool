package eng.it.loatool.cognitive_loa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCognitiveLOAService {

    @Transactional
    public Optional<CognitiveLOA> createCognitiveLOA(CognitiveLOA cognitiveLOA) {
        if (
            cognitiveLOA.getPkTbId() == null ||
            !cognitiveLOARepository.existsById(cognitiveLOA.getPkTbId())
        ) {
            return Optional.of(cognitiveLOARepository.save(cognitiveLOA));
        }
        return Optional.empty();
    }

    @Autowired private CognitiveLOARepository cognitiveLOARepository;

}
