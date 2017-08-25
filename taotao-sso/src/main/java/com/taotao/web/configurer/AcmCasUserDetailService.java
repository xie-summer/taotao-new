package com.taotao.web.configurer;

import com.taotao.model.acl.GewaraUser;
import com.taotao.util.LoggerUtils;
import com.taotao.util.TLogger;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class AcmCasUserDetailService implements AuthenticationUserDetailsService<CasAssertionAuthenticationToken> {

    private static final TLogger USER_SERVICE_LOGGER = LoggerUtils.getLogger(AcmCasUserDetailService.class);

    @Resource
    private TSysUserDao tsysUserDAO;

    @Override
    public UserDetails loadUserDetails(CasAssertionAuthenticationToken token) throws UsernameNotFoundException {
        USER_SERVICE_LOGGER.warn("校验成功的登录名为: " + token.getName());
        //此处涉及到数据库操作然后读取权限集合，读者可自行实现
        GewaraUser sysUser = tsysUserDAO.findByUserName(token.getName());
        if (null == sysUser) {
            throw new UsernameNotFoundException("username isn't exsited in log-cms");
        }
        return sysUser;
    }

}