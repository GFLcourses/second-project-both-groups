package com.gfl.resources_server.service.scenario;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfl.resources_server.model.Scenario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

@Service
public class ScenarioSourceServiceQueueHandler implements ScenarioSourceService {
    private static final Queue<Scenario> SCENARIOS = new LinkedList<>();

    @Value("${scenario.filePath}")
    private String scenarioFilePath;

    @Autowired
    public ScenarioSourceServiceQueueHandler() {  }

    @PostConstruct
    private void init() {
        try {
            InputStream inputStream = new ClassPathResource(scenarioFilePath).getInputStream();
            ObjectMapper objectMapper = new ObjectMapper();
            SCENARIOS.addAll(objectMapper.readValue(
                    inputStream,
                    new TypeReference<List<Scenario>>() {  }
            ));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Scenario get() {
        return SCENARIOS.peek();
    }
}
