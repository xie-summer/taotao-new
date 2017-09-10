package com.taotao.acl;

import com.taotao.model.BaseObject;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public abstract class GewaraUser extends BaseObject implements UserDetails {
	private static final long serialVersionUID = 6078080839080249253L;
	public static final String USER_TYPE_MEMBER = "user_type_member";
	@Override
	public final boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public final boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public final boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public abstract List<GrantedAuthority> getAuthorities();
	public abstract boolean isRole(String rolename);
	public abstract String getRolesString();
	public abstract String getRealname();
	public abstract Long getId();
	public abstract String getUsertype();
}
