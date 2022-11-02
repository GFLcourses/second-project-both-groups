package com.gfl.resources_server.service.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfl.resources_server.model.ProxyConfigHolder;
import com.gfl.resources_server.model.ProxyCredentials;
import com.gfl.resources_server.model.ProxyNetworkConfig;
import com.gfl.resources_server.model.Scenario;
import com.gfl.resources_server.properties.SourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class SourceFileReaderService {
    private final String proxyNetworkFilePath;
    private final String proxyCredsFilePath;
    private final String scenarioFilePath;

    @Autowired
    public SourceFileReaderService(SourceProperties sourceProperties) {
        this.proxyNetworkFilePath = sourceProperties.getProxyNetwork();
        this.proxyCredsFilePath = sourceProperties.getProxyCreds();
        this.scenarioFilePath = sourceProperties.getScenario();
    }

    public List<ProxyConfigHolder> getProxyConfigHolders() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = new ClassPathResource(proxyNetworkFilePath).getInputStream();
        List<ProxyNetworkConfig> proxyNetworkConfigList = new ArrayList<>(objectMapper.readValue(
                inputStream,
                new TypeReference<List<ProxyNetworkConfig>>() {  }
        ));
        inputStream = new ClassPathResource(proxyCredsFilePath).getInputStream();
        List<ProxyCredentials> proxyCredentialsList = new ArrayList<>(objectMapper.readValue(
                inputStream,
                new TypeReference<List<ProxyCredentials>>() {  }
        ));

        List<ProxyConfigHolder> proxies = new ArrayList<>();
        for (int i = 0; i < proxyNetworkConfigList.size(); i++) {
            proxies.add(new ProxyConfigHolder(
                    proxyNetworkConfigList.get(i), proxyCredentialsList.get(i)
            ));
        }
        return proxies;
    }

    public List<Scenario> getScenarios() throws IOException {
        InputStream inputStream = new ClassPathResource(scenarioFilePath).getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();

        return new ArrayList<>(objectMapper.readValue(
                inputStream,
                new TypeReference<List<Scenario>>() {  }
        ));
    }
}
