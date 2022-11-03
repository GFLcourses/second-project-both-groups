package com.gfl.resources_server.model.third_party_proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProxySupport {
    private String https;
    private String get;
    private String post;
    private String cookies;
    private String referer;
    private String userAgent;
    private String google;
}
