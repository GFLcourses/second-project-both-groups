package com.gfl.resources_server.service.mapper;

import com.gfl.resources_server.model.ProxyNetworkConfig;
import com.gfl.resources_server.response_dto.ProxyNetworkConfigDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProxyNetworkConfigMapper implements Mapper<ProxyNetworkConfigDto, ProxyNetworkConfig> {

    @Override
    public ProxyNetworkConfigDto map(ProxyNetworkConfig from) {
        return new ProxyNetworkConfigDto(from.getHostname(), from.getPort());
    }

    @Override
    public List<ProxyNetworkConfigDto> map(List<ProxyNetworkConfig> from) {
        return from.stream()
                .map(proxyNetworkConfig -> new ProxyNetworkConfigDto(
                        proxyNetworkConfig.getHostname(),
                        proxyNetworkConfig.getPort()
                ))
                .collect(Collectors.toList());
    }
}
