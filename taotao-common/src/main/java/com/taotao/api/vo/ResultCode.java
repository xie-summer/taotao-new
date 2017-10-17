package com.taotao.api.vo;


import org.apache.commons.lang.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;


import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
public class ResultCode<T> implements Serializable{
	public static final String CODE_SUCCESS = "0000";
	public static final String CODE_UNKNOWN_ERROR = "9999";
	public static final String CODE_DATA_ERROR = "4005";

	private static final long serialVersionUID = 4418416282894231647L;
	private String errcode;
	private String msg;
	private T retval;
	private boolean success = false;
	private Throwable exception;
	protected ResultCode(String code, String msg, T retval){
		this.errcode = code;
		this.msg = msg;
		this.retval = retval;
		this.success = StringUtils.equals(code, CODE_SUCCESS);
	}
	public static ResultCode SUCCESS = new ResultCode(CODE_SUCCESS, "操作成功！", null);
	@Override
	public boolean equals(Object another){
		if(another == null || !(another instanceof ResultCode)) {
            return false;
        }
		return this.errcode== ((ResultCode)another).errcode;
	}
	public boolean isSuccess(){
		return success;
	}
	public static ResultCode getFailure(String msg){
		return new ResultCode(CODE_UNKNOWN_ERROR, msg, null);
	}
	public static ResultCode getFailure(String code, String msg){
		return new ResultCode(code, msg, null);
	}
	public static ResultCode getSuccess(String msg){
		return new ResultCode(CODE_SUCCESS, msg, null);
	}
	public static <T> ResultCode<T> getSuccessReturn(T retval){
		return new ResultCode(CODE_SUCCESS, null, retval);
	}
	public static ResultCode getSuccessMap(){
		return new ResultCode(CODE_SUCCESS, null, Maps.newHashMap());
	}
	public static <T> ResultCode getFailureReturn(T retval){
		return new ResultCode(CODE_UNKNOWN_ERROR, null, retval);
	}
	public static <T> ResultCode getFailureReturn(T retval, String msg){
		return new ResultCode(CODE_UNKNOWN_ERROR, msg, retval);
	}
	public static ResultCode getFullErrorCode(String code, String msg, Object retval){
		return new ResultCode(code, msg, retval);
	}
	public T getRetval() {
		return retval;
	}
	public String getMsg() {
		return msg;
	}
	public void put(Object key, Object value){
		((Map)retval).put(key, value);
	}
	public String getErrcode() {
		return errcode;
	}
	public Throwable getException() {
		return exception;
	}
	/**
	 * dubbo接口服务端请不要设置此异常！只作为客户端封装使用
	 * @param exception
	 */
	public void setException(Throwable exception) {
		this.exception = exception;
	}

}
