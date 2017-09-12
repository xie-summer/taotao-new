package com.taotao.support.auth;

import org.springframework.security.core.Authentication;

public interface SessidGenerator {

	/**
	 * 生成sessid
	 * @param auth
	 * @return
	 */
	String generateSessid(Authentication auth);
	
}