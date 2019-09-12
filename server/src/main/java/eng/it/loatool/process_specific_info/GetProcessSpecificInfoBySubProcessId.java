package eng.it.loatool.process_specific_info;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetProcessSpecificInfoBySubProcessId {

    public Optional getOne(int subprocessId) {
        return processSpecificInfoRepository
            .getProcessSpecificInfoBySubProcessId(subprocessId);
    }

    public List getAll(int subprocessId) {
	return processSpecificInfoRepository
	    .getAllProcessSpecificInfoBySubProcessId(subprocessId);
    }

    @Autowired ProcessSpecificInfoRepository processSpecificInfoRepository;

}
