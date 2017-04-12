package com.fykj.product.evaluation.api.filling.vo;

import java.util.List;

import com.fykj.platform.kernel._c.model.JOutputModel;
import com.fykj.product.evaluation.api.filling.model.RptQuestionOption;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;

public class ReportQuestionOutVO implements JOutputModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7850047256136500152L;

	private String id;
	
	private int questionCode; //编号
	
	private String questionType; //题目类型
	
	private String questionContent;
	
	private float totalScore; //总分
	
	private int optNumber; //选项数量
	
	private int requiredStatus; //必选
	
	private String scoreItemId; //得分项关联
	
	private String remark; //描述
	
	private List<RptQuestionOption> options;
	
	private List<IntervalScore> intervalScores ;
	
	private boolean hasIntervalScores ;
	
	private String rptId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public int getOptNumber() {
		return optNumber;
	}

	public void setOptNumber(int optNumber) {
		this.optNumber = optNumber;
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

	public List<RptQuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<RptQuestionOption> options) {
		this.options = options;
	}

	public List<IntervalScore> getIntervalScores() {
		return intervalScores;
	}

	public void setIntervalScores(List<IntervalScore> intervalScores) {
		this.intervalScores = intervalScores;
	}

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public boolean isHasIntervalScores() {
		return hasIntervalScores;
	}

	public void setHasIntervalScores(boolean hasIntervalScores) {
		this.hasIntervalScores = hasIntervalScores;
	}
	
}
