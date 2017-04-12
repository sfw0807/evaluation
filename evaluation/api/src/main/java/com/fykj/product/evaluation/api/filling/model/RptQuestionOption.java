package com.fykj.product.evaluation.api.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

@Entity
@Table(name = "t_rpt_question_option")
public class RptQuestionOption extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8676875226582515608L;
	
	@Column(name = "rpt_question_id", length=40)
	private String rptQuestionId;
	
	@Column(name = "opt_name", length=40)
	private String optName; //选项
	
	@Column(name = "opt_content", length=1000)
	private String optContent; //描述

	@Column(name = "opt_score", length=1000)
	private float optScore; //分数
	
	@Column(name = "remark", length=1000)
	private String remark; //备注

	public String getRptQuestionId() {
		return rptQuestionId;
	}

	public void setRptQuestionId(String rptQuestionId) {
		this.rptQuestionId = rptQuestionId;
	}

	public String getOptName() {
		return optName;
	}

	public void setOptName(String optName) {
		this.optName = optName;
	}

	public String getOptContent() {
		return optContent;
	}

	public void setOptContent(String optContent) {
		this.optContent = optContent;
	}

	public float getOptScore() {
		return optScore;
	}

	public void setOptScore(float optScore) {
		this.optScore = optScore;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
