package eng.it.loatool.process_specific_info;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSpecificInfoRepository extends CrudRepository<ProcessesSpecificInformation, Integer> {

    @Query("from ProcessesSpecificInformation where fkTbAceSubProLev=:subprocessId")
    public Optional<ProcessesSpecificInformation> getProcessSpecificInfoBySubProcessId(@Param("subprocessId") Integer subprocessId);

    @Query(
        " select info" +
        " from" +
        "     ProcessesSpecificInformation info join SubProcessLevel level on info.fkTbAceSubProLev = level.pkTbId" +
        " where" +
        "     level in (" +
        "         select levelInner" +
        "         from SubProcessLevel levelInner" +
        "         where levelInner.fkTbAceProSeq = (" +
        "             select segment.pkTbId" +
        "             from ProcessSegment as segment join segment.subprocessLevels as levelInnerInner" +
        "             where levelInnerInner.pkTbId = :subprocessId)" +
        "     )"
    )
    public List<ProcessesSpecificInformation> getAllProcessSpecificInfoBySubProcessId(@Param("subprocessId") Integer subprocessId);

}
