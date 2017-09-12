package com.taotao.support;

public enum ERROR_CODE {
    SUCCESS("200"),//成功
    FAIL("400"),//失败
    UNAUTHORIZED("401"),//未认证（签名错误）
    NOT_FOUND("404"),//接口不存在
    INTERNAL_SERVER_ERROR("500");//服务器内部错误

    public String code;

    ERROR_CODE(String code) {
        this.code = code;
    }
}
