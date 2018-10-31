package eng.it.loatool.physical_loa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreatePhysicalLOAService {

    public Optional<PhysicalLOA> createPhysicalLOA(PhysicalLOA physicalLOA) {
        if (
            physicalLOA.getPkTbId() == null ||
            !physicalLOARepository.existsById(physicalLOA.getPkTbId())
        ) {
            return Optional.of(physicalLOARepository.save(physicalLOA));
        }
        return Optional.empty();
    }

    @Autowired private PhysicalLOARepository physicalLOARepository;

}
