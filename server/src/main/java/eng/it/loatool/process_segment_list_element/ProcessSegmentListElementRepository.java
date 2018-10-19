package eng.it.loatool.process_segment_list_element;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSegmentListElementRepository extends PagingAndSortingRepository<ProcessSegmentListElement, Integer> {
}
