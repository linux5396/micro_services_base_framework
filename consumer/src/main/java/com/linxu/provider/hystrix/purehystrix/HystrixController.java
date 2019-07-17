package com.linxu.provider.hystrix.purehystrix;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Future;

/**
 * @author linxu
 * @date 2019/7/12
 * tips:
 * 1、service can call many times fallback.just need to fallback="f1",f1.fallback="f2"...etc
 * 2、can use throwable as the fallback method parameter,and adjust the type of it to choose different ways.
 * 3、happens any ex expect HystrixBadRequestException will call the fallback,if you want ignore some ex,you can add
 * annotation parameter to do this.
 * 4、通常需要指定threadPoolKey，因为会根据线程key来划分业务的线程池。(如果没有指定线程池，则默认按照组来划分)
 * 5、Hystrix提供了缓存功能，但是，由于不是分布式缓存，在我看来，毫无作用。
 * 6、应对高并发下的请求合并：
 * 需要服务提供者与请求发送者的联合实现。按一定的时间窗口（默认10MS），对多个请求进行合并。
 * 7、这是单纯的hystrix熔断器；
 */
@Controller
public class HystrixController {
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 同步
     *
     * @return 返回一个直接结果
     */
    @HystrixCommand(fallbackMethod = "",ignoreExceptions = IOException.class,commandKey = "method name",groupKey = "组，一般按类名来",threadPoolKey = "")
    public String synRequest() {
        return restTemplate.getForObject("URL", String.class);
    }

    /**
     * 异步
     * can use ignoreException specify the type of ex to avoid service fallback by throwing anyEx
     * expect HystrixBadRequestException.So,use this character,we can catch the different type of ex do different solve.
     * just to add the <code>Throwable e</code> as the fallback Method parameter.
     *
     * @return 返回异步对象。
     */
    @HystrixCommand(fallbackMethod = "defaultFallBack",commandProperties = {
            //设置具体实例的超时时间，如果不设置，则采用全局的默认1S
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",value = "5000"),
            //下面的超时配置默认为启动，可以修改为false。来保证某个实例不启用超时
            @HystrixProperty(name = "execution.timeout.enabled",value = "false")
    },threadPoolProperties = {
            @HystrixProperty(name = "coreSize",value = "20")
            //线程池的最大队列长度。
            ,@HystrixProperty(name = "maxQueueSize",value = "-1"),
            //队列最大拒绝阈值，没有达到队列最大值，也能拒绝。
            @HystrixProperty(name = "queueSizeRejectionThreshold",value = "5")
    })
    public Future<String> asyncRequest() {
        return new AsyncResult<String>() {
            //这里是异步请求
            @Override
            public String invoke() {
                return restTemplate.getForObject("URL", String.class);
            }
        };
    }
    @HystrixCollapser(batchMethod = "batchRequest",collapserProperties = {
            //等待时间
            @HystrixProperty(name = "timeDelayInMillseconds",value = "100"),
            //每个批处理最大的请求数
            @HystrixProperty(name = "maxRequestsInBatch",value = "10"),
            @HystrixProperty(name = "requestCacheEnabled",value = "false")
    })
    public String singleRequest(){
        return null;
    }
    public List<String> batchRequest(){
        return restTemplate.getForObject("URL",List.class);
    }

    /**
     * private、public、protected all can define this method.
     * but you should  care of one tip, this method should be in thisClass.
     */
    private String defaultFallBack(Throwable throwable) {
        if (throwable instanceof IOException) {
            //do io service fallback
        } else if (throwable instanceof HttpClientErrorException) {
            //do request error fallback
        } else {
            return "";
        }
        return "";
    }

}
