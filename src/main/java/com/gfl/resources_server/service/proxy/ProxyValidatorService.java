package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.ProxyConfigHolder;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

@Service
public class ProxyValidatorService implements ProxyValidator {

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
                    .connectTimeout(60L, TimeUnit.SECONDS)
                    .proxy(proxy)
                    .build();

            Request request = new Request.Builder()
                    .get()
                    .url("http://info.cern.ch/")
                    .build();

            Call call = okHttpClient.newCall(request);
            Response httpResponse = call.execute();

            System.out.println(httpResponse.code()); // TODO: 07.11.2022 change it to logging, please

            return httpResponse.code() == HttpStatus.OK.value();
        } catch (IOException e) {
            System.out.println(e.getMessage()); // TODO: 07.11.2022 change it to logging, please
            return false;
        }
    }
}
