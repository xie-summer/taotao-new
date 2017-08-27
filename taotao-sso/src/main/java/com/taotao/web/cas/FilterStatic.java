package com.taotao.web.cas;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 静态文件过滤器
 */
@Component
public class FilterStatic {
    /** 返回静态资源过滤器集合
     * @return
     */
    public List<String> getStaticFilters() {
        return null;
    }
}
