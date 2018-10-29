package eng.it.loatool.product_planning;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPlanningRepository extends CrudRepository<ProductPlanning, Integer> {

    @Query("from ProductPlanning where fkTbAceProSeq=:processSegmentId")
    Optional<ProductPlanning> getByProcessSegmentId(@Param("processSegmentId") Integer processSegmentId);

}
