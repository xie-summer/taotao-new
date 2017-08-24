package com.taotao.web.constant;

/**
 * Created by sunder on 2017/7/6.
 */
public enum PageEnum {
    INDEX("index"),//主页
    LOGIN("login"),//登录页面
    LOGOUT("logout"),//退出页面
    ERROR("error"),//404错误页面
    SERVER_ERROR("server_error");//服务器错误页面
    public String code;
    PageEnum(String code) {
        this.code = code;
    }
    public String getValue() {
        return code;
    }
}
