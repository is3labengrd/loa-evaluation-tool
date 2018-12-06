package eng.it.loatool.api.v1;

import eng.it.loatool.var.request.VARWorkUnitImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eng.it.loatool.var.service.VARServiceWrapper;
import eng.it.loatool.var.store_main_processes_from_var.StoreMainProcessesFromVarService;
import eng.it.loatool.var.store_resources_from_var.StoreResourcesFromVarService;

import java.io.IOException;

@RestController
public class VARApi {

    @RequestMapping("/v1/var/process-segments")
    public Object getVarProcessSegmentList() throws Exception {
        return VARServiceWrapper.getProcessesSegmentList();
    }
    
    @RequestMapping(value = "/v1/var/addResource", method = RequestMethod.POST)
    public void addResource (@RequestBody String json) throws Exception {

        VARServiceWrapper.addResource(json);
    }

    @RequestMapping(value = "/v1/var/editResource", method = RequestMethod.PUT)
    public String editResource (@RequestBody String json) throws Exception {

        VARServiceWrapper.editResource(json);
        return null;
    }

    @RequestMapping(value = "/v1/var/DeleteResource/{name}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("name") String resName) throws IOException {
        VARWorkUnitImpl.delete(resName);
    }

    @RequestMapping("/v1/var/SiteInfo")
    public Object SiteInfo() throws Exception {
        return VARServiceWrapper.getSite();
    }

    @RequestMapping(value = "/v1/var/editProductPlanning", method = RequestMethod.PUT)
    public void editProductPlanning (@RequestBody String json) throws Exception {

        VARServiceWrapper.editProductPlanning(json);
    }

    @RequestMapping(value = "/v1/var/editProcessSpecificInformation", method = RequestMethod.PUT)
    public void editProcessSpecificInformation (@RequestBody String json) throws Exception {

        VARServiceWrapper.editProcessSpecificInformation(json);
    }

    @RequestMapping(value = "/v1/var/editProportionalWageCost", method = RequestMethod.PUT)
    public void editProportionalWageCost (@RequestBody String json) throws Exception {

        VARServiceWrapper.editProportionalWageCost(json);
    }

    @RequestMapping("/v1/var/ProductPlanning/{name}")
    public String getProductPlanningIntance(@PathVariable("name") String name) throws Exception {
        return VARServiceWrapper.getProductPlanningIntance(name);
    }

    @PostMapping("/v1/var/populate-process-segments")
    public void populateProcessSegments() throws Exception {
        storeMainProcessesService.storeMainProcessesFromVar();
    }

    @PostMapping("/v1/var/populate-resources")
    public void populateResources() throws Exception {
        storeResourcesFromVarService.execute();
    }

    @Autowired private StoreMainProcessesFromVarService storeMainProcessesService;
    @Autowired private StoreResourcesFromVarService storeResourcesFromVarService;

}
