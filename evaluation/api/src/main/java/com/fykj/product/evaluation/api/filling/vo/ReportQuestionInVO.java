package com.fykj.product.evaluation.api.filling.vo;

import java.util.List;

import com.fykj.product.evaluation.api.filling.model.ReportDetail;
import com.fykj.product.evaluation.api.filling.model.ReportQuestion;
import com.fykj.product.evaluation.api.filling.model.RptQuestionOption;

public class ReportQuestionInVO extends ReportQuestion{

	/**
	 * 
	 */
	private static final long serialVersionUID = -764043935317036358L;

	private String queId;
	
	private List<RptQuestionOption> options;
	
	private ReportDetail rptDetail;

	public List<RptQuestionOption> getOptions() {
		return options;
	}

	public void setOptions(List<RptQuestionOption> options) {
		this.options = options;
	}

	public ReportDetail getRptDetail() {
		return rptDetail;
	}

	public void setRptDetail(ReportDetail rptDetail) {
		this.rptDetail = rptDetail;
	}

	public String getQueId() {
		return queId;
	}

	public void setQueId(String queId) {
		this.queId = queId;
	}
}
