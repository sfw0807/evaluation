package com.fykj.product.evaluation.api.filling.vo;

import java.util.List;

public class RptQueAnsStisWraperVO {
	
	private List<ReportQuestionOutVO> queList;
	
	private List<ReportAnswerOutWraperVO> ansList;

	public List<ReportQuestionOutVO> getQueList() {
		return queList;
	}

	public void setQueList(List<ReportQuestionOutVO> queList) {
		this.queList = queList;
	}

	public List<ReportAnswerOutWraperVO> getAnsList() {
		return ansList;
	}

	public void setAnsList(List<ReportAnswerOutWraperVO> ansList) {
		this.ansList = ansList;
	}

}
