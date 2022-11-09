package com.gfl.resources_server.service.scenario;

import com.gfl.resources_server.model.Scenario;
import com.gfl.resources_server.service.util.SourceFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.logging.Logger;

@Service
public class ScenarioSourceServiceQueueHandler implements ScenarioSourceService {
    private static final Logger LOGGER = Logger.getLogger(ScenarioSourceServiceQueueHandler.class.getName());
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
            LOGGER.info("ScenarioSourceServiceQueueHandler working");
        } catch (IOException e) {
            LOGGER.warning("ScenarioSourceServiceQueueHandler error in init method");
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
