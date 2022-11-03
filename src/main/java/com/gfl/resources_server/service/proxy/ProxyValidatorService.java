package com.gfl.resources_server.service.proxy;

import com.gfl.resources_server.model.ProxyConfigHolder;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProxyValidatorService implements ProxyValidator {
    private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient();

    @Autowired
    public ProxyValidatorService() {  }

    @Override
    public boolean validate(ProxyConfigHolder proxyConfigHolder) {

        // TODO: 03.11.2022
        //  here we have to send some request through proxy let's say to google,
        //  and then we need check http response code: if it's 200 -- proxy is valid

        return true;
    }
}
