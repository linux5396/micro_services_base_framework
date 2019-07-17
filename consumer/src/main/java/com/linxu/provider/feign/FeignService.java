package com.linxu.provider.feign;

import com.linxu.provider.hystrix.feignhystrix.HystrixServiceCompenent;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author linxu
 * @date 2019/4/18
 * feign客户端
 *
 */
@FeignClient(value = "provider",fallback = HystrixServiceCompenent.class)
public interface FeignService {
    /**
     * to server
     *
     * @return s
     */
    @GetMapping(value = "/zone")
    String feignService();

    /**
     * 进行参数绑定
     * @param id 通过参数请求
     * @return 用户信息
     */
    @GetMapping(value = "/queryUserById")
    User queryUserById(@RequestParam("id") String id);
}
