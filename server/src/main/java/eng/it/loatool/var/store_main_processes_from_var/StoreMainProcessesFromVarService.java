package eng.it.loatool.var.store_main_processes_from_var;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eng.it.loatool.api.v1.process_segment_list_elements.PersistProcessSegmentListElementService;
import eng.it.loatool.process_segment.ProcessSegment;
import eng.it.loatool.process_segment.ProcessSegmentRepository;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElement;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElementRepository;
import eng.it.loatool.var.bean.MainProcess;
import eng.it.loatool.var.service.VARServiceWrapper;

@Service
public class StoreMainProcessesFromVarService {

    public void storeMainProcessesFromVar() {
        List<MainProcess> mainProcesses = VARServiceWrapper.getProcessesSegmentList();
        for (MainProcess mainProcess: mainProcesses) {
            ProcessSegment processSegment = varToNativeProcessSegmentTransformer
                .transform(mainProcess);
            ProcessSegment savedProcessSegment = processSegmentRepository
                .findByVarId(processSegment.getVarProSeqId())
                .map((segment) -> {
                    processSegment.setPkTbId(segment.getPkTbId());
                    return processSegmentRepository.save(processSegment);
                })
                .orElseGet(() -> {
                    return processSegmentRepository.save(processSegment);
                });
            processSegmentListElementRepository
                .getProcessSegmentListElementWhichHasNoSubprocesses(
                    savedProcessSegment.getPkTbId()
                )
                .orElseGet(() -> {
                    ProcessSegmentListElement elementWithNoSubProcesses = new ProcessSegmentListElement();
                    Integer savedProcessSegmentId = savedProcessSegment.getPkTbId();
                    elementWithNoSubProcesses.setFkTbAceProSeq(savedProcessSegmentId);
                    persistProcessSegmentListElementService.createProcessSegmentListElement(elementWithNoSubProcesses);
                    return null;
                });
        }
    }

    @Autowired
    StoreMainProcessesFromVarService(
        ProcessSegmentRepository processSegmentRepository,
        VarToNativeProcessSegmentTransformer varToNativeProcessSegmentTransformer,
        ProcessSegmentListElementRepository processSegmentListElementRepository,
        PersistProcessSegmentListElementService persistProcessSegmentListElementService
    ) {
        this.processSegmentRepository = processSegmentRepository;
        this.varToNativeProcessSegmentTransformer = varToNativeProcessSegmentTransformer;
        this.processSegmentListElementRepository = processSegmentListElementRepository;
        this.persistProcessSegmentListElementService = persistProcessSegmentListElementService;
    }

    private ProcessSegmentRepository processSegmentRepository;
    private VarToNativeProcessSegmentTransformer varToNativeProcessSegmentTransformer;
    private ProcessSegmentListElementRepository processSegmentListElementRepository;
    private PersistProcessSegmentListElementService persistProcessSegmentListElementService;

}
