package eng.it.loatool.prototypes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import eng.it.loatool.entities.TbAceSubProLev;

@Repository
public interface TbAceSubProLevRepository extends CrudRepository<TbAceSubProLev, Integer> {
}
