package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.ProxyConfigHolder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

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
                    .proxy(proxy)
                    .build();

            Request request = new Request.Builder()
                    .get()
                    .url("http://info.cern.ch/")
                    .build();

            var call = okHttpClient.newCall(request);
            Thread.sleep(17228500); // 24 * 60 * 60 * 1000 = 86_400_000 millisecond per day / 50 max request per day without premium = 17228000
            Response httpResponse = call.execute();
            System.out.println(httpResponse.code());

            // TODO: 04.11.2022
            //  we need figure out how to increase request timeOut to fix that exception.
            //  and then just return true/false in depends of http response code.

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }
}
