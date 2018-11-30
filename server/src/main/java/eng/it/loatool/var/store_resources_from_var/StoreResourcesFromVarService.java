package eng.it.loatool.var.store_resources_from_var;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.resource.Resource;
import eng.it.loatool.resource.ResourceRepository;
import eng.it.loatool.var.service.VARServiceWrapper;

@Service
public class StoreResourcesFromVarService {

    @Transactional
    public void execute() throws Exception {
        VARServiceWrapper.getResources().forEach((individual) -> {
            resourceRepository
                .getResourceByName(individual.getName())
                .map((resource) -> {
                    try {
                        Resource newResource = (new VarToNativeResourceTransformer())
                            .transform(individual);
                        resource.assimilateVarInstance(newResource);
                        return resourceRepository.save(resource);
                    } catch (Throwable t) {
                        logger.error("Couldn't handle VAR resource.", t);
                        return null;
                    }
                })
                .orElseGet(
                    () -> {
                        try {
                            Resource newResource = (new VarToNativeResourceTransformer())
                                .transform(individual);
                            return resourceRepository.save(newResource);
                        } catch (Throwable t) {
                            logger.error("Couldn't handle VAR resource", t);
                            return null;
                        }
                    }
                );
        });
    }

    @Autowired private ResourceRepository resourceRepository;
    private Logger logger = LoggerFactory.getLogger(StoreResourcesFromVarService.class);

}
