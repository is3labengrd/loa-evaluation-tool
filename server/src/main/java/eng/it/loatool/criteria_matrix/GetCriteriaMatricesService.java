package eng.it.loatool.criteria_matrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetCriteriaMatricesService {

    @Transactional
    public Iterable<CriteriaMatrix> getCriteriaMatrices() {
        return criteriaMatrixRepository.findAll();
    }

    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;

}
