package com.fykj.product.evaluation.api.filling.vo;

import java.util.List;

import com.fykj.product.evaluation.api.filling.model.RptRemark;

public class ReportAnswerOutWraperVO {
	
	private List<ReportAnswerOutVO> answerList;
	
	private String rptPubAnswerId;
	
	private RptRemark remark;
	
	private String targetId;

	public List<ReportAnswerOutVO> getAnswerList() {
		return answerList;
	}

	public void setAnswerList(List<ReportAnswerOutVO> answerList) {
		this.answerList = answerList;
	}

	public String getRptPubAnswerId() {
		return rptPubAnswerId;
	}

	public void setRptPubAnswerId(String rptPubAnswerId) {
		this.rptPubAnswerId = rptPubAnswerId;
	}

	public RptRemark getRemark() {
		return remark;
	}

	public void setRemark(RptRemark remark) {
		this.remark = remark;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

}
