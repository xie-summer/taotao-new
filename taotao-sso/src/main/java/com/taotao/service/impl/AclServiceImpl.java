package com.taotao.service.impl;

import com.gewara.model.acl.GewaraUser;
import com.gewara.model.acl.Role;
import com.gewara.model.acl.User;
import com.gewara.model.acl.WebModule;
import com.gewara.web.support.AclService;

import java.util.List;

/**
 * @author acerge(acerge@163.com)
 * @since 1:59:19 PM Aug 11, 2009
 */

public class AclServiceImpl extends AbstractAclService implements AclService<WebModule> {
	@Override
	public GewaraUser getGewaraUser(Long userid, String logonType) {
		return baseDao.getObject(User.class, userid);
	}
	
	@Override
	public List<WebModule> getMenuList(String tag) {
		String query = "from WebModule where display='Y' and tag = ? order by menucode";
		List result = baseDao.findByHql(query, tag);
		return result;
	}
	@Override
	public List<WebModule> getSecurityModuleList() {
		String query = "from WebModule where moduleurl is not null and tag like ? order by matchorder";
		List result = baseDao.findByHql(query, WebModule.TAG_GEWA+"%");
		return result;
	}
	@Override
	public List<String> getRolenameList() {
		List result = baseDao.getObjectPropertyList(Role.class, "name");
		return result;
	}
}
