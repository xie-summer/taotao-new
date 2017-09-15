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

	public void setCasServerPrefix(String casServerPrefix) {
		this.casServerPrefix = casServerPrefix;
	}

	public String getCasServerLoginUrl() {
		return casServerLoginUrl;
	}

	public void setCasServerLoginUrl(String casServerLoginUrl) {
		this.casServerLoginUrl = casServerLoginUrl;
	}

	public String getCasServerLogoutUrl() {
		return casServerLogoutUrl;
	}

	public void setCasServerLogoutUrl(String casServerLogoutUrl) {
		this.casServerLogoutUrl = casServerLogoutUrl;
	}

	public String getAppServicePrefix() {
		return appServicePrefix;
	}

	public void setAppServicePrefix(String appServicePrefix) {
		this.appServicePrefix = appServicePrefix;
	}

	public String getAppServiceLoginUrl() {
		return appServiceLoginUrl;
	}

	public void setAppServiceLoginUrl(String appServiceLoginUrl) {
		this.appServiceLoginUrl = appServiceLoginUrl;
	}

	public String getAppServiceLogoutUrl() {
		return appServiceLogoutUrl;
	}

	public void setAppServiceLogoutUrl(String appServiceLogoutUrl) {
		this.appServiceLogoutUrl = appServiceLogoutUrl;
	}
}