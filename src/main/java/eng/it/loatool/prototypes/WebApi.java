package eng.it.loatool.prototypes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebApi {

    @GetMapping("/proto/process-list")
    public ResponseEntity<?> getProcessList() {
        return ResponseEntity.ok(getProcessListService.getProcessList());
    }

    @Autowired private GetProcessListService getProcessListService;

}
