package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.ProxyConfigHolder;

public interface ProxyValidator {
    boolean validate(ProxyConfigHolder proxyConfigHolder);
}
