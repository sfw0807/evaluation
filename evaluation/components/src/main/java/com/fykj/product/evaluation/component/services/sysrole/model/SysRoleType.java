package com.fykj.product.evaluation.component.services.sysrole.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

@Entity
@Table(name = "t_sys_role_type")
public class SysRoleType  extends AbstractEntity {
	
	private static final long serialVersionUID = 1L;

	@Column(name = "sys_role_id",length=40,nullable=false)
	private String sysRoleId;
	
	@Column(name = "role_type",length=40,nullable=false)
	private String roleType; // 0-管理员角色  1-填报角色 2-自评角色 3-专家角色 4-终评角色

	public String getSysRoleId() {
		return sysRoleId;
	}

	public void setSysRoleId(String sysRoleId) {
		this.sysRoleId = sysRoleId;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
	
}
