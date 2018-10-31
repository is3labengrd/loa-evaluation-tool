package eng.it.loatool.physical_loa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class GetSinglePhysicalLOAService {

    @Transactional
    public Optional<PhysicalLOA> getSinglePhysicalLOA(Integer id) {
        return physicalLOARepository.findById(id);
    }

    @Autowired private PhysicalLOARepository physicalLOARepository;

}
