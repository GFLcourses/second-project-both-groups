package com.gfl.resources_server.service.mapper;

import com.gfl.resources_server.model.ProxyConfigHolder;
import com.gfl.resources_server.model.ProxyCredentials;
import com.gfl.resources_server.model.ProxyNetworkConfig;
import com.gfl.resources_server.model.third_party_proxy.ThirdPartyProxy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ThirdProxyToProxyConfigHolderMapper implements Mapper<ProxyConfigHolder, ThirdPartyProxy> {

    @Autowired
    public ThirdProxyToProxyConfigHolderMapper() {  }

    @Override
    public ProxyConfigHolder map(ThirdPartyProxy from) {
        return new ProxyConfigHolder(
                new ProxyNetworkConfig(from.getIp(), from.getPort()),
                new ProxyCredentials("what do I have to set here?", "-_-"));
    }

    @Override
    public List<ProxyConfigHolder> map(List<ThirdPartyProxy> from) {
        return from.stream()
                .map(proxy -> new ProxyConfigHolder(
                        new ProxyNetworkConfig(proxy.getIp(), proxy.getPort()),
                        new ProxyCredentials("what do I have to set here?", "-_-")
                ))
                .collect(Collectors.toList());
    }
}
