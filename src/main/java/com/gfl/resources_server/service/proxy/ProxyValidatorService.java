package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.ProxyConfigHolder;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.Authenticator;
import java.net.InetSocketAddress;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.logging.Logger;

@Service
public class ProxyValidatorService implements ProxyValidator {
    private static final HttpClient client = HttpClient.newHttpClient();
    public static Logger logger = Logger.getLogger(ProxyValidatorService.class.getName());

    @Autowired
    public ProxyValidatorService() {  }

    @SneakyThrows
    @Override
    public boolean validate(ProxyConfigHolder proxyConfigHolder) {
        boolean status = false;


        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://pubproxy.com/api/proxy"))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());
        logger.info(String.valueOf(response.statusCode()));
        if (response.statusCode() == 200) status = true;



        return status;
    }
}
