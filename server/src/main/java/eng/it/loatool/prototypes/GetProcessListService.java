package eng.it.loatool.prototypes;

import org.springframework.stereotype.Service;

@Service
public class GetProcessListService {

    public GetProcessListService() {
        this.processList = new ProcessList();
    }

    public ProcessList getProcessList() {
        return this.processList;
    }

    private ProcessList processList;

}
