package com.fykj.product.evaluation.api.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;

@Entity
@Table(name = "t_rpt_question")
public class ReportQuestion extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7560688299227863649L;
	
	@Column(name = "question_code", length=40)
	private int questionCode;
	
	@Column(name = "question_type")
	private String questionType;
	
	@Column(name = "question_content", length=2000)
	private String questionContent;
	
	@Column(name = "total_score")
	private float totalScore = 0;
	
	@Column(name = "input_type")
	private int inputType = RptCommonConstants.DEFAULT_INPUT_TYPE; //默认非必选
	
	@Column(name = "required_status")
	private int requiredStatus = RptCommonConstants.DEFAULT_REQUIRED_STATUS;
	
	@Column(name = "score_item_id", length=40)
	private String scoreItemId; //得分项关联
	
	@Column(name = "remark", length=1000)
	private String remark;

	public int getQuestionCode() {
		return questionCode;
	}

	public void setQuestionCode(int questionCode) {
		this.questionCode = questionCode;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getQuestionContent() {
		return questionContent;
	}

	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}

	public float getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(float totalScore) {
		this.totalScore = totalScore;
	}

	public int getRequiredStatus() {
		return requiredStatus;
	}

	public void setRequiredStatus(int requiredStatus) {
		this.requiredStatus = requiredStatus;
	}

	public String getScoreItemId() {
		return scoreItemId;
	}

	public void setScoreItemId(String scoreItemId) {
		this.scoreItemId = scoreItemId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getInputType() {
		return inputType;
	}

	public void setInputType(int inputType) {
		this.inputType = inputType;
	}
	
}
