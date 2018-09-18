package eng.it.loatool.process_specific_info;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSpecificInfoRepository extends CrudRepository<ProcessesSpecificInformation, Integer> {
}
