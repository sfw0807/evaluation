package com.fykj.product.evaluation.modular.evaluating.mitem.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import com.fykj.product.evaluation.modular.evaluating.mitem.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.JStringUtils;
import com.fykj.product.evaluation.api.scoreitem.model.ScoreItem;
import com.fykj.product.evaluation.api.scoreitem.service.ScoreItemServiceApi;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.model.EvalTarget;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.service.EvalTargetService;
import com.fykj.product.evaluation.modular.evaluating.mitem.model.MeasureItem;
import com.fykj.product.evaluation.modular.evaluating.mitem.service.MeasureItemService;
import com.fykj.sample.tree.JTreeNode;
import com.fykj.sample.tree.JTreeNodeMenu;
import com.fykj.sample.tree.JTreeUtils;

@Service
public class MeasureItemServiceImpl extends ServiceSupport 
implements MeasureItemService {

	private SingleEntityManager<MeasureItem> internalMeasureItemServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(MeasureItem.class);
	
	private SingleEntityManager<EvalTarget> internalEvalTargetServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(EvalTarget.class);
	
	@Autowired
	private EvalTargetService evalTargetService;
	

	@Autowired
	private ScoreItemServiceApi scoreItemServiceApi;
	
	@Override
	public void saveMeasureItem(MeasureItem measureItem) {
		if (JStringUtils.isNullOrEmpty(measureItem.getName())) {
			throw new BusinessException("名称不能为空");
		}
		internalMeasureItemServiceImpl.saveOnly(measureItem);
	}

//	@Override
//	public boolean exists(String code) {
//		MeasureItem param = internalMeasureItemServiceImpl.singleEntityQuery2().conditionDefault().equals("code", code)
//				.ready().model();
//		return param != null;
//	}

	@Override
	public void updateMeasureItem(MeasureItem measureItem) {
		MeasureItem dbMeasureItem = getMeasureItemById(measureItem.getId());
		// dbMeasureItem.setCode(measureItem.getCode());
		dbMeasureItem.setScore(measureItem.getScore());
		dbMeasureItem.setDescription(measureItem.getDescription());
		internalMeasureItemServiceImpl.updateOnly(dbMeasureItem);
	}

	@Override
	public void deleteMeasureItem(MeasureItem measureItem) {
		internalMeasureItemServiceImpl.delete(measureItem);
	}

	@Override
	public void deleteMeasureItemById(String id) {
		internalMeasureItemServiceImpl.delete(id);
	}

	@Override
	public MeasureItem getMeasureItemById(String id) {
		return internalMeasureItemServiceImpl.getById(id);
	}

	@Override
	public JPage<MeasureItem> getMeasureItems(MeasureItemCriteriaInVO measureItemCriteriaInVO,
			SimplePageRequest simplePageRequest) {
		return internalMeasureItemServiceImpl.singleEntityQuery2().conditionDefault()
				.likes("name", 
						JStringUtils.isNullOrEmpty(measureItemCriteriaInVO.getName())?null:
							measureItemCriteriaInVO.getName())
				.equals("parent", 
						JStringUtils.isNullOrEmpty(measureItemCriteriaInVO.getParent())?null:
							measureItemCriteriaInVO.getParent())
				.equals("projectId", 
						JStringUtils.isNullOrEmpty(measureItemCriteriaInVO.getProjectId())?null:
							measureItemCriteriaInVO.getProjectId())
				.ready().modelPage(simplePageRequest);
	}

	@Override
	public void deleteMeasureItems(String[] ids) {
		for (String id : ids) {
			deleteMeasureItemById(id);
		}
	}
	
	@Override
	public List<JTreeNode> getMeasureItemTree(String projectId) {
		
		String sql= internalMeasureItemServiceImpl.selectCause()
				+" from MeasureItem  "
				+ " where projectId = :projectId and isAvailable=1 order by createDate asc ";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("projectId", projectId);
		
		List<MeasureItemDetailOutVO> list = 
				jpqlQuery().setJpql(sql)
				.setParams(params)
				.models(MeasureItemDetailOutVO.class);
		return JTreeUtils.buildTree("-1", list, JTreeNodeMenu.class);
	}
	
	@Override
	public boolean ifContainsItem(String mitemId) {
		
		return internalMeasureItemServiceImpl.singleEntityQuery2()
		.conditionDefault().equals("parent", mitemId)
		.ready().count()>0;
	}

	@Override
	public List<MeasureItem> children(String measureItemId, boolean recursive) {
		MeasureItem measureItem=internalMeasureItemServiceImpl.singleEntityQuery2()
				.active(measureItemId).ready().model();
		return children(measureItemId, measureItem.getProjectId(),recursive);
	}
	
	private class Node {
		private MeasureItem item;
		
		private Node parent;
		
		private List<Node> children=new ArrayList<>();
	}
	
	@Override
	public List<MeasureItem> children(String measureItemId, String projectId, boolean recursive) {
		if(!recursive){
			return internalMeasureItemServiceImpl
			.singleEntityQuery2()
			.conditionDefault()
			.equals("parent", measureItemId)
			.equals("projectId", projectId)
			.ready().models();
		}
		
		List<MeasureItem> measureItems=internalMeasureItemServiceImpl
				.singleEntityQuery2()
				.conditionDefault()
				.equals("projectId", projectId)
				.ready().models();
		List<MeasureItem> items=new ArrayList<>();
		Map<String, Node> map=new HashMap<>();
		Node _root=new Node();
		map.put("-1", _root);
		
		LinkedBlockingQueue<MeasureItem> queue=new LinkedBlockingQueue<>(measureItems);
		
		while(!queue.isEmpty()){
			MeasureItem item=queue.poll();
			Node node=map.get(item.getId());
			if(node==null){
				node=new Node();
				node.item=item;
			}
			Node parent=map.get(item.getParent());
			if(parent!=null){
				parent.children.add(node);
				node.parent=parent;
				map.put(item.getId(), node);
			}else{
				if(!map.containsKey(item.getId())){
					// next chance
					queue.offer(item);
					map.put(item.getId(), node);
				}else{
					throw new IllegalStateException("CANNOT CONSTRUCT TREE VIEW");
				}
			}
			
		}
		Node node=map.get(measureItemId);
		 _children(node, items);
		return items;
	}
	
	private void _children(Node node,List<MeasureItem> items){
		if(node==null) return;
		for(Node nd:node.children){
			items.add(nd.item);
			_children(nd, items);
		}
	}
	
	@Override
	public List<MeasureItemRate> getMeasureItemRates(final MeasureItemRateCriteria measureItemRateCriteria) {
		
		Map<String, Object> params=new HashMap<>();
		params.put("parent", measureItemRateCriteria.getMeasureItemId());
		params.put("projectId", measureItemRateCriteria.getProjectId());
		
		List<MeasureItem> measureItems1=
				
				jpqlQuery().setJpql(
						 " from  MeasureItem a "
						+ " where a.isAvailable=1"
						+ " and a.parent= :parent"
						+ " and a.projectId =:projectId ")
				.setParams(params)
				.models();
		
		List<MeasureItemRate> measureItemRates=new ArrayList<>(measureItems1.size());
		for(MeasureItem measureItem:measureItems1){
			MeasureItemRate measureItemRate=new MeasureItemRate();
			measureItemRate.setId(measureItem.getId());
			measureItemRate.setName(measureItem.getName());
			measureItemRate.setScore(String.valueOf(measureItem.getScore()));
			measureItemRates.add(measureItemRate);
		}
		
		String part="select "
						+" avg(a.final_score) as avgScore , "
						+" avg(a.final_score)/(select count(0) from t_eval_target_project b ,t_eval_target c  where b.is_available=1 and  b.projectid= :projectId and c.id=b.eval_target_id and c.name like :name"
						
						+(JStringUtils.isNullOrEmpty(measureItemRateCriteria.getCategory())? "" : " and c.category= :category  ")
						
						+ "  ) rate, "
						+" '%s' as mitemId "
						+" from t_item_score a " 
						+" where a.is_available=1 "
						+" and a.eval_targetid in ( "
						+" select b.eval_target_id from t_eval_target_project b , t_eval_target c "
						+" where b.is_available=1 and  b.projectid= :projectId and c.id=b.eval_target_id and c.name like :name "
						
						+ (JStringUtils.isNullOrEmpty(measureItemRateCriteria.getCategory())? "" : " and c.category= :category  ")
						
						+" ) "
						+" and a.itemid in ( "
						+" select c.id from t_score_item c  "
						+" where c.is_available=1 and c.parent_quota in ( %s ) "
						+" ) ";
		
		String sql="";
		params=new HashMap<>();
		params.put("projectId", measureItemRateCriteria.getProjectId());
		int i=0;
		for(MeasureItemRate itemRate:measureItemRates){
			List<MeasureItem> measureItems=  children(itemRate.getId(), true);
			List<String> ids=new ArrayList<>();
			for(MeasureItem item:measureItems){
				ids.add(item.getId());
			}
			ids.add(itemRate.getId());
			String key="mitem"+i++;
			params.put(key, ids);
			sql =sql+(JStringUtils.isNullOrEmpty(sql)?  " " : " union all " )
					+String.format(part, itemRate.getId(),":"+key);
		}
		
		params.put("name", "%"+
				(JStringUtils.isNullOrEmpty(measureItemRateCriteria.getName())? "" : measureItemRateCriteria.getName())
				+"%");
		params.put("category", measureItemRateCriteria.getCategory());
		
		List<?> result= nativeQuery().setSql(sql)
		.setParams(params)
		.models();
		for(MeasureItemRate itemRate:measureItemRates){
			for(Object rs : result){
				Object[] _rs=(Object[])rs;
				if(itemRate.getId().equals(_rs[2])){
					
					String avg=_rs[0]==null?"0":_rs[0].toString();
					if(JStringUtils.isNotNullOrEmpty(avg)
							&& avg.indexOf(".")!=-1){
						avg=avg.substring(0, avg.indexOf(".")+3);
					}
					itemRate.setAvgScore(avg);
					String avgRate=_rs[1]==null?"0":_rs[1].toString();
					if(JStringUtils.isNotNullOrEmpty(avgRate)
							&& avgRate.indexOf(".")!=-1){
						avgRate=avgRate.substring(0, avgRate.indexOf(".")+3);
					}
					itemRate.setScoreRate(avgRate);
				}
			}
		}
		return measureItemRates;
	}
	
	
	@Override
	public List<MeasureTargetScore> getMeasureTargetScores(String measureItemId, String projectId,
			String[] evalTargetIds) {
		List<MeasureItem> measureItems=  children(measureItemId, true);
		List<String> ids=new ArrayList<>();
		for(MeasureItem item:measureItems){
			ids.add(item.getId());
		}
		ids.add(measureItemId);
		List<ScoreItem> scoreItems= scoreItemServiceApi.getScoreItemByItemMeasureIds(ids);
		List<String> scoreItemIds=new ArrayList<>();
		for(ScoreItem scoreItem:scoreItems){
			scoreItemIds.add(scoreItem.getId());
		}
		scoreItemIds.add("");
		
		String sql="select eval_targetid as evalTargetId , "
					+" b.name as name,   "
					+" sum(survey_score) as wjScore ,"
					+" sum(fill_score) as tbScore, "
					+" sum(final_score) as sumScore "
					+" from t_item_score a , t_eval_target  b  "
					+" where a.is_available=1 and  a.eval_targetid=b.id  "
					+" and eval_targetid in ( :evalTargetId ) "
					+" and itemid in ( :itemId  )"
					+" group by eval_targetid";
		Map<String, Object> params=new HashMap<>();
		params.put("evalTargetId", Arrays.asList(evalTargetIds));
		params.put("itemId", scoreItemIds);
		
		List<Object> list= nativeQuery().setSql(sql)
		.setParams(params)
		.models();
		
		List<MeasureTargetScore> measureTargetScores=new ArrayList<>();
		for(Object obj:list){
			Object[] objs=(Object[])obj;
			MeasureTargetScore measureTargetScore=new MeasureTargetScore();
			measureTargetScore.setEvalTargetId((String) objs[0]);
			measureTargetScore.setName((String) objs[1]);
			measureTargetScore.setWjScore((objs[2]==null?"0":String.valueOf(objs[2])));
			measureTargetScore.setTbScore(objs[3]==null?"0":String.valueOf(objs[3]));
			measureTargetScore.setSumScore(objs[4]==null?"0":String.valueOf(objs[4]));
			measureTargetScores.add(measureTargetScore);
		}
		return measureTargetScores;
	}
	
	
	private MeasureItemCompRate _one(String measureItemId, String projectId,
			String evalTargetId){
		List<MeasureItem> measureItems=  children(measureItemId, true);
		List<String> ids=new ArrayList<>();
		for(MeasureItem item:measureItems){
			ids.add(item.getId());
		}
		ids.add(measureItemId);
		List<ScoreItem> scoreItems= scoreItemServiceApi.getScoreItemByItemMeasureIds(ids);
		List<String> scoreItemIds=new ArrayList<>();
		for(ScoreItem scoreItem:scoreItems){
			scoreItemIds.add(scoreItem.getId());
		}
		scoreItemIds.add("");
		
		String sql="select "
				+" (select s.score from t_measure_item s where s.is_available=1 and s.id= :measureId ) as score"
				+" ,sum(final_score) as finalScore   "
				+" from t_item_score a where a.is_available=1 "
				+" and a.eval_targetid in ( :evalTargetId )   "
				+" and a.itemid in ( :itemId  ) ";
		Map<String, Object> params=new HashMap<>();
		params.put("evalTargetId", Arrays.asList(new String[]{evalTargetId}));
		params.put("itemId", scoreItemIds);
		params.put("measureId", measureItemId);
		
		List<?> basic=nativeQuery()
								.setSql(sql)
								.setParams(params)
								.models();
		
		EvalTarget evalTarget=evalTargetService.getEvalTargetById(evalTargetId);
		
		List<EvalTarget> evalTargets=internalEvalTargetServiceImpl.singleEntityQuery2()
				.conditionDefault().equals("category", evalTarget.getCategory()	)
				.ready().models();
		List<String> sm=new ArrayList<>();
		for(EvalTarget evalTarget2:evalTargets){
			sm.add(evalTarget2.getId());
		}
		
		params=new HashMap<>();
		params.put("evalTargetId",sm);
		params.put("itemId", scoreItemIds);
		params.put("measureId", measureItemId);
		
		List<?> smBasic=nativeQuery()
				.setSql(sql)
				.setParams(params)
				.models();
		
		Object[] objects=(Object[]) (basic.isEmpty()?null:basic.get(0));
		MeasureItemCompRate compRate=new MeasureItemCompRate();
		BigDecimal score= BigDecimal.valueOf((Double)objects[0]);
		BigDecimal finalScore= decimalCast(objects[1]);
		compRate.setScore(String.valueOf(score));
		compRate.setFinalScore(String.valueOf(finalScore));
		compRate.setScoreRate(String.valueOf(finalScore.divide(score, 2, RoundingMode.HALF_EVEN)));

		Object[] smObjects=(Object[]) (smBasic.isEmpty()?null:smBasic.get(0));
		BigDecimal smFinalScore= decimalCast(smObjects[1]);
		compRate.setSmScoreRate(String.valueOf(
				smFinalScore.divide(score.multiply(BigDecimal.valueOf(sm.size())), 2, RoundingMode.HALF_EVEN)
				));
		return compRate;
	}
	
	
	@Override
	public List<MeasureItemCompRate> getMeasureItemCompRate(String measureItemId, String projectId,
			String evalTargetId) {
		List<MeasureItemCompRate> rates=new ArrayList<>();
		List<MeasureItem> measureItems=
				children(measureItemId, projectId, false);
		for(MeasureItem item:measureItems){
			MeasureItemCompRate rate= _one(item.getId(), projectId, evalTargetId);
			rate.setId(item.getId());
			rate.setName(item.getName());
			rate.setProjectId(projectId);
			rate.setMeasureId(item.getId());
			rates.add(rate);
		}
		return rates;
	}


	@Override
	public List<MeasureItemRateSecLvVO> getMeasureItemCompRateLv2(String projectId, String evalTargetId) {
		List<MeasureItemRateSecLvVO> measureItemRateSecLvVOS = new ArrayList<>();
		String measureId = "-1";
		List<MeasureItemCompRate> lv1List = getMeasureItemCompRate(measureId, projectId, evalTargetId);
		for(MeasureItemCompRate lv1Item : lv1List){
			List<MeasureItemCompRate> lv2List = getMeasureItemCompRate(lv1Item.getMeasureId(), projectId, evalTargetId);
			if(lv2List.size() > 0){
				MeasureItemRateSecLvVO measureItemRateSecLvVO = new  MeasureItemRateSecLvVO();
				measureItemRateSecLvVO.setLvlItem(lv1Item);
				measureItemRateSecLvVO.setLv2Items(lv2List);
				measureItemRateSecLvVOS.add(measureItemRateSecLvVO);
			}

		}
		return measureItemRateSecLvVOS;
	}

	/**
	 * cast object to BigDecimal
	 * @param obj
	 * @return
	 */
	private BigDecimal decimalCast(Object obj){
		BigDecimal result = BigDecimal.valueOf(0);
		if(obj != null){
			if( obj instanceof Double){
				result = BigDecimal.valueOf((Double)obj);
			}
			else if( obj instanceof Integer){
				result = BigDecimal.valueOf((Integer)obj);
			}
			else if( obj instanceof Long){
				result = BigDecimal.valueOf((Long)obj);
			}
		}
		return result;
	}
}
