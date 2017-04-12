package com.fykj.product.evaluation.api.filling.service;

import java.util.List;

import com.fykj.product.evaluation.api.filling.model.ReportAnswer;
import com.fykj.product.evaluation.api.filling.model.RptAnswerOption;
import com.fykj.product.evaluation.api.filling.vo.CalcuateScoreOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutWraperVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceCriteriaInVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceInVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceWraperOutVO;

/**
 * @author Erik_Yim
 *
 * 答题service
 */
public interface RptAnswerServiceApi {
	
	String saveOrUpdateAnswer(ReportAnswer rptAnswer, List<RptAnswerOption> rptAnsOpts, String queType);
	
	@Deprecated
	void saveOrUpdateAnswers(List<ReportAnswerInVO> answers);

	void saveOrUpdateAnswers(ReportAnswerInWraperVO wraper);
	
	
	/**
	 * 查看填报答案详情
	 * @param scoreItemId
	 * @param userId
	 * @return
	 */
	ReportAnswerOutWraperVO getAnswerByScoreId(String scoreItemId, String targetId, String projectId);
	
	
	/**
	 *  保存附件类问题
	 * @param vo
	 */
	void saveOrUpdateRptResource(RptResourceInVO vo);
	
	RptResourceWraperOutVO getRptResourceList(RptResourceCriteriaInVO criteria);
	
	CalcuateScoreOutVO calcScoreByScoreItem(String targetId, String scoreItemId);
}
