package com.fykj.product.evaluation.api.scoreitem.vo;

import com.fykj.platform.kernel._c.model.JInputModel;
public class SysRoleCriteriaInVO implements JInputModel {
	
	private static final long serialVersionUID = 1L;
	private String name;
	private String description;
	private String roleType ;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
}
