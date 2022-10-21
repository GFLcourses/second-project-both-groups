package com.gfl.resources_server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value= { "classpath:application.properties" })
public class ApplicationConfig {
}
