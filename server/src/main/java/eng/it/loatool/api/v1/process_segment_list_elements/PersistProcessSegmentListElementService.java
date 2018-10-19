package eng.it.loatool.api.v1.process_segment_list_elements;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.process_segment.ProcessSegment;
import eng.it.loatool.process_segment.ProcessSegmentRepository;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElement;
import eng.it.loatool.process_segment_list_element.ProcessSegmentListElementRepository;
import eng.it.loatool.subprocess_level.SubProcessLevel;
import eng.it.loatool.subprocess_level.SubProcessLevelRepository;

@Service
public class PersistProcessSegmentListElementService {

    @Transactional
    public Optional<ProcessSegmentListElement> createProcessSegmentListElement(
        ProcessSegmentListElement element
    ) {
        if (
            element.getPkTbId() == null ||
            !processSegmentListElementRepository.existsById(element.getPkTbId())
        ) {
            element = manageRelatedObjects(element);
            return Optional.of(
                processSegmentListElementRepository.save(element)
            );
        }
        return Optional.empty();
    }

    @Transactional
    public Optional<ProcessSegmentListElement> updateProcessSegmentListElement(
        Integer elementId,
        ProcessSegmentListElement element
    ) {
        element.setPkTbId(elementId);
        if (
            element.getPkTbId() == null ||
            !processSegmentListElementRepository.existsById(element.getPkTbId())
        ) {
            return Optional.empty();
        }
        element = manageRelatedObjects(element);
        return Optional.of(
            processSegmentListElementRepository.save(element)
        );
    }

    private ProcessSegmentListElement manageRelatedObjects(ProcessSegmentListElement element) {
        Optional<ProcessSegment> mainProcess = processSegmentRepository
            .findById(zeroIfNull(element.getFkTbAceProSeq()));
        Optional<SubProcessLevel> subProcessLevel1 = subProcessLevelRepository
            .findById(zeroIfNull(element.getFkTbAceSubProLev1()));
        Optional<SubProcessLevel> subProcessLevel2 = subProcessLevelRepository
            .findById(zeroIfNull(element.getFkTbAceSubProLev2()));
        Optional<SubProcessLevel> subProcessLevel3 = subProcessLevelRepository
            .findById(zeroIfNull(element.getFkTbAceSubProLev3()));
        Optional<SubProcessLevel> subProcessLevel4 = subProcessLevelRepository
            .findById(zeroIfNull(element.getFkTbAceSubProLev4()));
        Optional<SubProcessLevel> subProcessLevel5 = subProcessLevelRepository
            .findById(zeroIfNull(element.getFkTbAceSubProLev5()));
        if (mainProcess.isPresent()) {
            element.setMainProcess(mainProcess.get());
        }
        if (subProcessLevel1.isPresent()) {
            element.setSubProcessLevel1(subProcessLevel1.get());
        }
        if (subProcessLevel2.isPresent()) {
            element.setSubProcessLevel2(subProcessLevel2.get());
        }
        if (subProcessLevel3.isPresent()) {
            element.setSubProcessLevel3(subProcessLevel3.get());
        }
        if (subProcessLevel4.isPresent()) {
            element.setSubProcessLevel4(subProcessLevel4.get());
        }
        if (subProcessLevel5.isPresent()) {
            element.setSubProcessLevel5(subProcessLevel5.get());
        }
        return element;
    }

    private Integer zeroIfNull(Integer x) {
        if (x == null) {
            return 0;
        }
        return x;
    }

    @Autowired private ProcessSegmentListElementRepository processSegmentListElementRepository;
    @Autowired private ProcessSegmentRepository processSegmentRepository;
    @Autowired private SubProcessLevelRepository subProcessLevelRepository;

}
