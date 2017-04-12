package com.fykj.product.evaluation.modular.evaluating.mitem.service;

import java.util.List;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.product.evaluation.modular.evaluating.mitem.model.MeasureItem;
import com.fykj.product.evaluation.modular.evaluating.mitem.vo.*;
import com.fykj.sample.tree.JTreeNode;

public interface MeasureItemService {

	void saveMeasureItem(MeasureItem measureItem);
	
	void updateMeasureItem(MeasureItem measureItem);
	
	void deleteMeasureItem(MeasureItem measureItem);
	
	void deleteMeasureItemById(String id);
	
	void deleteMeasureItems(String [] ids);
	
	MeasureItem getMeasureItemById(String id);
	
	JPage<MeasureItem> getMeasureItems(MeasureItemCriteriaInVO measureItemCriteriaInVO , SimplePageRequest simplePageRequest);
	 
//	boolean exists(String name);
	
	public List<JTreeNode> getMeasureItemTree(String projectId);
	
	/**
	 * check whether the m-item contains any child or not.
	 * @param mitemId
	 * @return  true contains child, otherwise false
	 */
	boolean ifContainsItem(String mitemId);
	
	List<MeasureItem> children(String measureItemId, boolean recursive);
	
	List<MeasureItem> children(String measureItemId,String projectId, boolean recursive);
	
	List<MeasureItemRate> getMeasureItemRates(MeasureItemRateCriteria measureItemRateCriteria);
	
	List<MeasureTargetScore> getMeasureTargetScores(String measureItemId,String projectId,String[] evalTargetIds);

	List<MeasureItemCompRate> getMeasureItemCompRate(String measureItemId,String projectId,String evalTargetId );
	List<MeasureItemRateSecLvVO> getMeasureItemCompRateLv2(String projectId, String evalTargetId );

}
