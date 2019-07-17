package com.linxu.provider.controller;

import com.linxu.provider.feign.FeignService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    FeignService service;

    /**
     * feignService
     */
    @GetMapping("/service")
    public String getService() {
        return service.feignService();
    }
}
