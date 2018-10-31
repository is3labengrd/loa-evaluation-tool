package eng.it.loatool.criteria_matrix;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class GetCriteriaMatrixService {

    @Transactional
    public Optional<CriteriaMatrix> getCriteriaMatrix(Integer matrixId) {
        return criteriaMatrixRepository.findById(matrixId);
    }

    @Transactional
    public Optional<CriteriaMatrix> getCriteriaMatrixBySubprocessId(Integer id) {
        return criteriaMatrixRepository.getCriteriaMatrixBySubprocessLevel(id);
    }

    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;

}
