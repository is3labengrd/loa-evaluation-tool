package eng.it.loatool.subprocess_level_resource;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SubProcessLevelResourceRepository extends CrudRepository<SubProcessLevelResource, Integer> {

    @Query("from SubProcessLevelResource where subprocessLevel.pkTbId=:subprocessId")
    public Iterable<SubProcessLevelResource> getSubProcessLevelResourcebySubProcessId(@Param("subprocessId") Integer subprocessId);

}
