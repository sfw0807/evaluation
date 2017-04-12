package com.fykj.product.evaluation.api.filling.service;

import com.fykj.product.evaluation.api.filling.model.RptPublishAnswer;

/**
 * @author Erik_Yim
 *
 * 答卷service
 */
public interface RptPubAnswerServiceApi {

	String savePubAnswer(RptPublishAnswer rptPublishAnswer);
	
	void deletePubAnswer(String rptPubAnswerId);
	
	String saveScore(String rptPubAnsId, float score);
	
	String getRptPubAnsIdBytargetId(String targetId, String projectId);
	
}
