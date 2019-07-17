package com.linxu.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linxu
 * @date 2019/4/18
 */
@RestController
public class ServiceController {
    @Value("${server.port}")
    String port;
    @Value("${zone.name}")
    String zone;

    @GetMapping("/service")
    public String getService() {
        return "I am provider , the port is: " + port;
    }
    @GetMapping("/zone")
    public String getZone(){
        return zone;
    }
}
