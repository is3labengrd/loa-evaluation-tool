package eng.it.loatool.process_segment_list_element;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSegmentListElementRepository extends PagingAndSortingRepository<ProcessSegmentListElement, Integer> {

    @Query(
        " from ProcessSegmentListElement p" +
        " where" +
        "   p.mainProcess.pkTbId = :mainProcessId and" +
        "   p.subProcessLevel1 = null and" +
        "   p.subProcessLevel2 = null and" +
        "   p.subProcessLevel3 = null and" +
        "   p.subProcessLevel4 = null and" +
        "   p.subProcessLevel5 = null"
    )
    Optional<ProcessSegmentListElement> getProcessSegmentListElementWhichHasNoSubprocesses(@Param("mainProcessId") Integer mainProcessId);

}
