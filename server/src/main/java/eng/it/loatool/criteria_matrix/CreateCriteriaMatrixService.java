package eng.it.loatool.criteria_matrix;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CreateCriteriaMatrixService {

    @Transactional
    public Optional<CriteriaMatrix> createCriteriaMatrix(CriteriaMatrix criteriaMatrix) {
        if (
            criteriaMatrix.getPkTbId() == null ||
            !criteriaMatrixRepository.existsById(criteriaMatrix.getPkTbId())
        ) {
            return Optional.of(criteriaMatrixRepository.save(criteriaMatrix));
        }
        return Optional.empty();
    }

    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;

}
