package com.taotao.service.acl.impl;

import com.taotao.model.acl.Role;
import com.taotao.model.acl.User;
import com.taotao.model.acl.WebModule;
import com.taotao.service.acl.AclManager;

import java.util.List;

/**
 * @author acerge(acerge@163.com)
 * @since 1:59:19 PM Aug 11, 2009
 */

public class AclManagerImpl implements AclManager {
	@Override
	public Role getRole(String rolename) {
		return null;
	}
	@Override
	public void removeRole(String rolename) {

	}
	@Override
	public Role addRole(String rolename, String description, String tag){
		Role role = new Role(rolename);
		role.setDescription(description);
		role.setTag(tag);
//		baseDao.saveObject(role);
		return role;
	}
	@Override
	public List<WebModule> getMainMenuList(String tag, boolean showAll) {
//		String query = "from WebModule w where w.menucode is not null and w.tag = ? and length(w.menucode)=2 " +
//		 		(showAll?"":"and w.display='Y'") + "order by menucode";
//		List result = baseDao.findByHql(query, tag);
		return null;
	}
	@Override
	public List<WebModule> getSubMenuList(String tag, String mainmenucode, boolean showAll) {
//		String query = "from WebModule w where w.menucode is not null " +
//				(showAll?"":"and w.display='Y' ") + "and w.tag = ? and length(w.menucode)=4 " +
//				"and w.menucode like ? and w.menucode!=?  order by menucode";
//		List result = baseDao.findByHql(query, tag, mainmenucode+"%", mainmenucode);
		return null;
	}
	@Override
	public String getMainMenuNextSubCode(String mainmenucode) {
//		DetachedCriteria query = DetachedCriteria.forClass(WebModule.class);
//		query.add(Restrictions.ne("menucode", mainmenucode));
//		query.add(Restrictions.like("menucode", mainmenucode, MatchMode.START));
//		query.setProjection(Projections.property("menucode"));
//		List codeList = baseDao.findByCriteria(query);
//		if(codeList.isEmpty()) return mainmenucode + "01";
//		List<Integer> codenumList = new ArrayList<Integer>();
//		for(Object code : codeList){
//			codenumList.add(Integer.valueOf(((String)code).substring(2, 4)));
//		}
//		Collections.sort(codenumList);
//		Integer num = codenumList.get(codenumList.size()-1);
//		num++;
//		String strnum = mainmenucode+num;
//		if(num<10) strnum = mainmenucode+"0"+num;
//		return String.valueOf(strnum);
		return null;
	}
	@Override
	public String getMainMenuNextCode(String tag) {
//		String query = "select w.menucode from WebModule w where w.menucode is not null and w.tag = ? and " +
//				"length(w.menucode)=2 order by menucode";
//		List codeList = baseDao.findByHql(query, tag);
//		if(codeList.isEmpty()) return "01";
//		List<Integer> codenumList = new ArrayList<Integer>();
//		for(Object code : codeList){
//			codenumList.add(Integer.valueOf((String)code));
//		}
//		Collections.sort(codenumList);
//		Integer num = codenumList.get(codenumList.size()-1);
//		num++;
//		String strnum = "" + num;
//		if(num<10) strnum = "0" + num;
//		return String.valueOf(strnum);
		return null;
	}
	@Override
	public List<Role> getRoleListByTag(String tag) {
//		String sql = "from Role where tag=? order by name";
//		List result = baseDao.findByHql(sql, tag);
//		return result;
		return null;
	}
	/*@Override
	public WebModule getWebModuleBySubMenucode(String submenucode) {
		if(submenucode.length()==4){
			String mainmenucode = submenucode.substring(0, 2);		
			String query = "from WebModule w where w.menucode = ? ";
			List<WebModule> wList = baseDao.findByHql(query, mainmenucode);
			if(wList.isEmpty()) return null;
			return wList.get(0);
		}
		return null;
	}*/
	@Override
	public List<User> getRelatedUsersByRole(Long roleId) {
//		String query = "from User where id in (select userid from User2Role where role.id = ?)";
//		List unrelatedUsers = baseDao.findByHql(query, roleId);
//		return unrelatedUsers;
		return null;
	}
	@Override
	public List<User> getUnrelatedUsersByRole(Long roleId) {
//		String query = "from User where id not in (select userid from User2Role where role.id = ?)";
//		List unrelatedUsers = baseDao.findByHql(query, roleId);
//		return unrelatedUsers;
		return null;
	}
	@Override
	public List<Long> getRelatedModulesByRole(Long roleId) {
//		String roleModuleQuery = "select module.id from Module2Role where role.id = ? ";
//		List roleModules = baseDao.findByHql(roleModuleQuery, roleId);
//		return roleModules;
		return null;
	}
	/*@Override
	public List<Long> getUnrelatedModuleListByRole(Long roleId) {
		// TODO Auto-generated method stub
		return null;
	}*/
}
