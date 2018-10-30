package eng.it.loatool.criteria_matrix;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CriteriaMatrixRepository extends CrudRepository<CriteriaMatrix, Integer> {

    @Query("from CriteriaMatrix where fkTbAceSubProLev=:subprocessId")
    public Optional<CriteriaMatrix> getCriteriaMatrixBySubprocessLevel(@Param("subprocessId") Integer subprocessId);

}
