package eng.it.loatool.criteria_matrix.cognitive;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetCognitiveCriteriaMatrixService {

    @Transactional
    public Optional<CognitiveCriteriaMatrix> getBySubprocessId(Integer id) {
        return cognitiveCriteriaMatrixRepository.getBySubprocessLevel(id);
    }

    @Autowired private CognitiveCriteriaMatrixRepository cognitiveCriteriaMatrixRepository;

}
