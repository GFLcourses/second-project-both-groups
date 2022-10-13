package com.gfl.resources_server.service.scenario;

import com.gfl.resources_server.model.Scenario;
import com.gfl.resources_server.model.Step;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class ScenarioSourceServiceQueueHandler implements ScenarioSourceService {
    private static final Queue<Scenario> SCENARIOS = new LinkedList<>();

    @Autowired
    public ScenarioSourceServiceQueueHandler() {  }

    @PostConstruct
    public void init() {
        SCENARIOS.add(new Scenario(
                "test scenario 1",
                "http://info.cern.ch",
                new ArrayList<>(List.of(
                        new Step("clickCss", "body > ul > li:nth-child(1) > a"),
                        new Step("sleep", "5"),
                        new Step("clickXpath", "/html/body/p")
                ))
        ));
        SCENARIOS.add(new Scenario(
                "test scenario 2",
                "http://info.cern.ch",
                new ArrayList<>(List.of(
                        new Step("clickXpath", "/html/body/p"),
                        new Step("sleep", "5"),
                        new Step("clickCss", "body > ul > li:nth-child(1) > a")
                ))
        ));
    }

    @Override
    public Scenario get() {
        return SCENARIOS.peek();
    }
}
