package com.gfl.resources_server.service.proxy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfl.resources_server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

@Service
public class ProxyServiceQueueHandlerService implements ProxySourceService {
    private static final Queue<ProxyConfigHolder> PROXIES = new LinkedList<>();

    @Value("${proxy.network.filePath}")
    private String proxyNetworkFilePath;

    @Value("${proxy.creds.filePath}")
    private String proxyCredsFilePath;


    @Autowired
    public ProxyServiceQueueHandlerService() {  }

    @PostConstruct
    public void init() {
        try {
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

            for (int i = 0; i < proxyNetworkConfigList.size(); i++) {
                PROXIES.add(new ProxyConfigHolder(
                   proxyNetworkConfigList.get(i), proxyCredentialsList.get(i)
                ));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProxyConfigHolder get() {
        return PROXIES.peek();
    }
}
