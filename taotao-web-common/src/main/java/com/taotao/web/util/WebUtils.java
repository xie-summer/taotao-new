package com.taotao.web.util;

import com.taotao.api.vo.ResultCode;
import com.taotao.util.BaseWebUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;


public abstract class WebUtils extends BaseWebUtils {

    public static void setInviteFromCookie(HttpServletResponse res, String basePath, Long from, String string) {
        // TODO 待定

    }

    public static String getAndSetDefault(HttpServletRequest request, HttpServletResponse response) {
//		String citypinyin = request.getParameter(AdminCityContant.CITYPINYINKEY);
//		String citycode = AdminCityContant.getCodeByPinyin(citypinyin);
//		if (StringUtils.isBlank(citycode)) {
//			Cookie cookie = getCookie(request, "citycode");
//			if (cookie != null) {
//				citycode = cookie.getValue();
//				if (isValidCitycode(citycode)) {
//					return citycode;
//				}
//			}
//
//			citycode = "310000";
//			if (!isRobot(request.getHeader("User-Agent"))) {
//				citycode = getCitycodeByIp(getRemoteIp(request));
//			}
//		} else {
//			// 强制设置citycode
//			request.setAttribute(AdminCityContant.CITYCODE_KEY, citycode);
//		}
//		Cookie cookie = new Cookie("citycode", citycode);
//		cookie.setPath("/");
//		cookie.setMaxAge(60 * 60 * 24);// 24 hour
//		response.addCookie(cookie);
//		return citycode;
        return null;
    }

    public static String getCitycodeByIp(String ip) {
//		String citycode = "310000";
//		try {
//			String address = IPUtil.getAddress(ip);
//			if (StringUtils.isNotBlank(address)) {
//				Map<String, List<GewaCity>> proMap = AdminCityContant.getProMap();
//				for (String proName : proMap.keySet()) {
//					if (StringUtils.contains(address, proName)) {
//						boolean isBreak = true;
//						List<GewaCity> cityList = proMap.get(proName);
//						for (GewaCity gewaCity : cityList) {
//							if (StringUtils.contains(address, gewaCity.getCityname())) {
//								citycode = gewaCity.getCitycode();
//								isBreak = false;
//								break;
//							}
//						}
//						if (isBreak) {
//							citycode = cityList.get(0).getCitycode();
//						}
//						break;
//					}
//				}
//			}
//		} catch (Exception e) {
//			dbLogger.error("", e);
//			dbLogger.warn("获取城市代码错误!");
//		}
//		return citycode;
        return null;
    }

    public static ResultCode getAndSetDefaultWap(HttpServletRequest request, HttpServletResponse response) {
        String citycode = "";
        Cookie cookie = getCookie(request, "citycode");
        if (cookie != null) {
            citycode = cookie.getValue();
            if (!isValidCitycode(citycode)) {
                return ResultCode.getFailure("城市未开通！");
            }
            return ResultCode.getSuccess(citycode);
        }
        if (StringUtils.isBlank(citycode)) {
            citycode = "310000";
        }
        cookie = new Cookie("citycode", citycode);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 60 * 24);// 24 hour
        response.addCookie(cookie);
        return ResultCode.getSuccess(citycode);
    }

    public static boolean isValidCitycode(String citycode) {
//		Map<String, String> cityMap = AdminCityContant.allcityMap;
//		return cityMap.containsKey(citycode);
        return true;
    }

    public static void setCitycode(HttpServletRequest request, String citycode, HttpServletResponse response) {
        // 强制设置citycode
//		request.setAttribute(AdminCityContant.CITYCODE_KEY, citycode);
//		Cookie cookie = new Cookie("citycode", citycode);
//		cookie.setPath("/");
//		cookie.setMaxAge(60 * 60 * 24);// 24 hour
//		response.addCookie(cookie);

    }

    public static String urlDecoder(String str) {
        return urlDecoder(str, "utf-8");
    }

    public static String urlDecoder(String str, String encode) {
        String tmp = "";
        try {
            tmp = URLDecoder.decode(str, encode);
        } catch (Exception e) {
        }
        return tmp;
    }

    public static String getIpAndPort(String ip, HttpServletRequest request) {
        if (StringUtils.isBlank(ip)) {
            return null;
        }
        String port = request.getHeader("x-client-port");
        if (StringUtils.isBlank(port)) {
            return ip;
        }
        String result = ip + ":" + port;
        return result;
    }
}
