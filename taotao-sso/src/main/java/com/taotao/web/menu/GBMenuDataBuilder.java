package com.taotao.web.menu;

import com.taotao.util.BeanUtil;
import com.taotao.web.support.SecurityModule;
import org.apache.commons.lang.StringUtils;

import java.util.*;

/**
 * Create the javascript required for the Xtree menu functionality
 * 
 * @author <a href="mailto:acerge@163.com">gebiao(acerge)</a>
 * @since 2007-9-28下午02:05:17
 */
public class GBMenuDataBuilder<T extends SecurityModule> {
	public static final String MENU_DATA_KEY = "com.gewara.web.menu.MENU_DATA_KEY";
	private String basePath = "";
	private Set<String> roles;
	private MenuRepository<T>  repository;

	public GBMenuDataBuilder(String basePath, String[] roles, MenuRepository<T> repository) {
		this.basePath = basePath;
		this.roles = new TreeSet<String>(Arrays.asList(roles));
		this.repository = repository;
	}

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public StringBuilder getMenuData() {
		StringBuilder sb = new StringBuilder();
		buildMenuRepository(sb);
		return sb;
	}

	public List getMenuTop() {

		List lst = new ArrayList();
		for (T menu : repository.topMenuList) {
			if (("1".equals(menu.getDisplay())||"Y".equals(menu.getDisplay()))&&isAllowed(menu)) {
				lst.add(menu);
			}
		}
		return lst;
	}

	private void buildMenuRepository(StringBuilder sb) {
		sb.append("{");
		sb.append("'id':'0',");
		sb.append("'text':'主菜单',");
		sb.append("'iconCls':'icon-pkg',");
		sb.append("'cls':'package',");
		sb.append("'singleClickExpand':true,");
		sb.append("'children':[");

		for (T menu : repository.topMenuList) {
			if (isAllowed(menu)) {
				sb.append("{");
				sb.append("'id':'" + menu.getMenucode() + "',");
				sb.append("'text':'" + menu.getMenutitle() + "',");
				List<T> subList = repository.menuMap.get(menu.getMenucode());
				if (subList != null && subList.size() > 0) {
					sb.append("'iconCls':'icon-pkg',");
					sb.append("'cls':'package',");
					sb.append("'singleClickExpand':true,");
					sb.append("'children':[");
					boolean hasSub = false;
					for (T subMenu : subList) {
						if (isAllowed(subMenu))
							hasSub = true;
						buildMenuData(subMenu, sb);
					}
					if (hasSub) {
						sb.deleteCharAt(sb.length() - 1);
					}
					sb.append("]");
				} else {
					String href = "";
					if (StringUtils.startsWith(menu.getModuleurl(), "http://"))
						href = menu.getModuleurl();
					else
						href = basePath + menu.getModuleurl();
					sb.append("'href':'" + href + "',");
					if (StringUtils.isNotBlank(menu.getTarget()))
						sb.append("'target':'" + menu.getTarget() + "',");
					sb.append("'isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true");
				}
				sb.append("},");
			}
		}
		sb.deleteCharAt(sb.length() - 1);
		sb.append("]");
		sb.append("}");
	}

	public List<T> getAllSubModule(){
		List<T> subModuleList = new ArrayList<T>();
		for (T menu : repository.topMenuList) {
			List<T> subList = repository.menuMap.get(menu.getMenucode());
			if (subList != null && subList.size() > 0) {
				for (T subMenu : subList) {
					if (isAllowed(subMenu)){
						subModuleList.add(subMenu);
					}
				}
			}
		}
		return subModuleList;
	}
	private void buildMenuData(T menu, StringBuilder sb) {
		if (!isAllowed(menu))
			return;
		sb.append("{");
		sb.append("'id':'" + menu.getMenucode() + "',");
		sb.append("'text':'" + menu.getMenutitle() + "',");
		String href = "";
		if (StringUtils.isNotBlank(menu.getModuleurl())) {
			if (menu.getModuleurl().startsWith("http://"))
				href = menu.getModuleurl();
			else
				href = basePath + menu.getModuleurl().substring(1);
			sb.append("'href':'" + href + "',");
		}
		if (StringUtils.isNotBlank(menu.getTarget()))
			sb.append("'target':'" + menu.getTarget() + "',");
		sb.append("'isClass':true,'iconCls':'icon-cls','cls':'cls','leaf':true");
		sb.append("},");
	}

	/**
	 * If the menu is allowed, this should return true.
	 *
	 * @return whether or not the menu is allowed.
	 */
	private boolean isAllowed(T menu) {
		String menuRoles = menu.getRolenames();
		// 无角色，默认允许
		if (StringUtils.isBlank(menuRoles))
			return false;
		String[] mroles = StringUtils.split(menuRoles, ",");
		for (int i = 0; i < mroles.length; i++) {
			if (roles.contains(mroles[i]))
				return true;
		}
		return false;
	}
	
	/**
	 * @return menuMap<menutitle, target, display, href>
	 */
	public Map<Map, List<Map>> getMenuTree(){
		Map<Map, List<Map>> menuMap = new LinkedHashMap<Map, List<Map>>();
		for(T menu: repository.topMenuList){
			if (isAllowed(menu)) {
				List<T> tmp = repository.menuMap.get(menu.getMenucode());
				List<Map> subList = new ArrayList<Map>();
				if(tmp!=null && tmp.size()>0){
					for(T subMenu: tmp){
						if(isAllowed(subMenu)) {
							Map tmpMap = BeanUtil.getBeanMapWithKey(subMenu, "menutitle", "target", "display");
							String href = "";
							if(subMenu.getModuleurl().startsWith("http://")) href = subMenu.getModuleurl();
							else if(StringUtils.isNotBlank(subMenu.getModuleurl())) href = basePath + subMenu.getModuleurl().substring(1);
							tmpMap.put("href", href);
							subList.add(tmpMap);
						}
					}
				}
				menuMap.put(BeanUtil.getBeanMapWithKey(menu, "menutitle", "target", "display"), subList);
			}
		}
		return menuMap;
	}

	public MenuRepository<T>  getRepository() {
		return repository;
	}

	public void setRepository(MenuRepository repository) {
		this.repository = repository;
	}
	
	
	
	
}
