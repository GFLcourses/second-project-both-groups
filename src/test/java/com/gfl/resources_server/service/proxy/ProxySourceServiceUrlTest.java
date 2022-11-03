package com.gfl.resources_server.service.proxy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gfl.resources_server.model.third_party_proxy.ProxyData;
import com.gfl.resources_server.model.third_party_proxy.ThirdPartyProxy;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.jupiter.api.Test;

import java.io.IOException;

class ProxySourceServiceUrlTest {
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();
    private static final String PROXY_URL = "http://pubproxy.com/api/proxy?format=json?type=http";

    @Test
    void get() throws IOException {
        Request request = new Request.Builder()
                .get()
                .url(PROXY_URL)
                .build();
        var call = OK_HTTP_CLIENT.newCall(request);
        var responseBody = call.execute().body();
        var objectMapper = new ObjectMapper();
        assert responseBody != null;
        var proxyFromSite = objectMapper.readValue(responseBody.string(), ThirdPartyProxy.class);
        System.out.println(proxyFromSite);
    }
}