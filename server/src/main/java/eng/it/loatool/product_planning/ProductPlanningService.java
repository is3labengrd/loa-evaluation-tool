package eng.it.loatool.product_planning;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductPlanningService {

	public Optional<ProductPlanning> create(ProductPlanning productPlanning) {

		Optional<ProductPlanning> optional = productPlanningRepository.getByProcessSegmentId(productPlanning.getFkTbAceProSeq());
		
		if (optional.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.of(productPlanningRepository.save(productPlanning));
		}

	}

	@Autowired
	ProductPlanningRepository productPlanningRepository;

}
