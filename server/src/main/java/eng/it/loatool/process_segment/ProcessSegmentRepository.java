package eng.it.loatool.process_segment;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProcessSegmentRepository extends CrudRepository<ProcessSegment, Integer> {
}
