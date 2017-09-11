package com.taotao.web.cas;

import com.taotao.service.AclService;
import com.taotao.support.SecurityModule;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
@Component("dBFilterInvocationSecurityMetadataSource")
public class DBFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource, InitializingBean, ApplicationContextAware {
	private ApplicationContext applicationContext;
	private List<String> rolenameList;
	private Map<RequestMatcher, Collection<ConfigAttribute>> requestMap;
	private AclService aclService;
	private Set<String> allMapping = null;
	private Map<String, Collection<ConfigAttribute>> cachedMatch = new HashMap<String, Collection<ConfigAttribute>>();
	private String basePath = null;
	public DBFilterInvocationSecurityMetadataSource() {

	}

	@Override
	public void afterPropertiesSet() throws Exception {
		aclService = applicationContext.getBean(AclService.class);
		Assert.notNull(aclService, "必须提供aclService实现！");
		init();
	}

	public void init() {
		List<SecurityModule> moduleList = aclService.getSecurityModuleList();
		Map<RequestMatcher, Collection<ConfigAttribute>> tmp = new LinkedHashMap<RequestMatcher, Collection<ConfigAttribute>>();
		for (SecurityModule module : moduleList) {
			String url = module.getModuleurl();
			if (StringUtils.isNotBlank(url)) {
				int idx = module.getModuleurl().indexOf('?');
				if(idx>0){
					url = url.substring(0, idx);
				}
				if (StringUtils.isBlank(module.getRolenames())) {
					tmp.put(new GewaAntPathRequestMatcher(url), new ArrayList<ConfigAttribute>(0));
				} else {
					tmp.put(new GewaAntPathRequestMatcher(url), SecurityConfig.createList(StringUtils.split(module.getRolenames(), ",")));
				}
			}
		}
		this.requestMap = tmp;
		this.rolenameList = aclService.getRolenameList();
		initUrlMapping();
	}

	// ~ Methods
	// ========================================================================================================
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) {
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
		Collection<ConfigAttribute> cached = cachedMatch.get(request.getRequestURI());
		if(cached!=null){
			return cached;
		}
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (entry.getKey().matches(request)) {
				return entry.getValue();
			}
		}
		return null;
	}
	private Collection<ConfigAttribute> getAttributesByUri(String uri){
		for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : requestMap.entrySet()) {
			if (((GewaAntPathRequestMatcher)entry.getKey()).getMatcher().matches(uri.toLowerCase())) {
				return entry.getValue();
			}
		}
		return null;
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return SecurityConfig.createList(rolenameList.toArray(new String[rolenameList.size()]));
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	public void initUrlMapping(String contextPath, HashSet<String> mappings) {
		if(StringUtils.isBlank(contextPath)){
			contextPath = "";
		}
		if(contextPath.endsWith("/")){
			contextPath = contextPath.substring(0, contextPath.length()-1);
		}
		basePath = contextPath;
		allMapping = mappings;
		initUrlMapping();
	}
	private void initUrlMapping(){
		if(allMapping!=null){
			HashMap<String, Collection<ConfigAttribute>> tmp = new HashMap<String, Collection<ConfigAttribute>>();
			for(String mapping: allMapping){
				String uri = basePath + mapping;
				Collection<ConfigAttribute> atts = getAttributesByUri(mapping);
				if(atts==null) {
					atts = new ArrayList<ConfigAttribute>();
				}
				tmp.put(uri, atts);
			}
			cachedMatch = tmp;
		}
	}
}
