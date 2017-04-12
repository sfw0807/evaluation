package com.fykj.product.evaluation.modular.filling.service;

import com.fykj.product.evaluation.api.filling.model.RptPublishAnswer;

/**
 * @author Erik_Yim
 *
 * 答卷service
 */
public interface RptPubAnswerService {

	String savePubAnswer(RptPublishAnswer rptPublishAnswer);

	void updateSubmitFlagByPk(String rptPubAnsId, String flagVaule);
	
	void deletePubAnswer(String rptPubAnswerId);
	
	String saveScore(String rptPubAnsId, float score);
	
	String getRptPubAnsIdBytargetId(String targetId, String projectId);

	int countPubAnswerByProjectId(String projectId);
	
}
