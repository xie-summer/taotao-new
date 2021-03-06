//package com.taotao.web.configurer;
//
//import com.taotao.configurer.MybatisConfigurer;
//import com.taotao.service.acl.AclService;
//import com.taotao.web.cas.CasProperties;
//import com.taotao.web.cas.FilterStatic;
//import com.taotao.web.interceptor.CasFilterSecurityInterceptor;
//import org.jasig.cas.client.session.SingleSignOutFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.cas.ServiceProperties;
//import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
//import org.springframework.security.cas.web.CasAuthenticationFilter;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.security.web.authentication.logout.LogoutFilter;
//
//@Configuration
//@EnableWebSecurity //启用web权限
//@EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法验证
////如果依赖数据库读取角色等，则需要配置
//@AutoConfigureAfter(value = {MybatisConfigurer.class, CasConfiguration.class})
//public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
//    /**
//     * 自定义动态权限过滤器
//     */
//    @Autowired
//    private /**final*/
//            CasFilterSecurityInterceptor casFilterSecurityInterceptor;
//
//    @Autowired
//    private /**final*/
//            FilterStatic filterStatic;
//
//    @Autowired
//    private AclService aclService;
//
//    @Autowired
//    private CasProperties casProperties;
//
//    /**
//     * 自定义过滤规则及其安全配置
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        // HeadersConfigurer
//        http.headers().frameOptions().disable();
//
//        // CsrfConfigurer
//        http.csrf().disable();
//
//        // ExpressionInterceptUrlRegistry
//        http.authorizeRequests()/** 配置安全策略*/
//                //.antMatchers("/","/hello").permitAll()//定义/请求不需要验证
//                .anyRequest().authenticated().anyRequest()/**其余的所有请求都需要验证 */
//                .fullyAuthenticated();/**记住用户*/
//
//        // acm cas策略
//        // 对logout请求放行
//        http.logout().logoutUrl("/logout").permitAll();
//        http.formLogin().loginPage("/login").permitAll().defaultSuccessUrl("/", true);//使用form表单登录
//
//        http.sessionManagement().maximumSessions(1).expiredUrl("/expired");
//        // 入口
//        CasAuthenticationEntryPoint entryPoint = getApplicationContext().getBean(CasAuthenticationEntryPoint.class);
//        CasAuthenticationFilter casAuthenticationFilter = getApplicationContext()
//                .getBean(CasAuthenticationFilter.class);
//        SingleSignOutFilter singleSignOutFilter = getApplicationContext().getBean(SingleSignOutFilter.class);
//        LogoutFilter logoutFilter = getApplicationContext().getBean(LogoutFilter.class);
//        /**
//         * 执行顺序为
//         * LogoutFilter-->SingleSignOutFilter-->CasAuthenticationFilter-->
//         * ExceptionTranslationFilter
//         */
//        http.exceptionHandling().authenticationEntryPoint(entryPoint).and().addFilter(casAuthenticationFilter)
//                .addFilterBefore(logoutFilter, LogoutFilter.class)
//                .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class);
//        // addFilter
//        http.addFilterBefore(casFilterSecurityInterceptor, FilterSecurityInterceptor.class);
//    }
//
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        //放入cas凭证校验器
//        AuthenticationProvider authenticationProvider = (AuthenticationProvider) getApplicationContext()
//                .getBean("casProvider");
//        auth.authenticationProvider(authenticationProvider);
//
//    }
//
//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        // 静态文静过滤
//        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
////        String[] filter = filterStatic.getStaticFilters().toArray(new String[0]);
////        web.ignoring().antMatchers(filter);
//    }
//
//    /**身份校验配置
//     * @param auth
//     * @throws Exception
//     */
//    @Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(aclService).passwordEncoder(new BCryptPasswordEncoder());
//    }
//
//    /**
//     * cas filter类
//     * <p>
//     * 针对/login请求的校验
//     *
//     * @return
//     */
//    @Bean
//    public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties properties) throws Exception {
//        CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
//        casAuthenticationFilter.setServiceProperties(properties);
//        casAuthenticationFilter.setFilterProcessesUrl(casProperties.getAppServiceLoginUrl());
//        casAuthenticationFilter.setAuthenticationManager(authenticationManager());
//        casAuthenticationFilter
//                .setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/index.html"));
//        return casAuthenticationFilter;
//    }
//}
