package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProxyFacadeService implements ProxySourceService {
    private final ProxySourceServiceUrl proxySourceServiceUrl;
    private final ProxyValidatorService proxyValidatorService;

    @Autowired
    public ProxyFacadeService(ProxySourceServiceUrl proxySourceServiceUrl, ProxyValidatorService proxyValidatorService) {
        this.proxySourceServiceUrl = proxySourceServiceUrl;
        this.proxyValidatorService = proxyValidatorService;
    }

    @Override
    public ProxyConfigHolder get() {
        ProxyConfigHolder proxyConfigHolder = proxySourceServiceUrl.get();
        if (proxyValidatorService.validate(proxyConfigHolder)) {
            return proxyConfigHolder;
        }
        return this.get();
    }
}
