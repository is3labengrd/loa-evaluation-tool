package eng.it.loatool.process_loa_info;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessLOAInformationRepository extends CrudRepository<ProcessLOAInformation, Integer>{
}
