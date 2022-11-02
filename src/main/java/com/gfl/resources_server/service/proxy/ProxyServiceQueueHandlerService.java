package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.*;
import com.gfl.resources_server.service.util.SourceFileReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

@Service
public class ProxyServiceQueueHandlerService implements ProxySourceService {
    private static Queue<ProxyConfigHolder> proxies = new LinkedList<>();
    private final SourceFileReaderService sourceFileReaderService;

    @Autowired
    public ProxyServiceQueueHandlerService(SourceFileReaderService sourceFileReaderService) {
        this.sourceFileReaderService = sourceFileReaderService;
    }

    @PostConstruct
    public void init() {
        try {
            proxies = new LinkedList<>(sourceFileReaderService.getProxyConfigHolders());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public ProxyConfigHolder get() {
        return proxies.peek();
    }
}
