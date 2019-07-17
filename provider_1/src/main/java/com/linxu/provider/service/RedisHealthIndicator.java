package com.linxu.provider.service;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * redis status health indicator.
 *
 * @author linxu
 * @date 2019/7/6
 */
@Component
public class RedisHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        int status = check();
        return status == 0 ? Health.up().build() : Health.down().withDetail("check connection fail!", status).build();
    }

    private int check() {
        //do some check...
        return 0;
    }
}
