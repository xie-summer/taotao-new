package com.taotao.support;

public class AuthConfig {
	private String ssoValidateUrl;
	private String ssoLoginUrl;
	
	public String getSsoValidateUrl() {
		return ssoValidateUrl;
	}

	public void setSsoValidateUrl(String ssoValidateUrl) {
		this.ssoValidateUrl = ssoValidateUrl;
	}

	public String getSsoLoginUrl() {
		return ssoLoginUrl;
	}

	public void setSsoLoginUrl(String ssoLoginUrl) {
		this.ssoLoginUrl = ssoLoginUrl;
	}
	
}
