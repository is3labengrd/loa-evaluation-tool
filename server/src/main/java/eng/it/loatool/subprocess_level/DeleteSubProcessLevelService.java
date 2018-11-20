package eng.it.loatool.subprocess_level;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.criteria_matrix.CriteriaMatrixRepository;

@Service
public class DeleteSubProcessLevelService {

    @Transactional
    public Optional<SubProcessLevel> deleteById(Integer id) {
        criteriaMatrixRepository
            .getCriteriaMatrixBySubprocessLevel(id)
            .ifPresent((matrix) -> {
                criteriaMatrixRepository.delete(matrix);
            });
        return generalCRUDService.delete(subProcessLevelRepository, id);
    }

    @Transactional
    public Optional<SubProcessLevel> delete(SubProcessLevel subProcessLevel) {
        if (subProcessLevel == null) {
            return Optional.empty();
        }
        criteriaMatrixRepository
            .getCriteriaMatrixBySubprocessLevel(subProcessLevel.getPkTbId())
            .ifPresent((matrix) -> {
                criteriaMatrixRepository.delete(matrix);
            });
        return generalCRUDService.delete(subProcessLevelRepository, subProcessLevel.getPkTbId());
    }

    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private SubProcessLevelRepository subProcessLevelRepository;
    @Autowired private CriteriaMatrixRepository criteriaMatrixRepository;

}
