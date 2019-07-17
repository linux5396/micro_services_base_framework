package com.linxu.provider.service;

import org.springframework.stereotype.Component;

/**
 * @author linxu
 * @date 2019/4/18
 */
@Component
public class HystrixServiceCompenent implements FeignService {
    @Override
    public String feignService() {
        return "sorry"+"this service is fail, by_hystrix";
    }
}
