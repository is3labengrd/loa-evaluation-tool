package eng.it.loatool.process_segment;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.subprocess_level.GetSubProcessLevelsService;

@Service
public class GetProcessSegmentService {

    @Transactional
    public Optional<ProcessSegment> getProcessSegment(Integer processId) {
        return refineProcessSegment(tbAceProSeqRepository.findById(processId));
    }

    @Transactional
    public Optional<ProcessSegment> refineProcessSegment(
        Optional<ProcessSegment> maybeProcessSegment
    ) {
        maybeProcessSegment.ifPresent((segment) -> {
            getSubProcessLevelsService.addInfoToSubprocessLevels((segment.getSubprocessLevels()));
        });
        return maybeProcessSegment;
    }

    @Autowired private ProcessSegmentRepository tbAceProSeqRepository;
    @Autowired private GetSubProcessLevelsService getSubProcessLevelsService;

}
