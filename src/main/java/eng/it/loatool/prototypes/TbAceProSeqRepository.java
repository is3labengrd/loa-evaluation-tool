package eng.it.loatool.prototypes;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import eng.it.loatool.entities.TbAceProSeq;

@Repository
public interface TbAceProSeqRepository extends CrudRepository<TbAceProSeq, Integer> {
}
