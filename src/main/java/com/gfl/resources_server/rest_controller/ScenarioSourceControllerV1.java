package com.gfl.resources_server.rest_controller;

import com.gfl.resources_server.model.Scenario;
import com.gfl.resources_server.response_dto.ScenarioDto;
import com.gfl.resources_server.service.mapper.ScenarioMapper;
import com.gfl.resources_server.service.scenario.ScenarioSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/scenario")
public class ScenarioSourceControllerV1 {
    private final ScenarioSourceService scenarioSource;
    private final ScenarioMapper scenarioMapper;

    @Autowired
    public ScenarioSourceControllerV1(ScenarioSourceService scenarioSource,
                                      ScenarioMapper scenarioMapper) {
        this.scenarioSource = scenarioSource;
        this.scenarioMapper = scenarioMapper;
    }

    @GetMapping("/")
    public ResponseEntity<ScenarioDto> getScenario() {
        try {
            var scenario = scenarioSource.get();
            var scenarioDto = scenarioMapper.map(scenario);
            return new ResponseEntity<>(scenarioDto, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/")
    public ResponseEntity<?> setScenario(@RequestBody Scenario scenario) {
        scenarioSource.setScenario(scenario);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
