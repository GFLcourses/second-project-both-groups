package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class ProxyServiceQueueHandlerService implements ProxySourceService {
    private static final Queue<ProxyConfigHolder> PROXIES = new LinkedList<>();

    @Autowired
    public ProxyServiceQueueHandlerService() {  }

    @PostConstruct
    public void init() {
        PROXIES.add(new ProxyConfigHolder(
                new ProxyNetworkConfig("host1", 8080),
                new ProxyCredentials("user11", "pass1")
        ));
        PROXIES.add(new ProxyConfigHolder(
                new ProxyNetworkConfig("host2", 8088),
                new ProxyCredentials("user2", "pass2")
        ));
        PROXIES.add(new ProxyConfigHolder(
                new ProxyNetworkConfig("host3", 8089),
                new ProxyCredentials("user4", "pass4")
        ));
    }

    @Override
    public ProxyConfigHolder get() {
        return PROXIES.peek();
    }
}
