package com.linxu.configuration;

import com.netflix.loadbalancer.IRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author linxu
 * @date 2019/7/9
 */
@Configuration
public class CustomRuleConfig {
    @Bean
    public IRule customRule(){
        return new CustomRule();
    }
}
