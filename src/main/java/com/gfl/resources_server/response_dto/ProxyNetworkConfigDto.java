package com.gfl.resources_server.response_dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProxyNetworkConfigDto {
    private String hostname;
    private Integer port;
}
