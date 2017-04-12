/**
 * 
 */
package com.fykj.sample.sysuser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

/**
 * @author zhengzw
 *
 */
@Entity
@Table(name = "t_sys_user_role")
public class SysUserRole extends AbstractEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 548430794319261090L;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "role_id")
	private String roleId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
}
