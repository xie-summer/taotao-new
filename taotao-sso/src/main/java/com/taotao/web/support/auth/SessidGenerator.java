package com.taotao.web.support.auth;

import org.springframework.security.core.Authentication;

public interface SessidGenerator {

	/**
	 * Éú³Ésessid
	 * @param auth
	 * @return
	 */
	String generateSessid(Authentication auth);
	
}
