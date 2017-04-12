package com.fykj.product.evaluation.modular.evaluating.mitem.vo;

import com.fykj.platform.kernel._c.model.JInputModel;

public class MeasureItemCriteriaInVO implements JInputModel {

	private String name;
	
	private String parent;
	
	private String projectId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getParent() {
		return parent;
	}

	public void setParent(String parent) {
		this.parent = parent;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	
}
