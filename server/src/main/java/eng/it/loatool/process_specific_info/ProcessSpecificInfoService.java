package eng.it.loatool.process_specific_info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProcessSpecificInfoService {

	public Optional<ProcessesSpecificInformation> create(ProcessesSpecificInformation processesSpecificInformation) {

		Optional<ProcessesSpecificInformation> optional = processSpecificInfoRepository
				.getProcessSpecificInfoBySubProcessId(processesSpecificInformation.getFkTbAceSubProLev());

		if (optional.isPresent()) {
			return Optional.empty();
		} else {
			return Optional.of(processSpecificInfoRepository.save(processesSpecificInformation));
		}

	}

	@Autowired
	ProcessSpecificInfoRepository processSpecificInfoRepository;

}
