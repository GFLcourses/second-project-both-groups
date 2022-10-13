package com.gfl.resources_server.service.mapper;

import com.gfl.resources_server.model.ProxyConfigHolder;
import com.gfl.resources_server.response_dto.ProxyConfigHolderDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProxyConfigHolderMapper implements Mapper<ProxyConfigHolderDto, ProxyConfigHolder> {
    private final ProxyCredentialsMapper proxyCredentialsMapper;
    private final ProxyNetworkConfigMapper proxyNetworkConfigMapper;

    public ProxyConfigHolderMapper(ProxyCredentialsMapper proxyCredentialsMapper,
                                   ProxyNetworkConfigMapper proxyNetworkConfigMapper) {
        this.proxyCredentialsMapper = proxyCredentialsMapper;
        this.proxyNetworkConfigMapper = proxyNetworkConfigMapper;
    }

    @Override
    public ProxyConfigHolderDto map(ProxyConfigHolder from) {
        return new ProxyConfigHolderDto(
                proxyNetworkConfigMapper.map(from.getProxyNetworkConfig()),
                proxyCredentialsMapper.map(from.getProxyCredentials()));
    }

    @Override
    public List<ProxyConfigHolderDto> map(List<ProxyConfigHolder> from) {
        return from.stream()
                .map(proxyConfigHolder -> new ProxyConfigHolderDto(
                        proxyNetworkConfigMapper.map(proxyConfigHolder.getProxyNetworkConfig()),
                        proxyCredentialsMapper.map(proxyConfigHolder.getProxyCredentials())
                ))
                .collect(Collectors.toList());
    }
}
