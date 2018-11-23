package eng.it.loatool.product_planning;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProductPlanningByProcessSegmentId {

    public Optional<ProductPlanning> getOne(int processSegmentId) {
        return productPlanningRepository.getByProcessSegmentId(processSegmentId);
    }

    @Autowired ProductPlanningRepository productPlanningRepository;

}
