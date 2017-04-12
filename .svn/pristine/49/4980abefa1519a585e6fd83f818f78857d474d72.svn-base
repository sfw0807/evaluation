/**
 * 
 */
package com.fykj.sample.sysuser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.CodesTable;
import com.fykj.platform.kernel._c.model.AbstractEntity;
import com.fykj.sample.cache.DictionaryCacheHelper;

/**
 * @author zhengzw
 *
 */
@Entity
@Table(name = "t_sys_user")
public class SysUser extends AbstractEntity {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 2860316140674418038L;

	@Column(name = "user_account")
	private String userAccount;

	@Column(name = "name")
	private String name;

	@Column(name = "password")
	private String password;

	@Column(name = "description")
	private String description;
	
	@Column(name = "email")
	private String email;

	@Column(name = "disabled")
	private String disabled;

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDisabled() {
		return disabled;
	}

	public void setDisabled(String disabled) {
		this.disabled = disabled;
	}

	public String getDisabledDict() {
		return DictionaryCacheHelper.getDictDataName(CodesTable.UserState.code, disabled);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
