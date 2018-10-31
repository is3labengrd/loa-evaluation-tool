package eng.it.loatool.physical_loa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetAllPhysicalLOAService {

    @Transactional
    public Iterable<PhysicalLOA> getAllPhysicalLOA() {
        return physicalLOARepository.findAll();
    }

    @Autowired private PhysicalLOARepository physicalLOARepository;

}
