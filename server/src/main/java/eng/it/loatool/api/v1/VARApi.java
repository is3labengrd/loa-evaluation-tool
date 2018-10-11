package eng.it.loatool.api.v1;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import eng.it.loatool.var.service.VARServiceWrapper;

import java.io.IOException;

@RestController
public class VARApi {

    @RequestMapping("/v1/var/process-segments")
    public Object getVarProcessSegmentList() {
        return VARServiceWrapper.getProcessesSegmentList();
    }

    @RequestMapping(value = "/v1/var/addResource", method = RequestMethod.POST)
    public void addResource (@RequestBody String json) throws IOException {

        VARServiceWrapper.addResource(json);
    }

    @RequestMapping(value = "/v1/var/editResource", method = RequestMethod.PUT)
    public void editResource (@RequestBody String json) throws IOException {

        VARServiceWrapper.editResource(json);
    }

    @RequestMapping(value = "/v1/var/editProductPlanning", method = RequestMethod.PUT)
    public void editProductPlanning (@RequestBody String json) throws IOException {

        VARServiceWrapper.editProductPlanning(json);
    }

    @RequestMapping(value = "/v1/var/editProcessSpecificInformation", method = RequestMethod.PUT)
    public void editProcessSpecificInformation (@RequestBody String json) throws IOException {

        VARServiceWrapper.editProcessSpecificInformation(json);
    }

    @RequestMapping(value = "/v1/var/editProportionalWageCost", method = RequestMethod.PUT)
    public void editProportionalWageCost (@RequestBody String json) throws IOException {

        VARServiceWrapper.editProportionalWageCost(json);
    }
}
