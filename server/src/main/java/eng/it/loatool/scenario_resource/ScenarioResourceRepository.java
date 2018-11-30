package eng.it.loatool.scenario_resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioResourceRepository extends JpaRepository<ScenarioResource, Integer> {

    @Query("from ScenarioResource where fkTbAceSubProLev=:subprocessId")
    public Iterable<ScenarioResource> getScenarioResourcebySubProcessId(@Param("subprocessId") Integer subprocessId);

    @Query("from ScenarioResource where fkTbAceRes=:resourceId")
    public Iterable<ScenarioResource> getScenarioResourcesbyResourceId(@Param("resourceId") Integer resourceId);

}
