package com.fykj.product.evaluation.api.scoreitem.vo;

import com.fykj.platform.kernel._c.model.JOutputModel;
/**
 * ClassName: UserRoleOutVO  
 * (用户所属角色)
 * @author zhangtian  
 * @version
 */
public class UserRoleOutVO implements JOutputModel {

	private static final long serialVersionUID = 1L;
	private String roleId; // 角色ID
	private String roleName ;// 角色名称
	private String roleType ;// 角色类型

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}
}
