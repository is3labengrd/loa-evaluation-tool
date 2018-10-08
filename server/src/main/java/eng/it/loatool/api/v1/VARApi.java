package eng.it.loatool.api.v1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import eng.it.loatool.var.service.VARServiceWrapper;

@RestController
public class VARApi {

    @RequestMapping("/v1/var/process-segments")
    public Object getVarProcessSegmentList() {
        return VARServiceWrapper.getProcessesSegmentList();
    }
}
