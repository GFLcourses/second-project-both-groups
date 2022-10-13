package com.gfl.resources_server.rest_controller;

import com.gfl.resources_server.model.Scenario;
import com.gfl.resources_server.response_dto.ScenarioDto;
import com.gfl.resources_server.service.mapper.ScenarioMapper;
import com.gfl.resources_server.service.scenario.ScenarioSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ScenarioDto getScenario() {
        Scenario scenario = scenarioSource.get();
        return scenarioMapper.map(scenario);
    }
}