package eng.it.loatool.api.v1;

import eng.it.loatool.var.service.VARServiceWrapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VARApi {

    @RequestMapping("/v1/var/process-segments")
    public String getVarProcesses() {
        return VARServiceWrapper.getProcessesSegmentList();
    }
}
