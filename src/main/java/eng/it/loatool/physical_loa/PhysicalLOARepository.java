package eng.it.loatool.physical_loa;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhysicalLOARepository extends CrudRepository<PhysicalLOA, Integer> {
}
