package com.gfl.resources_server.rest_controller;

import com.gfl.resources_server.exception.ThirdPartyServiceProxiesDailyLimitException;
import com.gfl.resources_server.service.mapper.ProxyConfigHolderMapper;
import com.gfl.resources_server.service.proxy.ProxySourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/proxy")
public class ProxySourceControllerV1 {
    private final ProxySourceService proxySourceService;
    private final ProxyConfigHolderMapper proxyMapper;

    @Autowired
    public ProxySourceControllerV1(@Qualifier("proxyFacadeService") ProxySourceService proxySourceService,
                                   ProxyConfigHolderMapper proxyMapper) {
        this.proxySourceService = proxySourceService;
        this.proxyMapper = proxyMapper;
    }

    @GetMapping("/")
    public ResponseEntity<?> getProxy() {
        try {
            var proxy = proxySourceService.get();
            var proxyDto = proxyMapper.map(proxy);
            return new ResponseEntity<>(proxyDto, HttpStatus.OK);
        } catch (ThirdPartyServiceProxiesDailyLimitException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NO_CONTENT);
        }
    }
}
