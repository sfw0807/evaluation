package com.fykj.product.evaluation.modular.evaluating.scoreitem.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.service.IntervalScoreServiceApi;
import com.fykj.product.evaluation.common.constant.ScoreRulesType;
/**
 * ClassName: IntervalScoreServiceImpl  
 * (接口表即选项表中记录评分规则--累加/区间记分)
 * @author zhangtian  
 * @version
 */
@Service
public class IntervalScoreServiceImpl implements IntervalScoreServiceApi {

	// 得分项基本信息
	private SingleEntityManager<IntervalScore> internalIntervalScoreServiceImpl = SingleEntityManagerGetter
																			.get()
																			.getInstance(IntervalScore.class) ;

	@Override
	public void saveIntervalScore(List<IntervalScore> intervalScores) {
		internalIntervalScoreServiceImpl.saveAllOnly(intervalScores);
	}

	@Override
	public void updateIntervalScore(List<IntervalScore> intervalScores,String scoreRuleType) {
		// 删除区间评分规则
		deleteIntervalScore(intervalScores);
		if(StringUtils.equals(scoreRuleType, ScoreRulesType.SECTION.getKey())) {// 区间评分
			// 重新插入区间评分规则
			saveIntervalScore(intervalScores);
		}
		
		// 更新选项表评分类别字段（累加/区间）
		// TODO
	}

	@Override
	public void deleteIntervalScore(List<IntervalScore> intervalScores) {
		internalIntervalScoreServiceImpl.deleteAllByModels(intervalScores);
	}

	@Override
	public void deleteIntervalScoreById(String id) {
		internalIntervalScoreServiceImpl.delete(id);
	}

	@Override
	public void deleteIntervalScores(String[] ids) {
		for(String id : ids) {
			deleteIntervalScoreById(id);
		}
	}

	@Override
	public List<IntervalScore> getIntervalScoreByScoreFormId(String formId) {
		return internalIntervalScoreServiceImpl
					.singleEntityQuery2()
					.conditionDefault()
					.equals("formId", formId)
					.ready()
					.order()
					.asc("createDate")
					.ready()
					.models();
	}

	@Override
	public IntervalScore getIntervalScoreById(String id) {
		return internalIntervalScoreServiceImpl.getById(id);
	}
}
