package com.taotao.mapper;

import com.taotao.model.acl.SysMenu;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface SysMenuMapper extends Mapper<SysMenu> {

    /** 根据URL查询访问需要的角色
     * @param url
     * @return
     */
    @Select("select * from menu where ")
    SysMenu findMenuRoles(String url);

    @Select("select * from menu m where display='Y' and tag = #{tag} order by menucode")
    List getMenuList(String tag);
    @Select("select * from menu m where moduleurl is not null and tag like #{tag} order by matchorder")
    List getSecurityModuleList(String tag);
}
