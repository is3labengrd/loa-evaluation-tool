package eng.it.loatool.minimal_satisfaction;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;

@Service
public class CreateMinimalSatisfactionService {

    @Transactional
    public Optional<MinimalSatisfaction> create(
        MinimalSatisfaction minimalSatisfaction
    ) {
        if (
            getMinimalSatisfactionService
                .getBySubProcessLevelId(minimalSatisfaction.getFkTbAceSubProLev())
                .isPresent()
        ) {
            return Optional.empty();
        } else {
            return generalCRUDService.create(
                minimalSatisfactionRepository,
                minimalSatisfaction
            );
        }

    }

    @Autowired private GetMinimalSatisfactionService getMinimalSatisfactionService;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private MinimalSatisfactionRepository minimalSatisfactionRepository;

}
