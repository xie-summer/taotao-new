package com.taotao.web.cas;

import com.google.common.collect.Lists;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 静态文件过滤器
 */
@Component
public class FilterStatic {
    /**
     * 返回静态资源过滤器集合
     *
     * @return
     */
    public static List<String> staticFilters = new ArrayList<>();

    static {
        staticFilters.add("/js/**");
        staticFilters.add("/css/**");
        staticFilters.add("/images/**");
        staticFilters.add("/**/favicon.ico");
        staticFilters.add("/**/*.ico");
    }

    public List<String> getStaticFilters() {
        return staticFilters;
    }
}
