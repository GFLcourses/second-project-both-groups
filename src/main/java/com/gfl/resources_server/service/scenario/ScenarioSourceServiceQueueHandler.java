package com.gfl.resources_server.service.scenario;

import com.gfl.resources_server.model.Scenario;
import com.gfl.resources_server.service.util.SourceFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Service
public class ScenarioSourceServiceQueueHandler implements ScenarioSourceService {
    private static Queue<Scenario> scenarios;
    private final SourceFileReaderService sourceFileReaderService;

    @Autowired
    public ScenarioSourceServiceQueueHandler(SourceFileReaderService sourceFileReaderService) {
        this.sourceFileReaderService = sourceFileReaderService;
    }

    @PostConstruct
    private void init() {
        try {
            scenarios = new LinkedList<>(sourceFileReaderService.getScenarios());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Scenario get() {
        return scenarios.poll();
    }

    @Override
    public void setScenario(Scenario scenario) {
        scenarios.add(scenario);
    }
}
