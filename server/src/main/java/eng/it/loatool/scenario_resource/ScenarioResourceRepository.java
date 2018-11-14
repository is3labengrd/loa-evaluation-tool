package eng.it.loatool.scenario_resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioResourceRepository extends JpaRepository<ScenarioResource, Integer> {
}
