package com.fykj.product.evaluation.api.filling.vo;

public class ReportQueAnsOutVO extends ReportQuestionOutVO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2186893516048041664L;

	private ReportAnswerOutVO ansOutVO = new ReportAnswerOutVO() ;

	public ReportAnswerOutVO getAnsOutVO() {
		return ansOutVO;
	}

	public void setAnsOutVO(ReportAnswerOutVO ansOutVO) {
		this.ansOutVO = ansOutVO;
	}
	
}
