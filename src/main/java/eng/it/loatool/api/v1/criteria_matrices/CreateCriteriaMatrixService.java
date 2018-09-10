package eng.it.loatool.api.v1.criteria_matrices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.criteria_matrix.CriteriaMatrix;
import eng.it.loatool.criteria_matrix.CriteriaMatrixRepository;

public class CreateCriteriaMatrixService {

    @Transactional
    public Optional<CriteriaMatrix> CreateCriteriaMatrix(CriteriaMatrix criteriaMatrix) {
        if (
            criteriaMatrix.getPkTbId() == null ||
            !criteriaMatrixRepository.existsById(criteriaMatrix.getPkTbId())
        ) {
            criteriaMatrixRepository.save(criteriaMatrix);
            return Optional.of(criteriaMatrix);
        }
        return Optional.empty();
    }

    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;

}
