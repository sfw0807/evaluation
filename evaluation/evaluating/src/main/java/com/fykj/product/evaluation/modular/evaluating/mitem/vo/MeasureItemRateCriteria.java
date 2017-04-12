package com.fykj.product.evaluation.modular.evaluating.mitem.vo;

import com.fykj.platform.kernel._c.model.JModel;

public class MeasureItemRateCriteria implements JModel {
	
	private String category;
	
	private String measureItemId;
	
	private String projectId;
	
	private String name;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getMeasureItemId() {
		return measureItemId;
	}

	public void setMeasureItemId(String measureItemId) {
		this.measureItemId = measureItemId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
}
