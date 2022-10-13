package com.gfl.resources_server.response_dto;

import com.gfl.resources_server.model.Step;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScenarioDto {
    private String name;
    private String site;
    private List<StepDto> steps;
}
