package com.fykj.product.evaluation.api.scoreitem.service;

import java.util.List;

import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;

/**
 * ClassName: FillScoreItemServiceApi  
 * (填报得分项)
 * @author zhangtian  
 * @version
 */
public interface FillScoreItemServiceApi {
	// 获取得分项下所有的填报项
	public List<ReportQuestionOutVO> getFillScoreItemOptions(String scoreItemId, String queryType) ;

	// 保存个人填报答案
	public void saveScoreItemOptions(ReportAnswerInWraperVO wraper, String rptId, String targetId);
	// 根据ScoreItemId查询填报详情
	public ReportAnswerOutWraperVO getAnswerByScoreId(String targetId, String scoreItemId, String projectId);

	// 获取得分项下所有的填报项--带自填答案
	public ReportQueAnsWraperVO getFillScoreItemOptionsAll(String scoreItemId, String targetId, String projectId);
}
