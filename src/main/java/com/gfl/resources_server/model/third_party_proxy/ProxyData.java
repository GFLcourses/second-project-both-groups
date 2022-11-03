package com.gfl.resources_server.model.third_party_proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProxyData {
//    private String ipPort;
    private String ip;
    private Integer port;
//    private String country;
//    private LocalDateTime lastChecked;
//    private String proxyLevel;
//    private String type;
//    private String speed;
//    private ProxySupport support;
//    private Integer count;
}
