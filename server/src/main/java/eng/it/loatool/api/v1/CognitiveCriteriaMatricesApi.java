package eng.it.loatool.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.criteria_matrix.cognitive.CognitiveCriteriaMatrix;
import eng.it.loatool.criteria_matrix.cognitive.CognitiveCriteriaMatrixRepository;
import eng.it.loatool.criteria_matrix.cognitive.GetCognitiveCriteriaMatrixService;

@Controller
public class CognitiveCriteriaMatricesApi {

    @GetMapping("/v1/cognitive-criteria-matrices")
    public ResponseEntity<?> getCriteriaMatrices() {
        return ResponseEntityTransformer.transformOk(
            generalCRUDService.getAll(cognitiveCriteriaMatrixRepository)
        );
    }

    @GetMapping("/v1/cognitive-criteria-matrices/{matrixId}")
    public ResponseEntity<?> getCriteriaMatrix(@PathVariable("matrixId") Integer matrixId) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.getOne(cognitiveCriteriaMatrixRepository, matrixId)
        );
    }

    @GetMapping("/v1/cognitive-criteria-matrices-by-subprocess-id/{id}")
    public ResponseEntity<?> getCriteriaMatrixBySubprocessId(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            getCognitiveCriteriaMatrixService.getBySubprocessId(id)
        );
    }

    @PostMapping("/v1/cognitive-criteria-matrices")
    public ResponseEntity<?> createCriteriaMatrix(@RequestBody CognitiveCriteriaMatrix body) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.create(cognitiveCriteriaMatrixRepository, body)
        );
    }

    @PutMapping("/v1/cognitive-criteria-matrices/{matrixId}")
    public ResponseEntity<?> updateCriteriaMatrix(
        @PathVariable("matrixId") Integer matrixId,
        @RequestBody CognitiveCriteriaMatrix body
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.update(cognitiveCriteriaMatrixRepository, matrixId, body)
        );
    }

    @Autowired private GetCognitiveCriteriaMatrixService getCognitiveCriteriaMatrixService;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private CognitiveCriteriaMatrixRepository cognitiveCriteriaMatrixRepository;

}
