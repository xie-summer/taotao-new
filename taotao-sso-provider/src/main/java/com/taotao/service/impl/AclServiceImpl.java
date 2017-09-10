package com.taotao.service.impl;


import com.taotao.mapper.GewaraUserMapper;
import com.taotao.mapper.SysMenuMapper;
import com.taotao.acl.*;
import com.taotao.service.AclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 菜单等管理
 */
@Service
public class AclServiceImpl extends AbstractAclService implements AclService<SysMenu> {
	@Autowired
	protected SysMenuMapper sysMenuMapper;
	@Autowired
	protected GewaraUserMapper gewaraUserMapper;

	@Override
	public SysMenu findMenuRoles(String url) {
		return sysMenuMapper.findMenuRoles(url);
	}
	@Override
	public GewaraUser getGewaraUser(Long userid, String logonType) {
//		 return gewaraUserMapper.getGewaraUser(userid,logonType);
	return null;
	}
	
	@Override
	public List<SysMenu> getMenuList(String tag) {
		List result = sysMenuMapper.getMenuList(tag);
		return result;
	}
	@Override
	public List<SysMenu> getSecurityModuleList() {
		List result = sysMenuMapper.getSecurityModuleList( WebModule.TAG_GEWA+"%");
		return result;
	}
	@Override
	public List<String> getRolenameList() {
//		List result = sysMenuMapper.getObjectPropertyList(Role.class, "name");
		return null;
	}
}
