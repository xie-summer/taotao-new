package com.taotao.model.acl;


import com.taotao.model.BaseObject;

import java.io.Serializable;

public class Module2Role extends BaseObject {
	private static final long serialVersionUID = -5801762779064940301L;
	private Long id;
	private WebModule module;
	private Role role;
	public Module2Role() {
	}
	public Module2Role(WebModule module, Role role) {
		this.module = module;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Override
	public Serializable realId() {
		return id;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public WebModule getModule() {
		return module;
	}
	public void setModule(WebModule module) {
		this.module = module;
	}
}
