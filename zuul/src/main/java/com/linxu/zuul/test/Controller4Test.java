package com.linxu.zuul.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author linxu
 * @date 2019/7/26
 * <tip>take care of yourself.everything is no in vain.</tip>
 */
@RestController
@RefreshScope
public class Controller4Test {
    @Value("${value4test}")
    private String value4Test;
    @GetMapping("/test")
    public String test() {
        return value4Test;
    }
}
