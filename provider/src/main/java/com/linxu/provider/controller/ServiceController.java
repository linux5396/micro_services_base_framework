package com.linxu.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author linxu
 * @date 2019/4/18
 */
@RestController
public class ServiceController {
    @Value("${server.port}")
    String port;
    @Value("${zone.name}")
    String zone;

    @GetMapping("/service")
    public String getService() {
        return "I am provider , the port is: " + port;
    }
    @GetMapping("/zone")
    public String getZone() throws IOException{
        //test客户端的超时
     /*   if (Math.random()>0.5){
            throw new IOException("asdasd");
        }*/
        try {
            System.err.println("time");
            int time=(int)(Math.random()*1000);
            System.out.println(time);
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return zone;
    }
}
