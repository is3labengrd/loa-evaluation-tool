package eng.it.loatool.process_loa_info;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessLOAInformationRepository extends CrudRepository<ProcessLOAInformation, Integer> {

    @Query("from ProcessLOAInformation where subProcessLevel.pkTbId=:subprocessId and loaType='P'")
    public Optional<ProcessLOAInformation> getPhysicalProcessLOAInformationBySubprocessId(
        @Param("subprocessId") Integer subprocessId
    );

    @Query("from ProcessLOAInformation where subProcessLevel.pkTbId=:subprocessId and loaType='C'")
    public Optional<ProcessLOAInformation> getCognitiveProcessLOAInformationBySubprocessId(
        @Param("subprocessId") Integer subprocessId
    );


}
