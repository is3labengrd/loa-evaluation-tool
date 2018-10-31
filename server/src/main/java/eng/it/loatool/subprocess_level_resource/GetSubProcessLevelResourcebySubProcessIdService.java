package eng.it.loatool.subprocess_level_resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetSubProcessLevelResourcebySubProcessIdService {

    public Iterable<SubProcessLevelResource> getBySubProcessId(Integer subprocessId) {
        return subProcessLevelResourceRepository.getSubProcessLevelResourcebySubProcessId(subprocessId);
    }

    @Autowired SubProcessLevelResourceRepository subProcessLevelResourceRepository;

}
