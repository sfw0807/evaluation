package com.fykj.product.evaluation.modular.evaluating.itemscore.service;

import java.math.BigDecimal;
import java.util.List;

import com.fykj.product.evaluation.modular.evaluating.itemscore.model.ItemScore;
import com.fykj.product.evaluation.modular.evaluating.itemscore.vo.EvalTargetItemCriteriaInVO;
import com.fykj.product.evaluation.modular.evaluating.itemscore.vo.EvalTargetItemRecordVO;

public interface ItemScoreService {

	void addItemScore(String itemId,String evalTargetId,String projectId);
	
	void deleteItemScore(ItemScore itemScore);
	
	void deleteItemScoreById(String id);
	
	void deleteItemScores(String [] ids);
	
	ItemScore getItemScoreById(String id);
	
	void updateScore(String id ,BigDecimal score,ScoreType scoreType);
	
	void updateScore(String itemId,String evalTargetId,BigDecimal score,ScoreType scoreType);
	
	List<EvalTargetItemRecordVO> getAllEvalTargetByItem(EvalTargetItemCriteriaInVO evalTargetItemCriteriaInVO);
	
	List<ItemScore> getItemScoreByProjectId(String projectId);
	
}
