package com.linxu.provider.ribbon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;
import java.util.Set;

/**
 * @author linxu
 * @date 2019/7/8
 */
@Component
public class GetOfRest {
    /**
     * 负载均衡请求模板
     */
    @Autowired
    private RestTemplate restTemplate;

    public String getString(String url, Map requestParams) throws Exception {
        if (requestParams.size() > 0) {
            StringBuilder sb = new StringBuilder();
            Object[] keys = requestParams.keySet().toArray();
            Object[] values = requestParams.entrySet().toArray();
            for (int i = 0; i < keys.length && i < values.length; i++) {
                sb.append(keys[i] = values[i] + "&");
            }
            ResponseEntity<String> responseEntity = restTemplate.getForEntity(url + sb.toString(), String.class);
            return responseEntity.getBody();
        }
        return null;
    }
}
