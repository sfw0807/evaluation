package com.fykj.product.evaluation.api.filling.vo;

import java.math.BigDecimal;
import java.util.List;

public class ReportAnswerInVO {
	
	private String id;

	private String rptQuestionId; //关联题目ID
	
	private String rptPubAnswerId; //关联答题ID
	
	private String answer; //问答题内容
	
	private float score;
	
	private List<String> rptQueOptIds; //关联选项ID(有可能是多选)
	
	private String questionType;
	
	private String scoreItemId;
	
	private BigDecimal answerNum;

	public String getRptQuestionId() {
		return rptQuestionId;
	}

	public void setRptQuestionId(String rptQuestionId) {
		this.rptQuestionId = rptQuestionId;
	}

	public String getRptPubAnswerId() {
		return rptPubAnswerId;
	}

	public void setRptPubAnswerId(String rptPubAnswerId) {
		this.rptPubAnswerId = rptPubAnswerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public List<String> getRptQueOptIds() {
		return rptQueOptIds;
	}

	public void setRptQueOptIds(List<String> rptQueOptIds) {
		this.rptQueOptIds = rptQueOptIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getScoreItemId() {
		return scoreItemId;
	}

	public void setScoreItemId(String scoreItemId) {
		this.scoreItemId = scoreItemId;
	}

	public BigDecimal getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(BigDecimal answerNum) {
		this.answerNum = answerNum;
	}
	
}
