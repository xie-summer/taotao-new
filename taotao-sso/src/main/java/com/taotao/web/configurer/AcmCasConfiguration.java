package com.taotao.web.configurer;

import com.taotao.support.AcmCasProperties;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;


@Configuration
public class AcmCasConfiguration {

    @Autowired
    private AcmCasProperties acmCasProperties;

    /**
     * 设置客户端service的属性
     * <p>
     * 主要设置请求cas服务端后的回调路径,一般为主页地址，不可为登录地址
     * <p>
     * </p>
     *
     * @return
     */
    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        // 设置回调的service路径，此为主页路径
        serviceProperties.setService(acmCasProperties.getAppServicePrefix() + "/index.html");
        // 对所有的未拥有ticket的访问均需要验证
        serviceProperties.setAuthenticateAllArtifacts(true);

        return serviceProperties;
    }

    /**
     * 配置ticket校验器
     *
     * @return
     */
    @Bean
    public Cas20ServiceTicketValidator cas20ServiceTicketValidator() {
        // 配置上服务端的校验ticket地址
        return new Cas20ServiceTicketValidator(acmCasProperties.getCasServerPrefix());
    }

    /**
     * 单点注销，接受cas服务端发出的注销session请求
     *
     * @return
     * @see SingleLogout(SLO) Front or Back Channel
     */
    @Bean
    public SingleSignOutFilter singleSignOutFilter() {
        SingleSignOutFilter outFilter = new SingleSignOutFilter();
        // 设置cas服务端路径前缀，应用于front channel的注销请求
        outFilter.setCasServerUrlPrefix(acmCasProperties.getCasServerPrefix());
        outFilter.setIgnoreInitConfiguration(true);

        return outFilter;
    }

    /**
     * 单点请求cas客户端退出Filter类
     * <p>
     * 请求/logout，转发至cas服务端进行注销
     */
    @Bean
    public LogoutFilter logoutFilter() {
        // 设置回调地址，以免注销后页面不再跳转
        StringBuilder logoutRedirectPath = new StringBuilder();
        logoutRedirectPath.append(acmCasProperties.getCasServerPrefix())
                .append(acmCasProperties.getCasServerLogoutUrl()).append("?service=")
                .append(acmCasProperties.getAppServicePrefix());

        LogoutFilter logoutFilter = new LogoutFilter(logoutRedirectPath.toString(), new SecurityContextLogoutHandler());

        logoutFilter.setFilterProcessesUrl(acmCasProperties.getAppServiceLogoutUrl());
        return logoutFilter;
    }

    /**
     * 创建cas校验类
     * <p>
     * <p>
     * <b>Notes:</b> TicketValidator、AuthenticationUserDetailService属性必须设置;
     * serviceProperties属性主要应用于ticketValidator用于去cas服务端检验ticket
     * </p>
     *
     * @return
     */
    @Bean("casProvider")
    public CasAuthenticationProvider casAuthenticationProvider(
            AuthenticationUserDetailsService<CasAssertionAuthenticationToken> userDetailsService) {
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setKey("casProvider");
        provider.setServiceProperties(serviceProperties());
        provider.setTicketValidator(cas20ServiceTicketValidator());
        provider.setAuthenticationUserDetailsService(userDetailsService);

        return provider;
    }

    /**
     * ==============================================================
     * ==============================================================
     */

    /**
     * 认证的入口，即跳转至服务端的cas地址
     * <p>
     * <p>
     * <b>Note:</b>浏览器访问不可直接填客户端的login请求,若如此则会返回Error页面，无法被此入口拦截
     * </p>
     */
    @Bean
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint() {
        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setServiceProperties(serviceProperties());
        entryPoint.setLoginUrl(acmCasProperties.getCasServerPrefix() + acmCasProperties.getCasServerLoginUrl());

        return entryPoint;
    }
}