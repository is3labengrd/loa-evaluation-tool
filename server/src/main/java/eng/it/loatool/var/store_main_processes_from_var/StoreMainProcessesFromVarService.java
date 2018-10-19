package eng.it.loatool.var.store_main_processes_from_var;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.process_segment.ProcessSegment;
import eng.it.loatool.process_segment.ProcessSegmentRepository;
import eng.it.loatool.var.bean.MainProcess;
import eng.it.loatool.var.service.VARServiceWrapper;

@Service
public class StoreMainProcessesFromVarService {

    public void storeMainProcessesFromVar() {
        List<MainProcess> mainProcesses = VARServiceWrapper.getProcessesSegmentList();
        for (MainProcess mainProcess: mainProcesses) {
            ProcessSegment processSegment = varToNativeProcessSegmentTransformer
                .transform(mainProcess);
            processSegmentRepository.findByVarId(processSegment.getVarProSeqId())
                .map((segment) -> {
                    processSegment.setPkTbId(segment.getPkTbId());
                    processSegmentRepository.save(processSegment);
                    return null;
                })
                .orElseGet(()->{
                    processSegmentRepository.save(processSegment);
                    return null;
                });
        }
    }

    @Autowired
    StoreMainProcessesFromVarService(
        ProcessSegmentRepository processSegmentRepository,
        VarToNativeProcessSegmentTransformer varToNativeProcessSegmentTransformer
    ) {
        this.processSegmentRepository = processSegmentRepository;
        this.varToNativeProcessSegmentTransformer = varToNativeProcessSegmentTransformer;
    }

    private ProcessSegmentRepository processSegmentRepository;
    private VarToNativeProcessSegmentTransformer varToNativeProcessSegmentTransformer;

}
