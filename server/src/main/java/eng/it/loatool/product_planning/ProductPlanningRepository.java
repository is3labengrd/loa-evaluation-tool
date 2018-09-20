package eng.it.loatool.product_planning;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPlanningRepository extends CrudRepository<ProductPlanning, Integer> {
}
