package eng.it.loatool.cognitive_loa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetSingleCognitiveLOAService {

    @Transactional
    public Optional<CognitiveLOA> getSingleCognitiveLOA(Integer id) {
        return cognitiveLOARepository.findById(id);
    }

    @Autowired private CognitiveLOARepository cognitiveLOARepository;

}
