package com.gfl.resources_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Scenario {
    private String name;
    private String site;
    private List<Step> steps;
}
