package eng.it.loatool.api.v1.criteria_matrices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.criteria_matrix.CriteriaMatrix;
import eng.it.loatool.criteria_matrix.CriteriaMatrixRepository;

@Service
public class GetCriteriaMatricesService {

    @Transactional
    public Iterable<CriteriaMatrix> getCriteriaMatrices() {
        return criteriaMatrixRepository.findAll();
    }

    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;

}
