package com.linxu.www;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author linxu
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class, RedisAutoConfiguration.class})
//成为注册中心
@EnableEurekaServer
//成为配置中心
//@EnableConfigServer
public class CloudApplication {
    /**
     * 安全组件
     * <version>Greenwich</version>
     * 这个版本的安全组件会默认进行csrf攻击防御
     * 直接关闭防御的代码如下：
     *
     * @Override protected void configure(HttpSecurity http) throws Exception {
     * //        去掉security 的csrf验证,否则其他应用无法使用账号密码连接注册中心
     * System.err.println("取消security csrf验证");
     * http.csrf().disable();
     * super.configure(http);
     * }
     * 但是，更好的办法如下编码
     * 配置/eureka/**忽略csrf防御拦截,直接放行(/eureka/**是在application.yml中配置的eureka注册地址)
     * 并且在所有微服务实例中采用username:password@domain:port进行注册
     */
    @EnableWebSecurity
    class WebSercurityConfig extends WebSecurityConfigurerAdapter {
        /**
         * @param http 由于eureka的注册是基于Http协议，固验证Http
         * @throws Exception EX
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http.csrf().ignoringAntMatchers("/eureka/**");
            super.configure(http);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(CloudApplication.class, args);
    }

}
