//package com.taotao.oss.web.controller.ali;
//
//import com.alibaba.fastjson.JSONObject;
//import com.taotao.oss.rpc.api.ali.AliOssService;
//import com.taotao.support.ErrorCode;
//import com.taotao.util.OssProperties;
//import com.zheng.oss.common.constant.OssConstant;
//import com.zheng.oss.common.constant.OssResult;
//import com.zheng.oss.common.constant.OssResultConstant;
//import com.zheng.oss.web.service.AliyunOssService;
//import lombok.extern.log4j.Log4j;
//import org.apache.commons.lang.StringUtils;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.converter.json.MappingJacksonValue;
//import org.springframework.stereotype.Controller;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * Created by ZhangShuzheng on 2017/5/15.
// */
//@Controller
//@RequestMapping("/aliyun/oss")
//@Log4j
//public class AliyunOssController {
//
//
//	@Autowired
//	private OssProperties ossProperties;
//	@Autowired
//	private AliOssService aliOssService;
//
//	/**
//	 * 签名生成
//	 * @param callback 跨域请求
//	 * @return
//	 */
//	@GetMapping("/policy")
//	@ResponseBody
//	//@CrossOrigin(origins = "*", methods = RequestMethod.GET) // 该注解不支持JDK1.7
//	public Object policy(@RequestParam(required = false) String callback) {
////		JSONObject result = aliOssService.policy();
//		ErrorCode result = aliOssService.policy();
//		if (StringUtils.isBlank(callback)) {
//			return result;
//		}
//		MappingJacksonValue jsonp = new MappingJacksonValue(result);
//		jsonp.setJsonpFunction(callback);
//		return jsonp;
//	}
//
//	/**
//	 * 上传成功回调方法
//	 * @param request
//	 * @return
//	 */
//	@PostMapping("callback")
//	@ResponseBody
//	public Object callback(HttpServletRequest request) {
//		JSONObject data = new JSONObject();
//		String filename = request.getParameter("filename");
//		filename = "http://".concat(ossProperties.getOssBucketName()).concat(".").concat(ossProperties.getOssEndpoint()).concat("/").concat(filename);
//		data.put("filename", filename);
//		data.put("size", request.getParameter("size"));
//		data.put("mimeType", request.getParameter("mimeType"));
//		data.put("width", request.getParameter("width"));
//		data.put("height", request.getParameter("height"));
//		return ErrorCode.getSuccessReturn("上传成功",data);
//	}
//
//}
