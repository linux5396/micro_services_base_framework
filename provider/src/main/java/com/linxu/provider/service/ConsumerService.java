package com.linxu.provider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class ConsumerService {
    @Autowired
    RestTemplate restTemplate;
    public String hiService() {

        return restTemplate.getForObject("http://provider-0/service?",String.class)+"I am ribbon";
    }


}
