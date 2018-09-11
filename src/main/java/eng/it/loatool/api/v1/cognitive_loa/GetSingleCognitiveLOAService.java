package eng.it.loatool.api.v1.cognitive_loa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.cognitive_loa.CognitiveLOA;
import eng.it.loatool.cognitive_loa.CognitiveLOARepository;

@Service
public class GetSingleCognitiveLOAService {

    @Transactional
    public Optional<CognitiveLOA> getSingleCognitiveLOA(Integer id) {
        return cognitiveLOARepository.findById(id);
    }

    @Autowired private CognitiveLOARepository cognitiveLOARepository;

}
