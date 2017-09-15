package com.taotao.oss.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Oss对象存储配置属性
 */
@Component
//@Data
public class OssProperties {

    /**
     * endpoint 结束点
     */
    @Value("aliyun.oss.endpoint")
    private String OssEndpoint;


    /**
     * bucketName 名
     */
    @Value("aliyun.oss.bucketName")

    private String OssBucketName;
    /**
     * 最大文件大小
     */
    @Value("aliyun.oss.maxSize")
    private String OssMaxSize;
    /**
     * 签名过期时间（分钟）
     */
    @Value("aliyun.oss.policy.expire")
    private String OssPolicyExpire;

    @Value("aliyun.oss.internal")
    private String OssInternal;
    public String getOssEndpoint() {
        return OssEndpoint;
    }

    public void setOssEndpoint(String ossEndpoint) {
        OssEndpoint = ossEndpoint;
    }

    public String getOssBucketName() {
        return OssBucketName;
    }

    public void setOssBucketName(String ossBucketName) {
        OssBucketName = ossBucketName;
    }

    public String getOssMaxSize() {
        return OssMaxSize;
    }

    public void setOssMaxSize(String ossMaxSize) {
        OssMaxSize = ossMaxSize;
    }

    public String getOssPolicyExpire() {
        return OssPolicyExpire;
    }

    public void setOssPolicyExpire(String ossPolicyExpire) {
        OssPolicyExpire = ossPolicyExpire;
    }
}
