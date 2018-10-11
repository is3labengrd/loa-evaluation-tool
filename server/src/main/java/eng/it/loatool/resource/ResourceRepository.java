package eng.it.loatool.resource;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceRepository extends PagingAndSortingRepository<Resource, Integer> {
}
