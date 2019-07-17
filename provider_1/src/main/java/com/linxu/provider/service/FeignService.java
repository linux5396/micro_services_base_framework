package com.linxu.provider.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author linxu
 * @date 2019/4/18
 */
@FeignClient(value = "provider-0",fallback = HystrixServiceCompenent.class)
public interface FeignService {
    /**
     * to server
     *
     * @return s
     */
    @GetMapping("/service")
    String feignService();
}
