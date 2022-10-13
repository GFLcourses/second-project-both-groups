package com.gfl.resources_server.service.mapper;

import com.gfl.resources_server.model.Scenario;
import com.gfl.resources_server.response_dto.ScenarioDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScenarioMapper implements Mapper<ScenarioDto, Scenario> {
    private final StepMapper stepMapper;

    @Autowired
    public ScenarioMapper(StepMapper stepMapper) {
        this.stepMapper = stepMapper;
    }

    @Override
    public ScenarioDto map(Scenario from) {
        return new ScenarioDto(
                from.getName(),
                from.getSite(),
                stepMapper.map(from.getSteps()));
    }

    @Override
    public List<ScenarioDto> map(List<Scenario> from) {
        return from.stream()
                .map(scenario -> new ScenarioDto(
                        scenario.getName(),
                        scenario.getSite(),
                        stepMapper.map(scenario.getSteps())
                ))
                .collect(Collectors.toList());
    }
}
