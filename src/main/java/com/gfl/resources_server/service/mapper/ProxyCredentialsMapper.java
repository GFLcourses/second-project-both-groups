package com.gfl.resources_server.service.mapper;

import com.gfl.resources_server.model.ProxyCredentials;
import com.gfl.resources_server.response_dto.ProxyCredentialsDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProxyCredentialsMapper implements Mapper<ProxyCredentialsDto, ProxyCredentials> {

    @Override
    public ProxyCredentialsDto map(ProxyCredentials from) {
        return new ProxyCredentialsDto(from.getUsername(), from.getPassword());
    }

    @Override
    public List<ProxyCredentialsDto> map(List<ProxyCredentials> from) {
        return from.stream()
                .map(proxyCredentials -> new ProxyCredentialsDto(
                        proxyCredentials.getUsername(),
                        proxyCredentials.getPassword()
                ))
                .collect(Collectors.toList());
    }
}
