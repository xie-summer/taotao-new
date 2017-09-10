package com.taotao.acl;

import com.taotao.model.BaseObject;

import java.io.Serializable;

/**
 * This class is used to represent available roles in the database.
 * </p>
 * @author <a href="mailto:acerge@163.com">gebiao(acerge)</a>
 * @since 2007-9-28下午02:05:17
 */
public class Role extends BaseObject {
	private static final long serialVersionUID = 3690197650654049848L;
	protected Long id;
	protected String name;
	protected String description;
	protected String tag;				//分类：gewa，partner
	public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getAuthority() {
		return getName();
	}

	public String getName() {
		return this.name;
	}

	public String getDescription() {
		return this.description;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public Serializable realId() {
		return id;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
}
