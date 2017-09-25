package com.taotao.api.gateway.filter;

import com.netflix.zuul.ZuulFilter;

/**
 * 与SpringMvc中的过滤器功能一样
 * 例如检查请求来源
 * 自定义Filter需要extend ZuulFilter
 * 设置 filterType, 重写run内的业务逻辑
 */
public class AccessSignFilter extends ZuulFilter {

    /**
     * pre：可以在请求被路由之前调用
     * routing：在路由请求时候被调用
     * post：在routing和error过滤器之后被调用
     * error：处理请求时发生错误时被调用
     *
     * @return
     */
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @Override
    public Object run() {
        return null;
    }
}
