package com.fykj.platform.web.model;

import java.io.Serializable;


public class SimplePageRequestVO implements Serializable ,Cloneable {

	private int page;
	
	private int size;
	
	private String orderColumn;
	
	private String orderType;
	
	
	SimplePageRequestVO(){}

	public SimplePageRequestVO(int page, int size) {
		this.page = page;
		this.size = size;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getOrderColumn() {
		return orderColumn;
	}

	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
