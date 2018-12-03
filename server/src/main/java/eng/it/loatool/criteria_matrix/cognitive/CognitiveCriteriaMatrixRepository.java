package eng.it.loatool.criteria_matrix.cognitive;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CognitiveCriteriaMatrixRepository extends JpaRepository<CognitiveCriteriaMatrix, Integer>{

    @Query("from CognitiveCriteriaMatrix where fkTbAceSubProLev=:subprocessId")
    public Optional<CognitiveCriteriaMatrix> getBySubprocessLevel(@Param("subprocessId") Integer subprocessId);

}
