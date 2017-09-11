package com.taotao.service.impl;

import com.taotao.model.acl.GewaraUser;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author acerge(acerge@163.com)
 * @since 1:59:19 PM Aug 11, 2009
 */

public abstract class AbstractAclService implements UserDetailsService {

	protected ThreadLocal<String> LOGON_TYPE = new ThreadLocal<String>();
	public void setLogonType(String logonType){
		this.LOGON_TYPE.set(logonType);
	}
	protected boolean copyAuthorities = false;
	public void setCopyAuthorities(boolean copyAuthorities) {
		this.copyAuthorities = copyAuthorities;
	}
	@Override
	public GewaraUser loadUserByUsername(String username) throws UsernameNotFoundException {
		LOGON_TYPE.set(null);
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		GewaraUser user = null ;
		if (principal instanceof UserDetails) {
			 username = ((UserDetails)principal).getUsername();
			String password = ((UserDetails) principal).getPassword();
		} else {
//			String username = principal.toString();
		}

//				baseDao.getObjectByUkey(User.class, "username", username);
		return user;
	}

	public boolean isCopyAuthorities() {
		return copyAuthorities;
	}
}
