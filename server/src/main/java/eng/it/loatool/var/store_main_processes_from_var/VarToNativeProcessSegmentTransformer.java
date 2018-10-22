package eng.it.loatool.var.store_main_processes_from_var;

import org.springframework.stereotype.Service;

import eng.it.loatool.process_segment.ProcessSegment;
import eng.it.loatool.var.bean.MainProcess;

@Service
public class VarToNativeProcessSegmentTransformer {

    public ProcessSegment transform(MainProcess mainProcess) {
        ProcessSegment result = new ProcessSegment();
        result.setName(mainProcess.getName());
        result.setNLowerLevelSubPro(mainProcess.getSubProcLowerLevel());
        result.setVarProSeqId(mainProcess.getProcessSegmentId());
        return result;
    }

}
