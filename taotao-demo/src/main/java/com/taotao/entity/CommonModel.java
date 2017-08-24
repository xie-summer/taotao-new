package com.taotao.entity;

import org.codehaus.jackson.annotate.JsonUnwrapped;

import javax.persistence.Transient;
import java.io.Serializable;

public class CommonModel implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7916328962275543445L;
	@Transient
	protected String sort;//排序字段
	@Transient
	protected String order;//排序方向
	@Transient
	protected String startDate;//开始时间
	@Transient
	protected String endDate;//结束时间		
	@Transient
	protected Integer pageNum;//页码	
	@Transient	
	protected Integer pageSize;//每页多少条
	
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
	@JsonUnwrapped
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@JsonUnwrapped
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@JsonUnwrapped
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	@JsonUnwrapped
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
	
}
