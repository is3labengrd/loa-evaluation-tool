package eng.it.loatool.minimal_satisfaction;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetMinimalSatisfactionService {

    public Optional<MinimalSatisfaction> getByProcessId(Integer processId) {
        return minimalSatisfactionRepository.getByProcessId(processId);
    }

    public Optional<MinimalSatisfaction> getBySubProcessLevelId(Integer subprocessId) {
        return minimalSatisfactionRepository.getBySubProcessLevelId(subprocessId);
    }

    @Autowired private MinimalSatisfactionRepository minimalSatisfactionRepository;

}
