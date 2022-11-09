package com.gfl.resources_server.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfl.resources_server.exception.ThirdPartyServiceProxiesDailyLimitException;
import com.gfl.resources_server.model.ProxyConfigHolder;
import com.gfl.resources_server.model.third_party_proxy.ThirdPartyProxy;
import com.gfl.resources_server.service.mapper.ThirdProxyToProxyConfigHolderMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
public class ProxySourceServiceUrl implements ProxySourceService {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProxySourceServiceUrl.class);
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    private final ThirdProxyToProxyConfigHolderMapper thirdProxyToProxyConfigHolderMapper;

    @Value("${source-properties.proxy-url}")
    private String proxyUrl;
    @Value("${source-properties.fiftyProxiesPerDayErrorMessage}")
    private String fiftyProxiesLimitMessage;

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
            var responseBodyString = call.execute().body().string();

            if (responseBodyString.contains(fiftyProxiesLimitMessage)) {
                LOGGER.warn(String.format("proxies were terminated. Message from 3rd-party service: %s",
                        responseBodyString));
                throw new ThirdPartyServiceProxiesDailyLimitException(fiftyProxiesLimitMessage);
            }

            var objectMapper = new ObjectMapper();
            var proxyFromSite = objectMapper.readValue(responseBodyString, ThirdPartyProxy.class);
            return thirdProxyToProxyConfigHolderMapper.map(proxyFromSite);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
