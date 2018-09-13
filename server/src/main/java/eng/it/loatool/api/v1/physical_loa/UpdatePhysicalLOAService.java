package eng.it.loatool.api.v1.physical_loa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.physical_loa.PhysicalLOA;
import eng.it.loatool.physical_loa.PhysicalLOARepository;

@Service
public class UpdatePhysicalLOAService {

    @Transactional
    public Optional<PhysicalLOA> updatePhysicalLOA(Integer id, PhysicalLOA physicalLOA) {
        physicalLOA.setPkTbId(id);
        if (id == null || !physicalLOARepository.existsById(id)) {
            return Optional.empty();
        }
        return Optional.of(physicalLOARepository.save(physicalLOA));
    }

    @Autowired private PhysicalLOARepository physicalLOARepository;

}
