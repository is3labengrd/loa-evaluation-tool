package eng.it.loatool.cognitive_loa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetAllCognitiveLOAService {

    @Transactional
    public Iterable<CognitiveLOA> getAllCognitiveLOA() {
        return cognitiveLOARepository.findAll();
    }

    @Autowired private CognitiveLOARepository cognitiveLOARepository;

}
