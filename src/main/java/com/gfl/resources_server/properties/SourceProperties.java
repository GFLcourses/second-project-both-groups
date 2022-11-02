package com.gfl.resources_server.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("source-properties")
@Data
public class SourceProperties {
    private String scenario;
    private String proxyNetwork;
    private String proxyCreds;
}
