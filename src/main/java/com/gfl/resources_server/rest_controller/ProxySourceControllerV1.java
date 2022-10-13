package com.gfl.resources_server.rest_controller;

import com.gfl.resources_server.model.ProxyConfigHolder;
import com.gfl.resources_server.response_dto.ProxyConfigHolderDto;
import com.gfl.resources_server.service.mapper.ProxyConfigHolderMapper;
import com.gfl.resources_server.service.proxy.ProxySourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/proxy")
public class ProxySourceControllerV1 {
    private final ProxySourceService proxySourceService;
    private final ProxyConfigHolderMapper proxyMapper;

    @Autowired
    public ProxySourceControllerV1(ProxySourceService proxySourceService,
                                   ProxyConfigHolderMapper proxyMapper) {
        this.proxySourceService = proxySourceService;
        this.proxyMapper = proxyMapper;
    }

    @GetMapping("/")
    public ProxyConfigHolderDto getProxy() {
        ProxyConfigHolder proxy = proxySourceService.get();
        return proxyMapper.map(proxy);
    }
}