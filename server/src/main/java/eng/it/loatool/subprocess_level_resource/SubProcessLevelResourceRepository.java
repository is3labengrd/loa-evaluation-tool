package eng.it.loatool.subprocess_level_resource;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProcessLevelResourceRepository extends CrudRepository<SubProcessLevelResource, Integer> {
}
