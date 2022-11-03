package com.gfl.resources_server.model.third_party_proxy;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ThirdPartyProxy {
    private ProxyData[] data;

    public String getIp() {
        return this.data[0].getIp();
    }

    public Integer getPort() {
        return this.data[0].getPort();
    }
}
