package eng.it.loatool.api.v1.physical_loa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.physical_loa.PhysicalLOA;
import eng.it.loatool.physical_loa.PhysicalLOARepository;

@Service
public class GetAllPhysicalLOAService {

    @Transactional
    public Iterable<PhysicalLOA> getAllPhysicalLOA() {
        return physicalLOARepository.findAll();
    }

    @Autowired private PhysicalLOARepository physicalLOARepository;

}
