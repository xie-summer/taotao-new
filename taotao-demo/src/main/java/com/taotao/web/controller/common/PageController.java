package com.taotao.web.controller.common;

import com.taotao.whats.web.controller.base.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 页面跳转控制器
 * @author xieshengrong
 */
@Controller
@RequestMapping("/page")
public class PageController extends BaseController{
	/**
	 * 页面跳转通用action
	 * @param pageName
	 * @return
	 */
	@RequestMapping(value = "{pageName}", method = RequestMethod.GET)
	public String toPage(@PathVariable("pageName") String pageName) {
		return pageName;

	}
	/**
	 * 页面跳转通用action
	 * @param pageName
	 * @return
	 */
	@RequestMapping(value = "{page}/{pageName}", method = RequestMethod.GET)
	public String toPage(@PathVariable("page") String page,@PathVariable("pageName") String pageName) {
		return page+"/"+pageName;

	}
}
