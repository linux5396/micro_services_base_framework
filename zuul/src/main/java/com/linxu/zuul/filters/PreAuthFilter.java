package com.linxu.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * @author linxu
 * @date 2019/7/25
 * <tip>take care of yourself.everything is no in vain.</tip>
 * a pre filter on zuul.
 * just a demo.
 */
/*@Component*/
public class PreAuthFilter extends ZuulFilter {
    private static Logger log = LoggerFactory.getLogger(ZuulFilter.class);

    @Override
    public String filterType() {
        //路由前调用，主要用于鉴权等。可以结合分布式消息中间件，做一个全局登录。
        return FilterConstants.PRE_TYPE;
        //发生错误时候调用，作用于路由之后。最终交付给POST filter
        //  return FilterConstants.ERROR_TYPE;
        //在路由后调用，主要用于一些数据的处理。
        //   return FilterConstants.POST_TYPE;
        //正在路由时调用
        // return FilterConstants.ROUTE_TYPE;
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    /**
     * @return T
     */
    @Override
    public boolean shouldFilter() {
        // 进行跨域请求的时候，并且请求头中有额外参数，比如token，客户端会先发送一个OPTIONS请求来探测后续需要发起的跨域POST请求是否安全可接受
        // 所以这个请求就不需要拦截，下面是处理方式
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        if (request.getMethod().equals(RequestMethod.OPTIONS.name())) {
            log.info("OPTIONS请求不做拦截操作");
            return false;
        }
        return true;
    }

    /**
     * filter implements here.
     *
     * @return obj
     * @throws ZuulException occurred error.
     */
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        log.info("GateWay do auth. request is {}", request.getRemoteAddr() + " to " + request.getRequestURL() + " by " + request.getMethod());
        Object tokens = request.getParameter("accessToken");
        if (null == tokens) {
            log.warn("lack of tokens.");
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            return null;
        }
        log.info("access tokens is ok!");
        return null;
    }
}
