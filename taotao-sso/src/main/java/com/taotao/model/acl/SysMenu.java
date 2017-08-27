package com.taotao.model.acl;

import com.taotao.model.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 菜单
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="menu")
public class SysMenu extends WebModule{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "module_url")
    private String moduleurl;
    @Column(name = "match_order")
    private Integer matchorder; //在权限分配时的匹配顺序
    @Column(name = "menu_code")
    private String menucode;
    @Column(name = "menu_title")
    private String menutitle;
    @Column(name = "target")
    private String target;
    @Column(name = "display")
    private String display; //是否
    @Column(name = "tag")
    private String tag;		//分类：gewa，partner
    @Column(name = "role_names")
    private String rolenames;
    @Column(name = "top")
    private boolean top;
}
