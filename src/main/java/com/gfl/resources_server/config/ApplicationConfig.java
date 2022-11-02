package com.gfl.resources_server.config;

import com.gfl.resources_server.properties.SourceProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value={ "classpath:application.properties" })
@EnableConfigurationProperties(SourceProperties.class)
public class ApplicationConfig {
}
