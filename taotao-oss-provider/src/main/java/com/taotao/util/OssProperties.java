package com.taotao.util;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Oss对象存储配置属性
 */
@Component
@Data
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
}
