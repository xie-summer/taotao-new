package com.taotao.oss.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.taotao.oss.rpc.api.AliOssService;
import com.taotao.oss.util.OssProperties;
import com.taotao.support.ErrorCode;

import lombok.extern.log4j.Log4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by ZhangShuzheng on 2017/5/15.
 */
@RestController
@Log4j
public class AliyunOssController {


    @Autowired
    private OssProperties ossProperties;
    @Autowired
    private AliOssService aliOssService;

    /**
     * 签名生成
     *
     * @param callback 跨域请求
     * @return
     */
    @GetMapping("/aliyun/oss/policy")
    //@CrossOrigin(origins = "*", methods = RequestMethod.GET) // 该注解不支持JDK1.7
    public Object policy(@RequestParam(required = false) String callback) {
//		JSONObject result = aliOssService.policy();
        ErrorCode result = aliOssService.policy();
        if (StringUtils.isBlank(callback)) {
            return result;
        }
        if (result.isSuccess()) {
            MappingJacksonValue jsonp = new MappingJacksonValue(result);
            jsonp.setJsonpFunction(callback);
            return jsonp;
        }
        return null;
    }

    /**
     * 上传成功回调方法
     *
     * @param request
     * @return
     */
    @PostMapping("callback")
    public Object callback(HttpServletRequest request) {
        JSONObject data = new JSONObject();
        String filename = request.getParameter("filename");
        filename = "http://".concat(ossProperties.getOssBucketName()).concat(".").concat(ossProperties.getOssEndpoint()).concat("/").concat(filename);
        data.put("filename", filename);
        data.put("size", request.getParameter("size"));
        data.put("mimeType", request.getParameter("mimeType"));
        data.put("width", request.getParameter("width"));
        data.put("height", request.getParameter("height"));
        return ErrorCode.getSuccessReturn("上传成功", data);
    }

}
