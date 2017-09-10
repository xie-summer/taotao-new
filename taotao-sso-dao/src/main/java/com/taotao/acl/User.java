package com.taotao.acl;

import com.google.common.collect.Lists;
import lombok.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * @author <a href="mailto:acerge@163.com">gebiao(acerge)</a>
 * @since 2007-9-28下午02:05:17
 */
@Table(name = "user")
@AllArgsConstructor
@NoArgsConstructor
public class User extends GewaraUser {
    private static final long serialVersionUID = 3832626162173359411L;
    @Column(name = "user_type")
    @Getter
    @Setter
    private String usertype; // 用户类型：inner:内部用户，其他：外部用户
    @Getter
    @Setter
    private List<GrantedAuthority> tmpAuth;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    private Long id;
    @Column(name = "username")
    @Getter
    @Setter
    private String username; // required
    @Column(name = "password")
    @Getter
    @Setter
    private String password; // required
    @Column(name = "nick_name")
    @Getter
    @Setter
    private String nickname;
    @Column(name = "account_enabled")
    @Getter
    @Setter
    private String accountEnabled; // Y or N
    @Column(name = "city_code")
    @Getter
    @Setter
    private String citycode;
    @Column(name = "mobile")
    @Getter
    @Setter
    private String mobile;
    @Column(name = "role_names")
    @Getter
    @Setter
    private String rolenames;
    @Column(name = "email")
    @Getter
    @Setter
    private String email;


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

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsertype() {
        return usertype;
    }

    @Override
    public final List<GrantedAuthority> getAuthorities() {
        if (tmpAuth != null)
            return tmpAuth;
        tmpAuth = Lists.newArrayList();
        if (StringUtils.isBlank(rolenames))
            return tmpAuth;
        tmpAuth.addAll(AuthorityUtils.createAuthorityList(StringUtils.split(rolenames, ",")));
        return tmpAuth;
    }

    @Override
    public final String getRolesString() {
        return rolenames;
    }

    @Override
    public final boolean isRole(@NonNull String rolename) {
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
    public Serializable realId() {
        return id;
    }

    @Override
    public boolean isEnabled() {
        return "Y".equals(accountEnabled);
    }


}
