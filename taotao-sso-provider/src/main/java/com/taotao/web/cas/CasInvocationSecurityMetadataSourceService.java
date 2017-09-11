package com.taotao.web.cas;

import com.google.common.collect.Lists;
import com.taotao.model.acl.SysMenu;
import com.taotao.util.LoggerUtils;
import com.taotao.util.TLogger;
import com.taotao.service.AclService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * SecurityMetadataSource-当前访问路径的权限获取
 */
@Component("securityMetadataSource")
public class CasInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource , InitializingBean, ApplicationContextAware {
    private AclService aclService;
    private ApplicationContext applicationContext;
    private  HashSet<Pattern>  patterns = new HashSet<>();;

    private final TLogger logger = LoggerUtils.getLogger(this.getClass());

    @Override
    public void afterPropertiesSet() throws Exception {
        aclService = applicationContext.getBean(AclService.class);
        Assert.notNull(aclService, "必须提供sysMenuService实现！");
    }
    public CasInvocationSecurityMetadataSourceService() {}
    public CasInvocationSecurityMetadataSourceService(FilterStatic filterStatic) {
        //可通过配置过滤路径，这里就省略不写了，写法与AcmCasProperties一致
        for (String filter:filterStatic.getStaticFilters()){
            String regex= filter.replace("**","*").replace("*",".*");
            patterns.add(Pattern.compile(regex));
        }
    }



    /**
     * 查找url对应的角色
     */
    public Collection<ConfigAttribute> loadResourceDefine(String url){
        Collection<ConfigAttribute> array = Lists.newArrayList();
        ConfigAttribute cfg;
        SysMenu permission = aclService.findMenuRoles(url);
        if (permission !=null) {
            for (String role :permission.getRolenames().split(",")){
                cfg = new SecurityConfig(role);
                //此处只添加了用户的名字，其实还可以添加更多权限的信息，例如请求方法到ConfigAttribute的集合中去。此处添加的信息将会作为CasAccessDecisionManager类的decide的第三个参数。
                array.add(cfg);
            }
            return array;
        }
        return null;
    }

    /** 查询访问object 所需要的对应的权限
     * @param object
     * @return
     * @throws IllegalArgumentException
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        String url = request.getRequestURI();
        url = url.replaceFirst(request.getContextPath(), "");
        logger.warn(url);

        //将请求的url与配置文件中不需要访问控制的url进行匹配
        Iterator<Pattern> patternIterator=patterns.iterator();
        while (patternIterator.hasNext()){
            Pattern pattern = patternIterator.next();
            Matcher matcher=pattern.matcher(url);
            if (matcher.find())
                return null;
        }
        return loadResourceDefine(url);
    }


    /** 获取访问所有对象请求的权限，即不需要访问权限
     * @return
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
