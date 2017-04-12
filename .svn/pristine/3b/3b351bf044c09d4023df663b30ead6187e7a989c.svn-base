package com.fykj.product.evaluation.modular.evaluating.mitem.controller;

import java.util.ArrayList;
import java.util.List;

import com.fykj.product.evaluation.modular.evaluating.mitem.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.JPageUtil;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.platform.util.JStringUtils;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.product.evaluation.common.constant.SplitCharacter;
import com.fykj.product.evaluation.modular.evaluating.mitem.model.MeasureItem;
import com.fykj.product.evaluation.modular.evaluating.mitem.service.MeasureItemService;
import com.fykj.sample.cache.DictionaryCache;
import com.fykj.sample.tree.JTreeNode;

/**
 * @author JIAZJ
 */
@Controller
@RequestMapping("/measureItem")
@ParamValidation4Controller
public class MeasureItemController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private DictionaryCache dictionaryCache;
	
	
	@Autowired
	private MeasureItemService measureItemService;

	@ResponseBody
	@RequestMapping("/saveMeasureItem")
	public InvokeResult saveMeasureItem(MeasureItemAddInVO measureItemAddInVO) throws Exception {
		MeasureItem measureItem = Copy.simpleCopy(measureItemAddInVO, MeasureItem.class);
		measureItemService.saveMeasureItem(measureItem);
		return InvokeResult.success(measureItem.getId());
	}

	@ResponseBody
	@RequestMapping("/updateMeasureItem")
	public InvokeResult updateMeasureItem(MeasureItemEditInVO measureItemEditInVO) throws Exception {
		MeasureItem measureItem = Copy.simpleCopy(measureItemEditInVO, MeasureItem.class);
		measureItemService.updateMeasureItem(measureItem);
		return InvokeResult.success(measureItem.getId());
	}
	
	@ResponseBody
	@RequestMapping("/getMeasureItemById")
	public InvokeResult getMeasureItemById(String id) throws Exception {
		MeasureItem measureItem = measureItemService.getMeasureItemById(id);
		MeasureItemDetailOutVO measureItemDetailOutVO = null;
		if (measureItem != null) {
			measureItemDetailOutVO = Copy.simpleCopy(measureItem, MeasureItemDetailOutVO.class);
		}
		return InvokeResult.success(measureItemDetailOutVO);
	}

	@ResponseBody
	@RequestMapping("/deleteMeasureItemById")
	public InvokeResult deleteMeasureItemById(String ids) throws Exception {
		String[] arr = ids.split(SplitCharacter.SPLIT_COMMA.key);
		measureItemService.deleteMeasureItems(arr);
		return InvokeResult.success(true);
	}

	@ResponseBody
	@RequestMapping("/getMeasureItemsByPage")
	public InvokeResult getMeasureItemsByPage(MeasureItemCriteriaInVO carCriteriaInVO,
			SimplePageRequestVO simplePageRequestVO) throws Exception {
		JPage<MeasureItem> page = measureItemService.getMeasureItems(carCriteriaInVO,
				new SimplePageRequest(simplePageRequestVO.getPage(), simplePageRequestVO.getSize()));
		List<MeasureItem> content = page.getContent();
		List<MeasureItemRecordOutVO> outContent = new ArrayList<MeasureItemRecordOutVO>();
		for (MeasureItem measureItem : content) {
			MeasureItemRecordOutVO measureItemRecordOutVO= Copy.simpleCopy(measureItem, MeasureItemRecordOutVO.class);
			outContent.add(measureItemRecordOutVO);
		}
		JPageUtil.replaceConent(page, outContent);
		return InvokeResult.success(page);
	}
	
	
	@ResponseBody
	@RequestMapping("/getMeasureItemTree")
	public InvokeResult getMeasureItemTree(String projectId) throws Exception {
		if(JStringUtils.isNullOrEmpty(projectId))
			throw new BusinessException("project id missing");
		List<JTreeNode> measureItem = measureItemService.getMeasureItemTree(projectId);
		return InvokeResult.success(measureItem);
	}
	
	@ResponseBody
	@RequestMapping("/getMeasureItemFrontTree")
	public InvokeResult getMeasureItemFrontTree(String projectId) throws Exception {
		if(JStringUtils.isNullOrEmpty(projectId))
			throw new BusinessException("project id missing");
		List<JTreeNode> measureItem = measureItemService.getMeasureItemTree(projectId);
		return InvokeResult.success(measureItem);
	}
	
	@ResponseBody
	@RequestMapping("/getMeasureItemRates")
	public InvokeResult getMeasureItemRates(MeasureItemRateCriteria measureItemRateCriteria) throws Exception {
		
		List<MeasureItemRate> measureItemRates = measureItemService.getMeasureItemRates(measureItemRateCriteria);
		return InvokeResult.success(measureItemRates);
	}
	//public List<MeasureTargetScore> getMeasureTargetScores(String measureItemId, String projectId,
	//String[] evalTargetIds) {
		
	@ResponseBody
	@RequestMapping("/getMeasureTargetScores")
	public InvokeResult getMeasureTargetScores(String measureItemId, String projectId,
			String evalTargetId) throws Exception {
		String[] evalTargetIds=evalTargetId.split(",");
		List<MeasureTargetScore> measureItemRates = measureItemService.getMeasureTargetScores(measureItemId, projectId, evalTargetIds);
		return InvokeResult.success(measureItemRates);
	}
	
	
	@ResponseBody
	@RequestMapping("/getMeasureItemCompRate")
	public InvokeResult getMeasureItemCompRate(String measureItemId,String projectId,String evalTargetId ) throws Exception {
		List<MeasureItemCompRate> measureItemRates = measureItemService.getMeasureItemCompRate(measureItemId, projectId, evalTargetId);
		return InvokeResult.success(measureItemRates);
	}


	@ResponseBody
	@RequestMapping("/getMeasureItemCompRateLv2")
	public InvokeResult getMeasureItemCompRateLv2(String projectId,String evalTargetId ) throws Exception {
		List<MeasureItemRateSecLvVO> measureItemRates = measureItemService.getMeasureItemCompRateLv2( projectId, evalTargetId);
		return InvokeResult.success(measureItemRates);
	}


}
