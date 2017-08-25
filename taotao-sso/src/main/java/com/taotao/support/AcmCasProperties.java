package com.taotao.support;

import com.taotao.util.LocalIpUtil;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class AcmCasProperties {

    @Value("${cas.server.host.url}")
    private String casServerPrefix;

    @Value("${cas.server.host.login_url}")
    private String casServerLoginUrl;

    @Value("${cas.server.host.logout_url}")
    private String casServerLogoutUrl;

    @Value("${app.server.host.url}")
    private String appServicePrefix;

    @Value("${app.login.url}")
    private String appServiceLoginUrl;

    @Value("${app.logout.url}")
    private String appServiceLogoutUrl;

    public String getCasServerPrefix() {
        return LocalIpUtil.replaceTrueIpIfLocalhost(casServerPrefix);
    }
}