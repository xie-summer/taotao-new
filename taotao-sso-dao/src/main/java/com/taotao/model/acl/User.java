package com.taotao.model.acl;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="mailto:acerge@163.com">gebiao(acerge)</a>
 * @since 2007-9-28下午02:05:17
 */
@Table(name = "user" )
public class User extends GewaraUser {
    private static final long serialVersionUID = 3832626162173359411L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "username")
    private String username; // required
    @Column(name = "password")
    private String password; // required
//    @Column(name = "nick_name")
    private String nickname;
//    @Column(name = "account_enabled")
    private String accountEnabled; // Y or N
//    @Column(name = "city_code")
    private String citycode;
//    @Column(name = "mobile")
    private String mobile;
//    @Column(name = "role_names")
    private String rolenames;
//    @Column(name = "user_type")
    private String usertype; // 用户类型：inner:内部用户，其他：外部用户
//    @Column(name = "email")
    private String email;

//    private List<GrantedAuthority> tmpAuth;

    public User() {
    }


//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        List<GrantedAuthority> auths = new ArrayList<>();
//        //获取用户对应的角色集合
//        List<SysRole> roles = this.getSysRoles();
//        for (SysRole role : roles) {
//            //手动加上ROLE_前缀
//            auths.add(new SimpleGrantedAuthority(SercurityConstants.prefix+role.getRoleName()));
//        }
//        return auths;
//    }

    public User(String username) {
        this.username = StringUtils.lowerCase(username);
    }

    @Override
    public final List<GrantedAuthority> getAuthorities() {
//        if (tmpAuth != null)
//            return tmpAuth;
//        tmpAuth = new ArrayList<GrantedAuthority>();
//        if (StringUtils.isBlank(rolenames))
//            return tmpAuth;
//        tmpAuth.addAll(AuthorityUtils.createAuthorityList(StringUtils.split(rolenames, ",")));
//        return tmpAuth;
    	return null;
    }

    public void setAuthorities(List<GrantedAuthority> tmpAuth) {
//        this.tmpAuth = tmpAuth;

    }

    @Override
    public final String getRolesString() {
        return rolenames;
    }

    @Override
    public final boolean isRole(String rolename) {
        String[] roles = StringUtils.split(rolenames, ",");
        return ArrayUtils.contains(roles, rolename);
    }

    @Override
    public String getRealname() {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        int index = username.indexOf('@');
        if (index > 0)
            return "m-" + username.substring(0, index);
        return "m-" + username;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public Serializable realId() {
        return id;
    }

    @Override
    public boolean isEnabled() {
        return "Y".equals(accountEnabled);
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getCitycode() {
        return citycode;
    }

    public void setCitycode(String citycode) {
        this.citycode = citycode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getRolenames() {
        return rolenames;
    }

    public void setRolenames(String rolenames) {
        this.rolenames = rolenames;
    }

    @Override
    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getAccountEnabled() {
        return accountEnabled;
    }

    public void setAccountEnabled(String accountEnabled) {
        this.accountEnabled = accountEnabled;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}

