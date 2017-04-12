package com.fykj.product.evaluation.modular.filling.service;

import java.util.List;

import com.fykj.product.evaluation.api.filling.model.ReportAnswer;
import com.fykj.product.evaluation.api.filling.model.RptAnswerOption;
import com.fykj.product.evaluation.api.filling.vo.CalcuateScoreOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutWraperVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceCriteriaInVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceInVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceWraperOutVO;

/**
 * @author Erik_Yim
 *
 * 答题service
 */
public interface RptAnswerService {
	
	String saveOrUpdateAnswer(ReportAnswer rptAnswer, List<RptAnswerOption> rptAnsOpts, String queType);
	
	@Deprecated
	void saveOrUpdateAnswers(List<ReportAnswerInVO> answers);

	void saveOrUpdateAnswers(ReportAnswerInWraperVO wraper);
	
	ReportAnswerOutWraperVO getAnswerByScoreId(String scoreItemId, String targetId, String projectId); //TODO 根据userid查询的都换成targetid
	
	ReportAnswerOutVO getAnswerByQuestionId(String queId, String targetId, String projectId);
	
	List<ReportAnswerOutWraperVO> getAnswersByScoreId(String scoreItemId, String type, String projectId);
	
	/**
	 *  保存附件类问题
	 * @param vo
	 */
	void saveOrUpdateRptResource(RptResourceInVO vo);
	
	
	RptResourceWraperOutVO getRptResourceList(RptResourceCriteriaInVO criteria);
	
	CalcuateScoreOutVO calcScoreByScoreItem(String targetId, String scoreItemId);
}
