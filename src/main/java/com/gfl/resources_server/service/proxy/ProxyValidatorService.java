package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.ProxyConfigHolder;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

@Service
public class ProxyValidatorService implements ProxyValidator {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProxyValidatorService.class);

    @Value("${validator-properties.connectionAwaitTime}")
    private Long connectionAwaitTime;
    @Value("${validator-properties.site}")
    private String testSite;

    @Autowired
    public ProxyValidatorService() {  }

    @Override
    public boolean validate(ProxyConfigHolder proxyConfigHolder) {
        try {
            SocketAddress socketAddress = new InetSocketAddress(
                    proxyConfigHolder.getProxyNetworkConfig().getHostname(),
                    proxyConfigHolder.getProxyNetworkConfig().getPort());
            Proxy proxy = new Proxy(Proxy.Type.HTTP, socketAddress);

            OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
                    .connectTimeout(connectionAwaitTime, TimeUnit.SECONDS)
                    .readTimeout(connectionAwaitTime, TimeUnit.SECONDS)
                    .writeTimeout(connectionAwaitTime, TimeUnit.SECONDS)
                    .proxy(proxy)
                    .build();
            Request request = new Request.Builder()
                    .get()
                    .url(testSite)
                    .build();

            Call call = okHttpClient.newCall(request);
            Response httpResponse = call.execute();

            LOGGER.info("Proxy is valid " + String.valueOf(httpResponse.code()));
            return httpResponse.code() == HttpStatus.OK.value();
        } catch (IOException e) {
            LOGGER.warn("Proxy validation failed. Caused exception: " + e);
            return false;
        }
    }
}
