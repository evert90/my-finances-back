package br.com.erp.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "keepalive", url = "${app.url}")
public interface KeepAliveClient {

    @GetMapping("/keepalive") // Replace with an endpoint in your app
    void ping();
}
