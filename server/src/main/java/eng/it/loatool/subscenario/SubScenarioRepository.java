package eng.it.loatool.subscenario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubScenarioRepository extends JpaRepository<SubScenario, Integer> {
}
