package com.linxu.provider.feign;

import feign.Feign;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * @author linxu
 * @date 2019/7/16
 * 用于禁用某个Feign客户端的Hystrix组件
 */
//@Configuration 如果想使用；这个注解也要注释
public class DisableHystrixConfiguration {
    @Bean
    @Scope("prototype")
    public Feign.Builder feignBuilder(){
        return Feign.builder();
    }
}
