package com.fykj.product.evaluation.modular.evaluating.itemscore.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.JStringUtils;
import com.fykj.product.evaluation.modular.evaluating.itemscore.model.ItemScore;
import com.fykj.product.evaluation.modular.evaluating.itemscore.service.ItemScoreService;
import com.fykj.product.evaluation.modular.evaluating.itemscore.service.ScoreType;
import com.fykj.product.evaluation.modular.evaluating.itemscore.vo.EvalTargetItemCriteriaInVO;
import com.fykj.product.evaluation.modular.evaluating.itemscore.vo.EvalTargetItemRecordVO;
import com.fykj.product.evaluation.modular.evaluating.project.service.ProjectService;

@Service
public class ItemScoreServiceImpl extends ServiceSupport 
implements ItemScoreService {

	private SingleEntityManager<ItemScore> internalItemScoreServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(ItemScore.class);

	@Autowired
	private ProjectService projectService;
	
	@Override
	public void addItemScore(String itemId, String evalTargetId,String projectId) {
		if (JStringUtils.isNullOrEmpty(evalTargetId)) {
			throw new BusinessException("被评对象不能为空");
		}
		if (JStringUtils.isNullOrEmpty(itemId)) {
			throw new BusinessException("得分项不能为空");
		}
		if (JStringUtils.isNullOrEmpty(projectId)) {
			throw new BusinessException("项目ID不能为空");
		}
		ItemScore itemScore=new ItemScore();
		itemScore.setItemId(itemId);
		itemScore.setEvalTargetId(evalTargetId);
		itemScore.setProjectId(projectId);
		internalItemScoreServiceImpl.saveOnly(itemScore); 
	}

	@Override
	public void deleteItemScore(ItemScore itemScore) {
		internalItemScoreServiceImpl.delete(itemScore);
	}

	@Override
	public void deleteItemScoreById(String id) {
		internalItemScoreServiceImpl.delete(id);
	}

	@Override
	public ItemScore getItemScoreById(String id) {
		return internalItemScoreServiceImpl.getById(id);
	}
	
	@Override
	public void deleteItemScores(String[] ids) {
		for (String id : ids) {
			deleteItemScoreById(id);
		}
	}

	@Override
	public void updateScore(String id, BigDecimal score, ScoreType scoreType) {
		
		ItemScore itemScore=internalItemScoreServiceImpl.getById(id);
		_setScore(itemScore, score, scoreType);
		internalItemScoreServiceImpl.updateOnly(itemScore);
	}

	private void _setScore(ItemScore itemScore, BigDecimal score, ScoreType scoreType) {
		switch (scoreType) {
			case SELF:
				itemScore.setSelfScore(score);
				break;
			case EXPERT:
				itemScore.setExpertScore(score);
				break;
			case END:
				itemScore.setEndScore(score);
				break;	
			case FILL:
				itemScore.setFillScore(score);
				break;
			case SURVEY:
				itemScore.setSurveyScore(score);
				break;
			case FINAL:
				itemScore.setFinalScore(score);
				break;
			default:
				break;
		}
	}
	
	private ItemScore itemScore(String itemId,String evalTargetId){
		return internalItemScoreServiceImpl.singleEntityQuery2()
				.conditionDefault()
				.equals("evalTargetId", evalTargetId)
				.equals("itemId",itemId)
				.ready().model();
	}
	
	
	
	
	@Override
	public void updateScore(String itemId, String evalTargetId, BigDecimal score, ScoreType scoreType) {
		if (JStringUtils.isNullOrEmpty(evalTargetId)) {
			throw new BusinessException("被评对象不能为空");
		}
		if (JStringUtils.isNullOrEmpty(itemId)) {
			throw new BusinessException("得分项不能为空");
		}
		
		ItemScore itemScore=itemScore(itemId, evalTargetId);
		if(itemScore==null){
			itemScore=new ItemScore();
			itemScore.setItemId(itemId);
			itemScore.setEvalTargetId(evalTargetId);
			_setScore(itemScore, score, scoreType);
			internalItemScoreServiceImpl.saveOnly(itemScore);
		}else{
			_setScore(itemScore, score, scoreType);
			internalItemScoreServiceImpl.updateOnly(itemScore);
		}
		
	}

	
	
	@Override
	public List<EvalTargetItemRecordVO> getAllEvalTargetByItem(EvalTargetItemCriteriaInVO evalTargetItemCriteriaInVO) {
	
		Map<String, Object> params=new HashMap<>();
		
		String sql=" select a.evalTargetId as evalTargetId , "
				+ " a.itemId as itemId , "
				+ " a.selfScore as selfScore ,"
				+ " a.expertScore as expertScore ,"
				+ " a.endScore as endScore ,"
				+ " b.name as name ,"
				+ " b.category as category "
				+ " from ItemScore a ,  EvalTarget b "
				+ " where a.isAvailable=1 and b.isAvailable=1 "
				+ " and a.evalTargetId=b.id "
				+ " and a.itemId= :itemId "
				+ " and b.name like :name "; 
		
		if(JStringUtils.isNotNullOrEmpty(evalTargetItemCriteriaInVO.getCategory())){
			sql=sql+" and b.category= :category " ;
			params.put("category", evalTargetItemCriteriaInVO.getCategory());
		}
		
		sql=sql+" order by endScore , expertScore";
		
		params.put("itemId", evalTargetItemCriteriaInVO.getItemId());
		params.put("name", "%"+
		(JStringUtils.isNullOrEmpty(evalTargetItemCriteriaInVO.getName())?""
				:evalTargetItemCriteriaInVO.getName())+"%");
		
		return jpqlQuery().setJpql(sql)
				.setParams(params)
				.models(EvalTargetItemRecordVO.class);
	}
	
	
	@Override
	public List<ItemScore> getItemScoreByProjectId(String projectId) {
	
		return internalItemScoreServiceImpl.singleEntityQuery2()
				.conditionDefault()
				.equals("projectId", projectId)
				.ready()
				.models();

	}
	
	
	
	
	
	
	
	
	
	
	
}
