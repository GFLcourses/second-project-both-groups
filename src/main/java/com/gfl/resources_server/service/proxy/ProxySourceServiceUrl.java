package com.gfl.resources_server.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfl.resources_server.model.ProxyConfigHolder;
import com.gfl.resources_server.model.third_party_proxy.ThirdPartyProxy;
import com.gfl.resources_server.service.mapper.ThirdProxyToProxyConfigHolderMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class ProxySourceServiceUrl implements ProxySourceService {
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    private final ThirdProxyToProxyConfigHolderMapper thirdProxyToProxyConfigHolderMapper;

    @Value("${source-properties.proxy-url}")
    private String proxyUrl;

    @Autowired
    public ProxySourceServiceUrl(ThirdProxyToProxyConfigHolderMapper thirdProxyToProxyConfigHolderMapper) {
        this.thirdProxyToProxyConfigHolderMapper = thirdProxyToProxyConfigHolderMapper;
    }

    @Override
    public synchronized ProxyConfigHolder get() {
        try {
            TimeUnit.SECONDS.sleep(2L);
            Request request = new Request.Builder()
                    .get()
                    .url(proxyUrl)
                    .build();
            var call = OK_HTTP_CLIENT.newCall(request);
            var responseBody = call.execute().body();
            var objectMapper = new ObjectMapper();
            assert responseBody != null;
            var proxyFromSite = objectMapper.readValue(responseBody.string(), ThirdPartyProxy.class);
            return thirdProxyToProxyConfigHolderMapper.map(proxyFromSite);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
