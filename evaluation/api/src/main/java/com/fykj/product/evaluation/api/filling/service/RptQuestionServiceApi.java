package com.fykj.product.evaluation.api.filling.service;

import java.util.List;

import com.fykj.product.evaluation.api.filling.model.RptQuestionOption;
import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.filling.vo.RptQueAnsStisWraperVO;

public interface RptQuestionServiceApi {

	
	String saveOrUpdateRptQuestion(ReportQuestionInVO reportQuestionInVO);
	
	void saveQuestionOptions(List<RptQuestionOption> options, String questionId);
	
	List<ReportQuestionOutVO> getRptQuestionByScoreItemId(String scoreItemId, String queryType);
	
	ReportQuestionOutVO getRptQuestionByPK(String queId);
	
	void deleteQuestionByScoreItemId(String scoreItemId);
	
	void deleteQuestionByPK(String queId);

	/**
	 * 获取答案及题目
	 * @param scoreItemId
	 * @param targetId
	 * @param projectId
	 * @return
	 */
	ReportQueAnsWraperVO getRptQueAnsByScoreItemId(String scoreItemId, String targetId, String projectId);
	
	
	/**
	 * 报表接口
	 * @param scoreItemId
	 * @return
	 */
	RptQueAnsStisWraperVO getQueAnsStisReport(String scoreItemId);
}
