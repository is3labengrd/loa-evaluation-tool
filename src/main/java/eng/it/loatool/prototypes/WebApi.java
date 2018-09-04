package eng.it.loatool.prototypes;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebApi {

	@GetMapping("/proto/process-list")
	public ResponseEntity<ProcessList> getProcessList() {
		return ResponseEntity.ok(
			ProcessList.getExample()
		);
	}

	@GetMapping("/proto/test")
	public ResponseEntity<String> test() {
		return ResponseEntity.ok("Test");
	}

}
