package com.taotao.acl;


import com.taotao.model.BaseObject;

import java.io.Serializable;

public class User2Role extends BaseObject {
    private static final long serialVersionUID = -5801762779064940301L;
    private Long id;
    private Long userid;
    private Role role;

    public User2Role() {
    }

    public User2Role(Long userid, Role role) {
        this.userid = userid;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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

}
