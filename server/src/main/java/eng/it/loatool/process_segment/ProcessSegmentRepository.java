package eng.it.loatool.process_segment;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSegmentRepository extends CrudRepository<ProcessSegment, Integer> {

    @Query("from ProcessSegment p where p.varProSeqId = :varId")
    Optional<ProcessSegment> findByVarId(@Param("varId") String varId);

}
