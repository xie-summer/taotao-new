package com.taotao.entity;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import javax.persistence.Transient;
import java.io.Serializable;

/** 
 * @author  作者 zsj
 * @date 创建时间：2016年11月29日 上午10:50:04 
 * @version 1.0 
 * @parameter  
 * @since  
 * @return  
 */
public class BaseEntity implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1449875354570226204L;
	//頁碼
	@Transient

	protected Integer pageNum;
//	每頁多少條 
	@Transient
	
	protected Integer pageSize;
	@JsonUnwrapped
	public Integer getPageNum() {
		return pageNum;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	@JsonUnwrapped
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	

}
