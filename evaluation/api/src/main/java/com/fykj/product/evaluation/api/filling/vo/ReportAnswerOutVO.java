package com.fykj.product.evaluation.api.filling.vo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fykj.product.evaluation.api.filling.model.RptAnswerOption;
import com.fykj.product.evaluation.api.filling.model.RptResource;

public class ReportAnswerOutVO {

	private String id;

	private String rptQuestionId; //关联题目ID
	
	private String rptPubAnswerId; //关联答题ID
	
	private String answer; //问答题内容
	
	private BigDecimal answerNum;
	
	private List<RptAnswerOption> rptQueOptIds = new ArrayList<RptAnswerOption>(); //关联选项ID(有可能是多选)
	
	private List<RptResource> resources;
	
	private String questionType;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public List<RptAnswerOption> getRptQueOptIds() {
		return rptQueOptIds;
	}

	public void setRptQueOptIds(List<RptAnswerOption> rptQueOptIds) {
		this.rptQueOptIds = rptQueOptIds;
	}

	public List<RptResource> getResources() {
		return resources;
	}

	public void setResources(List<RptResource> resources) {
		this.resources = resources;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public BigDecimal getAnswerNum() {
		return answerNum;
	}

	public void setAnswerNum(BigDecimal answerNum) {
		this.answerNum = answerNum;
	}
	
}
