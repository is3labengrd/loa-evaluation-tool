package eng.it.loatool.api.v1.process_specific_info;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.process_specific_info.ProcessSpecificInfoRepository;

@Service
public class GetProcessSpecificInfoBySubProcessId {

    public Optional getOne(int subprocessId) {
        return processSpecificInfoRepository
            .getProcessSpecificInfoBySubProcessId(subprocessId);
    }

    @Autowired ProcessSpecificInfoRepository processSpecificInfoRepository;

}
