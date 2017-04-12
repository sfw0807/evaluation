package com.fykj.product.evaluation.modular.filling.service.impl;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.product.evaluation.api.filling.model.RptPublishAnswer;
import com.fykj.product.evaluation.api.filling.service.RptPubAnswerServiceApi;
import com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService;

@Service
public class RptPubAnswerServiceImpl extends ServiceSupport implements RptPubAnswerService,RptPubAnswerServiceApi {


	private SingleEntityManager<RptPublishAnswer> rptPublishAnswerImpl = SingleEntityManagerGetter.get()
			.getInstance(RptPublishAnswer.class);
	
	

	/* (non-Javadoc)
	 * @see com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService#saveScore(java.lang.String, float)
	 * 保存文件得分
	 */
	@Override
	public String saveScore(String rptPubAnsId, float score) {
		RptPublishAnswer pubAnswer = rptPublishAnswerImpl.getById(rptPubAnsId);
		pubAnswer.setScore(score);
		rptPublishAnswerImpl.saveOnly(pubAnswer);
		return pubAnswer.getId();
	}



	@Override
	public String savePubAnswer(RptPublishAnswer rptPublishAnswer) {
		
		List<RptPublishAnswer> rptPublishAns = rptPublishAnswerImpl
		.singleEntityQuery2()
		.conditionDefault()
		.equals("targetId", rptPublishAnswer.getTargetId())
		.ready()
		.order()
		.asc("createDate")
		.ready()
		.models();
		
		if(CollectionUtils.isNotEmpty(rptPublishAns)) {
			return rptPublishAns.get(0).getId();
		}
		
		rptPublishAnswerImpl.saveOnly(rptPublishAnswer);
		return rptPublishAnswer.getId();
	}



	@Override
	public void deletePubAnswer(String rptPubAnswerId) {
		rptPublishAnswerImpl.delete(rptPubAnswerId);
	}

	@Override
	public String getRptPubAnsIdBytargetId(String targetId, String projectId) {
		String sql = "SELECT distinct r.id as id FROM RptPublishAnswer r left join PublishReport pr on r.rptId = pr.id "
				+ " where r.targetId = :targetId and r.isAvailable = 1 and pr.projectId = :projectId "
				+ " order by r.createDate asc";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("targetId", targetId);
		params.put("projectId", projectId);
		
		List<String> rptPubAnsIds = jpqlQuery().setJpql(sql)
				.setParams(params)
				.models();
		
		if(CollectionUtils.isNotEmpty(rptPubAnsIds)) {
			return rptPubAnsIds.get(0);
		}
		
		return null;
		
	}


	@Override
	public void updateSubmitFlagByPk(String rptPubAnsId, String flagVaule) {
		RptPublishAnswer pubAnswer = rptPublishAnswerImpl.getById(rptPubAnsId);
		if (pubAnswer != null) {
			pubAnswer.setSubmitFlag(flagVaule);
			rptPublishAnswerImpl.saveOnly(pubAnswer);
		}

	}

	@Override
	public int countPubAnswerByProjectId(String projectId) {

		String sql = "select count(ra.id) from RptPublishAnswer ra inner join PublishReport pr" +
					" on ra.rptId = pr.id where pr.projectId = :projectId and ra.submitFlag = 1 ";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("projectId", projectId);

		int totalCount = jpqlQuery().setJpql(sql)
				.setParams(params)
				.model();
		return totalCount;
	}
}
