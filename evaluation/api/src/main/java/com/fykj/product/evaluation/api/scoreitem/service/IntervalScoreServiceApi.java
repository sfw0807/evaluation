package com.fykj.product.evaluation.api.scoreitem.service;

import java.util.List;

import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;

/**
 * ClassName: ScoreRolesService  
 * (评分规则管理)
 * @author zhangtian  
 * @version
 */
public interface IntervalScoreServiceApi {
	// 保存评分规则
	public void saveIntervalScore(List<IntervalScore> intervalScores) ;
	// 更新评分规则
	public void updateIntervalScore(List<IntervalScore> intervalScores, String scoreRuleType) ;
	// 删除评分规则
	public void deleteIntervalScore(List<IntervalScore> intervalScores) ;
	// 根据ID删除评分规则
	public void deleteIntervalScoreById(String id) ;
	// 批量删除得评分规则
	public void deleteIntervalScores(String[] ids) ;
	// 根据表单ID查询评分规则
	public List<IntervalScore> getIntervalScoreByScoreFormId(String formId) ;
	// 根据ID查询评分规则
	public IntervalScore getIntervalScoreById(String id) ;
}
