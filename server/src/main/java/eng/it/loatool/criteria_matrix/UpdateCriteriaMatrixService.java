package eng.it.loatool.criteria_matrix;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UpdateCriteriaMatrixService {

    @Transactional
    public Optional<CriteriaMatrix> updateCriteriaMatrix(Integer matrixId, CriteriaMatrix criteriaMatrix) {
        criteriaMatrix.setPkTbId(matrixId);
        if (criteriaMatrix == null || !criteriaMatrixRepository.existsById(matrixId)) {
            return Optional.empty();
        }
        return Optional.of(criteriaMatrixRepository.save(criteriaMatrix));
    }

    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;

}
