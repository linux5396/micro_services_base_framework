package com.linxu.provider.service;

import com.linxu.provider.utils.DateUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Future;


@Service
public class ConsumerService {
    /**
     * 注入负载均衡的rest模板
     */
    @Autowired
    private RestTemplate restTemplate;
    //设置熔断机制。默认超时机制，1S
    @HystrixCommand(fallbackMethod = "fallBack",threadPoolKey = "123",commandProperties = {
            @HystrixProperty(name = "execution.timeout.enabled",value = "false")
    })
    public String hiService() {
        return restTemplate.getForObject("http://provider/zone?",String.class)+"         I am ribbon";
    }

    /**
     * 用于测试同个池中的不同实例配置，比如超时与不超时是否隔离。
     * @return
     */
    @HystrixCommand(fallbackMethod = "fallBack",threadPoolKey = "123")
    public String hiService1() {
        return restTemplate.getForObject("http://provider/zone?",String.class)+"         I am ribbon in timeout";
    }

    public String anotherService() {
        return restTemplate.getForObject("http://provider-1/zone?",String.class)+"         I am ribbon";
    }
    private String fallBack(){
        return "System is busy.Please retry in Now :"+DateUtil.getCurrentTime();
    }
    //异步
    @HystrixCommand(fallbackMethod = "fallBack")
    public Future<String> asyncRequest() {
        return new AsyncResult<String>() {
            //这里是异步请求
            @Override
            public String invoke() {
                return restTemplate.getForObject("http://provider/zone?", String.class);
            }
        };
    }
}
