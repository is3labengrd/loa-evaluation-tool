package eng.it.loatool.api.v1.cognitive_loa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.cognitive_loa.CognitiveLOA;
import eng.it.loatool.cognitive_loa.CognitiveLOARepository;

@Service
public class GetAllCognitiveLOAService {

    @Transactional
    Iterable<CognitiveLOA> getAllCognitiveLOA() {
        return cognitiveLOARepository.findAll();
    }

    @Autowired private CognitiveLOARepository cognitiveLOARepository;

}
