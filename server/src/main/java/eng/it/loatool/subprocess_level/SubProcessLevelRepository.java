package eng.it.loatool.subprocess_level;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import eng.it.loatool.process_loa_info.ProcessLOAInformation;

@Repository
public interface SubProcessLevelRepository extends PagingAndSortingRepository<SubProcessLevel, Integer> {

    @Query(
    "   select info" +
    "   from" +
    "       ProcessLOAInformation as info" +
    "   where " +
    "       :subprocessId = info.fkTbAceSubProLev"
    )
    Optional<ProcessLOAInformation> getSubProcessInfo(@Param("subprocessId") Integer subprocessId);

}
