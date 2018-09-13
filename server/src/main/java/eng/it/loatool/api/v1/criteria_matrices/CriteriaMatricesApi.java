package eng.it.loatool.api.v1.criteria_matrices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.criteria_matrix.CriteriaMatrix;
import eng.it.util.ResponseEntityTransformer;

@Controller
public class CriteriaMatricesApi {

    @GetMapping("/v1/criteria-matrices")
    public ResponseEntity<?> getCriteriaMatrices() {
        return ResponseEntityTransformer.transformOk(
            getCriteriaMatricesService.getCriteriaMatrices()
        );
    }

    @GetMapping("/v1/criteria-matrices/{matrixId}")
    public ResponseEntity<?> getCriteriaMatrix(@PathVariable("matrixId") Integer matrixId) {
        return ResponseEntityTransformer.transform(
            getCriteriaMatrixService.getCriteriaMatrix(matrixId)
        );
    }

    @PostMapping("/v1/criteria-matrices")
    public ResponseEntity<?> createCriteriaMatrix(@RequestBody CriteriaMatrix body) {
        return ResponseEntityTransformer.transform(
            createCriteriaMatrixService.createCriteriaMatrix(body)
        );
    }

    @PutMapping("/v1/criteria-matrices/{matrixId}")
    public ResponseEntity<?> updateCriteriaMatrix(
        @PathVariable("matrixId") Integer matrixId,
        @RequestBody CriteriaMatrix body
    ) {
        return ResponseEntityTransformer.transform(
            updateCriteriaMatrixService.updateCriteriaMatrix(matrixId, body)
        );
    }

    @Autowired private UpdateCriteriaMatrixService updateCriteriaMatrixService;
    @Autowired private CreateCriteriaMatrixService createCriteriaMatrixService;
    @Autowired private GetCriteriaMatricesService getCriteriaMatricesService;
    @Autowired private GetCriteriaMatrixService getCriteriaMatrixService;

}