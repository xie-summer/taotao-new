package com.taotao.support;

import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class ErrorCode<T> implements Serializable {
    private static final long serialVersionUID = 4418416282894231647L;
    private String errcode;
    private String msg;
    private String syscode;    //内部代码
    private String sysmsg;    //内部消息
    private T retval;
    private boolean success = false;

    public ErrorCode() {

    }

    public ErrorCode(String code, String msg, T retval) {
        this.errcode = code;
        this.msg = msg;
        this.syscode = code;
        this.sysmsg = msg;
        this.retval = retval;
        this.success = StringUtils.equals(code, CODE_SUCCESS);
    }

    private static final String CODE_SUCCESS = "0000";
    private static final String CODE_UNKNOWN_ERROR = "9999";

    public static ErrorCode SUCCESS = new ErrorCode(CODE_SUCCESS, "操作成功！", null);

    @Override
    public boolean equals(Object another) {
        if (another == null || !(another instanceof ErrorCode)) return false;
        return this.errcode == ((ErrorCode) another).errcode;
    }

    public boolean isSuccess() {
        return success;
    }

    public static ErrorCode getFailure(String msg) {
        return new ErrorCode(CODE_UNKNOWN_ERROR, msg, null);
    }

    public static ErrorCode getFailure(String code, String msg) {
        return new ErrorCode(code, msg, null);
    }

    public static ErrorCode getSuccess(String msg) {
        return new ErrorCode(CODE_SUCCESS, msg, null);
    }

    public static <T> ErrorCode<T> getSuccessReturn(T retval) {
        return new ErrorCode(CODE_SUCCESS, null, retval);
    }

    public static <T> ErrorCode<T> getSuccessReturn(String msg, T retval) {
        return new ErrorCode(CODE_SUCCESS, msg, retval);
    }

    public static ErrorCode getSuccessMap() {
        return new ErrorCode(CODE_SUCCESS, null, new HashMap());
    }

    public static <T> ErrorCode getFailureReturn(T retval) {
        return new ErrorCode(CODE_UNKNOWN_ERROR, null, retval);
    }

    public static ErrorCode getFullErrorCode(String code, String msg, Object retval) {
        return new ErrorCode(code, msg, retval);
    }

    public T getRetval() {
        return retval;
    }

    public String getMsg() {
        return msg;
    }

    public ErrorCode setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public void put(Object key, Object value) {
        ((Map) retval).put(key, value);
    }

    public String getErrcode() {
        return errcode;
    }

    public ErrorCode setErrcode(String errcode) {
        this.errcode = errcode;
        return this;
    }

    public String getSyscode() {
        return syscode;
    }

    public ErrorCode setSyscode(String syscode) {
        this.syscode = syscode;
        return this;
    }

    public String getSysmsg() {
        return sysmsg;
    }

    public ErrorCode setSysmsg(String sysmsg) {
        this.sysmsg = sysmsg;
        return this;
    }
}
