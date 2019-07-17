package com.linxu.provider.hystrix.feignhystrix;

import com.linxu.provider.feign.FeignService;
import com.linxu.provider.feign.User;
import org.springframework.stereotype.Component;

/**
 * @author linxu
 * feign熔断器
 */
@Component
public class HystrixServiceCompenent implements FeignService {
    @Override
    public String feignService() {
        return "sorry" + "this service is fail, by_hystrix";
    }

    @Override
    public User queryUserById(String id) {
        return null;
    }
}
