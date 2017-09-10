package com.taotao.web.menu;

import com.taotao.web.support.SecurityModule;
import org.springframework.beans.support.PropertyComparator;

import java.io.Serializable;
import java.util.*;

public class MenuRepository<T extends SecurityModule>  implements Serializable {
	private static final long serialVersionUID = -7404705716824736582L;
	public static final String GEWA_MENU_REPOSITORY_KEY = "com.gewara.web.menu.GEWA_MENU_REPOSITORY";
	public static final String PARTNER_MENU_REPOSITORY_KEY = "com.gewara.web.menu.PARTNER_MENU_REPOSITORY";
	
	Map<String, List<T>> menuMap = null;
	List<T> topMenuList = null;
	public MenuRepository(List<T> moduleList){
		menuMap = new LinkedHashMap<String, List<T>>();
		topMenuList = new ArrayList<T>();
		menuMap.put("0", topMenuList);
		List<T> tmp = null;
		for(T module: moduleList){
			if(module.isTop()){
				topMenuList.add(module);
			}else {
				tmp = menuMap.get(module.getParentcode());
				if(tmp==null) {
					tmp = new ArrayList<T>();
					menuMap.put(module.getParentcode(), tmp);
				}
				tmp.add(module);
			}
		}
		Collections.sort(topMenuList, new PropertyComparator("matchorder", false, true));
		for(String code: menuMap.keySet()){
			Collections.sort(menuMap.get(code), new PropertyComparator("matchorder", false, true));
		}
	}
	public Map<String, List<T>> getMenuMap() {
		return menuMap;
	}
	public void setMenuMap(Map<String, List<T>> menuMap) {
		this.menuMap = menuMap;
	}
	
	
	public List<T> getTopMenu(){
		return menuMap.get("0");
	}
	
	
}
