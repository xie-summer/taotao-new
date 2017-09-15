package com.taotao.web.configurer;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.taotao.util.LocalIpUtil;

/**
 * CAS的配置参数
 */
@Component
@Data
public class CasProperties {
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