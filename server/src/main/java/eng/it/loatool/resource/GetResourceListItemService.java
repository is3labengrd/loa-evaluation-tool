package eng.it.loatool.resource;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class GetResourceListItemService {

    @Transactional
    public Iterable getAllBySubprocessId(int subprocessLevelId, int page, int size) {
        if (page < 0 || size < 1) {
            return resourceRepository.getResourceItemsBySubprocessId(subprocessLevelId);
        }
        return resourceRepository.getResourceItemsBySubprocessId(subprocessLevelId, PageRequest.of(page, size));
    }

    @Autowired private ResourceRepository resourceRepository;

}
