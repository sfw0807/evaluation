package com.fykj.product.evaluation.modular.evaluating.evaltarget.vo;

import com.fykj.platform.kernel._c.model.JInputModel;
import com.fykj.sample.sysuser.model.SysUser;

public class UserDetailCriteriaInVO extends SysUser implements JInputModel {
	
	private String evalTargetId;

	public String getEvalTargetId() {
		return evalTargetId;
	}

	public void setEvalTargetId(String evalTargetId) {
		this.evalTargetId = evalTargetId;
	}
	
	
}
