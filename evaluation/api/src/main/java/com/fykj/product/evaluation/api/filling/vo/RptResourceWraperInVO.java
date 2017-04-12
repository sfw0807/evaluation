package com.fykj.product.evaluation.api.filling.vo;

import java.util.List;

import com.fykj.product.evaluation.api.filling.model.RptResource;

public class RptResourceWraperInVO {
	
	private String rptAnswerId;
	
	private List<RptResource> resources;

	public String getRptAnswerId() {
		return rptAnswerId;
	}

	public void setRptAnswerId(String rptAnswerId) {
		this.rptAnswerId = rptAnswerId;
	}

	public List<RptResource> getResources() {
		return resources;
	}

	public void setResources(List<RptResource> resources) {
		this.resources = resources;
	}
	
	

}
