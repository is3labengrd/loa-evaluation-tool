package eng.it.loatool.subprocess_level;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.criteria_matrix.CriteriaMatrixRepository;
import eng.it.loatool.process_specific_info.ProcessSpecificInfoRepository;
import eng.it.loatool.scenario_resource.ScenarioResourceRepository;
import eng.it.loatool.subprocess_level_resource.SubProcessLevelResourceRepository;

@Service
public class DeleteSubProcessLevelService {

    @Transactional
    public Optional<SubProcessLevel> delete(SubProcessLevel subProcessLevel) {
        if (subProcessLevel == null) {
            return Optional.empty();
        }
        return this.deleteById(subProcessLevel.getPkTbId());
    }

    @Transactional
    public Optional<SubProcessLevel> deleteById(Integer id) {
        criteriaMatrixRepository
            .getCriteriaMatrixBySubprocessLevel(id)
            .ifPresent((matrix) -> {
                criteriaMatrixRepository.delete(matrix);
            });
        processSpecificInfoRepository
            .getProcessSpecificInfoBySubProcessId(id)
            .ifPresent((info) -> {
                processSpecificInfoRepository.delete(info);
            });
        scenarioResourceRepository
            .getScenarioResourcebySubProcessId(id)
            .forEach((scenarioResource) -> {
                scenarioResourceRepository.delete(scenarioResource);
            });
        subProcessLevelResourceRepository
            .getSubProcessLevelResourcebySubProcessId(id)
            .forEach((subprocessResource) -> {
                subProcessLevelResourceRepository
                    .delete(subprocessResource);
            });
        return generalCRUDService.delete(subProcessLevelRepository, id);
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private SubProcessLevelRepository subProcessLevelRepository;
    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;
    @Autowired private ProcessSpecificInfoRepository processSpecificInfoRepository;
    @Autowired private ScenarioResourceRepository scenarioResourceRepository;
    @Autowired private SubProcessLevelResourceRepository subProcessLevelResourceRepository;

}
