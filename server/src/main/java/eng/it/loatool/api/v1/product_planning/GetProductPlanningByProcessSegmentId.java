package eng.it.loatool.api.v1.product_planning;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.product_planning.ProductPlanningRepository;

@Service
public class GetProductPlanningByProcessSegmentId {

    public Optional getOne(int processSegmentId) {
        return productPlanningRepository.getByProcessSegmentId(processSegmentId);
    }

    @Autowired ProductPlanningRepository productPlanningRepository;

}
