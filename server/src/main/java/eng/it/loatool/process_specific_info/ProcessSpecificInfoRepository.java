package eng.it.loatool.process_specific_info;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSpecificInfoRepository extends CrudRepository<ProcessesSpecificInformation, Integer> {

    @Query("from ProcessesSpecificInformation where fkTbAceSubProLev=:subprocessId")
    public Optional<ProcessesSpecificInformation> getProcessSpecificInfoBySubProcessId(@Param("subprocessId") Integer subprocessId);

}
