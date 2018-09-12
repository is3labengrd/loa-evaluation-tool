package eng.it.loatool.api.v1.criteria_matrices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.criteria_matrix.CriteriaMatrix;
import eng.it.loatool.criteria_matrix.CriteriaMatrixRepository;

@Service
public class UpdateCriteriaMatrixService {

    @Transactional
    public Optional<CriteriaMatrix> updateCriteriaMatrix(Integer matrixId, CriteriaMatrix criteriaMatrix) {
        criteriaMatrix.setPkTbId(matrixId);
        if (criteriaMatrix == null || !criteriaMatrixRepository.existsById(matrixId)) {
            return Optional.empty();
        }
        criteriaMatrixRepository.save(criteriaMatrix);
        return Optional.of(criteriaMatrix);
    }

    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;

}
