package com.gfl.resources_server.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProxyNetworkConfig {
    private String hostname;
    private Integer port;
}
