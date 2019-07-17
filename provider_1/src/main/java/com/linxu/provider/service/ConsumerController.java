package com.linxu.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author linxu
 * @date 2019/4/18
 */
@RestController
//刷新配置
//@RefreshScope
public class ConsumerController {
    @Autowired
    private ConsumerService consumerService;

    @GetMapping("/consumer")
    public String consumerService() {
        return consumerService.hiService();
    }

   /* @Value("${remotevalue}")
    String remoteValue;*/

/*    @GetMapping("/config")
    public String test() {
        return remoteValue;
    }*/
 /*   @Resource
    private FeignService service;
    @GetMapping("/consumer")
    public String test() {
        return service.feignService();
    }*/
}
