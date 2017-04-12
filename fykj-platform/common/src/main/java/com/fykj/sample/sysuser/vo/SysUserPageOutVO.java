package com.fykj.sample.sysuser.vo;

import com.fykj.CodesTable;
import com.fykj.platform.kernel._c.model.JOutputModel;
import com.fykj.sample.cache.DictionaryCacheHelper;

public class SysUserPageOutVO implements JOutputModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5830602614597325141L;

	private String id;

	private String userAccount;

	private String name;

	private String description;

	private String disabled;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

}
