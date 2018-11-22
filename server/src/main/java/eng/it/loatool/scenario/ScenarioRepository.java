package eng.it.loatool.scenario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioRepository extends JpaRepository<Scenario, Integer> {

    @Query("from Scenario where fkTbAceProSeq = :processSegmentId")
    Iterable<Scenario> getScenariosbyProcessSegmentId(@Param("processSegmentId") Integer processSegmentId);

}
