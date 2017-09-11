package com.taotao.web.configurer;

import com.taotao.configurer.MybatisConfigurer;
import com.taotao.web.cas.CasProperties;
import com.taotao.web.cas.FilterStatic;
import com.taotao.web.interceptor.CasFilterSecurityInterceptor;
import com.taotao.service.acl.AclService;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;


@EnableWebSecurity //启用web权限
public class MultiHttpSecurityConfig {
    @Autowired
    public static CasProperties casProperties;
    @Autowired
    private AclService aclService;
    @Autowired
    public /** final*/
            FilterStatic filterStatic;
    /**
     * 自定义动态权限过滤器
     */
    @Autowired
    public /**final*/
            CasFilterSecurityInterceptor casFilterSecurityInterceptor;
    private ApplicationContext context;

    protected final ApplicationContext getApplicationContext() {
        return this.context;
    }

    @Autowired
    public void setApplicationContext(ApplicationContext context) {
        this.context = context;
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //放入cas凭证校验器
        AuthenticationProvider authenticationProvider = (AuthenticationProvider) getApplicationContext()
                .getBean("casProvider");
        auth.authenticationProvider(authenticationProvider);
    }

    /**
     * cas filter类
     * <p>
     * 针对/login请求的校验
     *
     * @return
     */


    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true) //启用方法验证
    //如果依赖数据库读取角色等，则需要配置
    @AutoConfigureAfter(value = {MybatisConfigurer.class, CasConfiguration.class})
    @Order(1)
    public /*static*/ class WebSecurityConfig extends WebSecurityConfigurerAdapter {
        @Bean
        public CasAuthenticationFilter casAuthenticationFilter(ServiceProperties properties) throws Exception {
            CasAuthenticationFilter casAuthenticationFilter = new CasAuthenticationFilter();
            casAuthenticationFilter.setServiceProperties(properties);
            casAuthenticationFilter.setFilterProcessesUrl(casProperties.getAppServiceLoginUrl());
            casAuthenticationFilter.setAuthenticationManager(authenticationManager());
            casAuthenticationFilter
                    .setAuthenticationSuccessHandler(new SimpleUrlAuthenticationSuccessHandler("/index.html"));
            return casAuthenticationFilter;
        }

        /**
         * 身份校验配置
         *
         * @param auth
         * @throws Exception
         */
        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(aclService).passwordEncoder(new BCryptPasswordEncoder());
        }

        /**
         * 静态文静过滤
         */
        @Override
        public void configure(WebSecurity web) throws Exception {
            String[] filter = filterStatic.getStaticFilters().toArray(new String[0]);
            web.ignoring().antMatchers(filter);
        }

        /**
         * 自定义过滤规则及其安全配置
         */
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // HeadersConfigurer
            http.headers().frameOptions().disable(); /**disable() 等同于 链式编程and()*/

            // CsrfConfigurer
            http.csrf().disable();

            // ExpressionInterceptUrlRegistry
            http.authorizeRequests()/** 配置安全策略*/
                    //.antMatchers("/","/hello").permitAll()//定义/请求不需要验证
                    .anyRequest().authenticated().anyRequest()/**其余的所有请求都需要验证 */
                    .fullyAuthenticated();/**记住用户*/

            // acm cas策略
            // 对logout请求放行
            /**
             * 当使用 WebSecurityConfigurerAdapter，注销功能将会被自动应用，也就是说，就算不写也有用。
             默认情况下访问/logout将会将用户注销，包含的内容有：
             1、使HttpSession失效
             2、清空已配置的RememberMe验证
             3、清空 SecurityContextHolder
             4、重定向到 /login?success*/
            http.logout().deleteCookies().logoutUrl("/logout").logoutSuccessUrl("/login?success").permitAll();
            /***使用form表单登录    如果需要使用自定义登录页面需要
             http.formLogin().loginPage("/login")
             ***/
            http.formLogin()./*loginPage("/login").*/permitAll().defaultSuccessUrl("/", true);
            http.sessionManagement().maximumSessions(1).expiredUrl("/expired");
            // 入口
            CasAuthenticationEntryPoint entryPoint = getApplicationContext().getBean(CasAuthenticationEntryPoint.class);
            CasAuthenticationFilter casAuthenticationFilter = getApplicationContext()
                    .getBean(CasAuthenticationFilter.class);
            SingleSignOutFilter singleSignOutFilter = getApplicationContext().getBean(SingleSignOutFilter.class);
            LogoutFilter logoutFilter = getApplicationContext().getBean(LogoutFilter.class);
            /**
             * 执行顺序为
             * LogoutFilter-->SingleSignOutFilter-->CasAuthenticationFilter-->
             * ExceptionTranslationFilter
             */
            http.exceptionHandling().authenticationEntryPoint(entryPoint).and().addFilter(casAuthenticationFilter)
                    .addFilterBefore(logoutFilter, LogoutFilter.class)
                    .addFilterBefore(singleSignOutFilter, CasAuthenticationFilter.class);
            // addFilter
            http.addFilterBefore(casFilterSecurityInterceptor, FilterSecurityInterceptor.class);
        }


    }
}
