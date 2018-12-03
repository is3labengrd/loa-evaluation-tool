package eng.it.loatool.minimal_satisfaction;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MinimalSatisfactionRepository extends JpaRepository<MinimalSatisfaction, Integer>{

    @Query("from MinimalSatisfaction where fkTbAceProSeq=:processId")
    public Optional<MinimalSatisfaction> getByProcessId(@Param("processId") Integer processId);

}
