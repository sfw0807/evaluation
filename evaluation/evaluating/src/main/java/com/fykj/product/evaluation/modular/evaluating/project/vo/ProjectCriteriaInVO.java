package com.fykj.product.evaluation.modular.evaluating.project.vo;

import com.fykj.platform.kernel._c.model.JInputModel;

public class ProjectCriteriaInVO implements JInputModel {

	private String startDateStr;
	
	private String endDateStr;
	
	private String name;
	
	private String status;

	public String getStartDateStr() {
		return startDateStr;
	}

	public void setStartDateStr(String startDateStr) {
		this.startDateStr = startDateStr;
	}

	public String getEndDateStr() {
		return endDateStr;
	}

	public void setEndDateStr(String endDateStr) {
		this.endDateStr = endDateStr;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
