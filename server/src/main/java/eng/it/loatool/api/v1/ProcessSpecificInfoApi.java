package eng.it.loatool.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.process_specific_info.GetProcessSpecificInfoBySubProcessId;
import eng.it.loatool.process_specific_info.ProcessSpecificInfoRepository;
import eng.it.loatool.process_specific_info.ProcessesSpecificInformation;

@Controller
public class ProcessSpecificInfoApi {

    @GetMapping("/v1/process-specific-info")
    public ResponseEntity<?> getAllProcessSpecificInfo() {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(processSpecificInfoRepository)
        );
    }

    @GetMapping("/v1/process-specific-info/{id}")
    public ResponseEntity<?> getProcessSpecificInfo(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(processSpecificInfoRepository, id)
        );
    }

    @GetMapping("/v1/process-specific-info-by-subprocess-id/{id}")
    public ResponseEntity<?> getProcessSpecificInfoBySubProcessId(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            getProcessSpecificInfoBySubProcessId.getOne(id)
        );
    }

    @PostMapping("/v1/process-specific-info")
    public ResponseEntity<?> createProcessSpecificInfo(@RequestBody ProcessesSpecificInformation body) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.create(processSpecificInfoRepository, body)
        );
    }

    @PutMapping("/v1/process-specific-info/{id}")
    public ResponseEntity<?> updateProcessSpecificInfo(
        @PathVariable("id") Integer id,
        @RequestBody ProcessesSpecificInformation body
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.update(processSpecificInfoRepository, id, body)
        );
    }

    @Autowired private GetProcessSpecificInfoBySubProcessId getProcessSpecificInfoBySubProcessId;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ProcessSpecificInfoRepository processSpecificInfoRepository;

}
