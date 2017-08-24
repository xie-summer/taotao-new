package com.taotao.entity;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * 
 *
 * @author ct
 * 2017年3月17日
 *
 */
public class BaseResData<T> implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4674870219607458870L;
	public String status;//状态 1：成功   0：失败
	public String code;
	public String msg;
	private T resdata;
	private Date systemTime; //系统时间
	public BaseResData(){
		
	}
	public BaseResData(T resdata){
		this.resdata = resdata;
	}

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	public T getResdata() {
		return resdata;
	}
	public void setResdata(T resdata) {
		this.resdata = resdata;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Date getSystemTime() {
		this.systemTime = new Date();
		return systemTime;
	}
	public void setSystemTime(Date systemTime) {
		this.systemTime = systemTime;
	}
	/**
	 * 将当前对象中各个属性转成map
	 * @throws
	 * @throws Exception
	 */
	public Map<String,String> toMap() throws Exception{
		Map<String,String> fields = new HashMap<String,String>();
		Field[] origFields = this.getClass().getDeclaredFields();
		String fieldName = null;
		Object fieldValue = null;
		for (int i = 0; i < origFields.length; i++) {
			fieldName = origFields[i].getName();
			fieldValue = origFields[i].get(this);
			if (fieldValue == null) {
				continue;
			}
			if(fieldValue instanceof BaseResData ){
				Map<String,String> mapF = ((BaseResData)fieldValue).toMap();
				fields.putAll(mapF);
			}else{
				fields.put(fieldName, fieldValue.toString());
			}
		}
		return fields;
	}
}
