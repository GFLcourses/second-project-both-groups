package com.gfl.resources_server.service.mapper;

import com.gfl.resources_server.model.Step;
import com.gfl.resources_server.response_dto.StepDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StepMapper implements Mapper<StepDto, Step> {

    @Override
    public StepDto map(Step from) {
        return new StepDto(from.getAction(), from.getValue());
    }

    @Override
    public List<StepDto> map(List<Step> from) {
        return from.stream()
                .map(step -> new StepDto(
                        step.getAction(),
                        step.getValue()
                ))
                .collect(Collectors.toList());
    }
}
