package eng.it.loatool.api.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import eng.it.loatool.GeneralCRUDService;
import eng.it.loatool.api.ResponseEntityTransformer;
import eng.it.loatool.scenario_resource.GetScenarioResourceService;
import eng.it.loatool.scenario_resource.PersistScenarioResourceService;
import eng.it.loatool.scenario_resource.ScenarioResource;
import eng.it.loatool.scenario_resource.ScenarioResourceRepository;

@Controller
public class ScenarioResourcesApi {
    @GetMapping("/v1/scenario-resources")
    public ResponseEntity<?> getEveryScenarioResource() {
        return ResponseEntityTransformer.transformOk(
            getScenarioResourceService.getScenarioResources()
        );
    }

    @GetMapping("/v1/scenario-resources/{id}")
    public ResponseEntity<?> getScenarioResource(@PathVariable("id") Integer id) {
        return ResponseEntityTransformer.transform(
            getScenarioResourceService.getScenarioResourcebyId(id)
        );
    }

    @PostMapping("/v1/scenario-resources")
    public ResponseEntity<?> createScenarioResource(@RequestBody ScenarioResource body) {
        return ResponseEntityTransformer.transform(
            persistScenarioResourceService.create(body)
        );
    }

    @PutMapping("/v1/scenario-resources/{id}")
    public ResponseEntity<?> updateScenarioResource(
        @PathVariable("id") Integer id,
        @RequestBody ScenarioResource body
    ) {
        return ResponseEntityTransformer.transform(
            persistScenarioResourceService.update(id, body)
        );
    }

    @DeleteMapping("/v1/scenario-resources/{id}")
    public ResponseEntity<?> deleteScenarioResource(
        @PathVariable("id") Integer id
    ) {
        return ResponseEntityTransformer.transform(
            generalCRUDService.delete(scenarioResourceRepository, id)
        );
    }

    @Autowired private PersistScenarioResourceService persistScenarioResourceService;
    @Autowired private GetScenarioResourceService getScenarioResourceService;
    @Autowired private GeneralCRUDService generalCRUDService;
    @Autowired private ScenarioResourceRepository scenarioResourceRepository;

}
