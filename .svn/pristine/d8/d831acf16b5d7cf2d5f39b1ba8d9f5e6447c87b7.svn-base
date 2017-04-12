package com.fykj.product.evaluation.modular.evaluating.itemscore.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.JStringUtils;
import com.fykj.product.evaluation.api.filling.model.RptAnswerOption;
import com.fykj.product.evaluation.api.filling.model.RptResource;
import com.fykj.product.evaluation.api.filling.service.RptQuestionServiceApi;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.filling.vo.RptQueAnsStisWraperVO;
import com.fykj.product.evaluation.common.constant.FillType;
import com.fykj.product.evaluation.modular.evaluating.Codes.Project.EVAL_T_C;
import com.fykj.product.evaluation.modular.evaluating.itemscore.model.ItemScore;
import com.fykj.product.evaluation.modular.evaluating.itemscore.service.ItemScoreService;
import com.fykj.product.evaluation.modular.evaluating.itemscore.service.ScoreType;
import com.fykj.product.evaluation.modular.evaluating.itemscore.vo.EvalTargetItemCriteriaInVO;
import com.fykj.product.evaluation.modular.evaluating.itemscore.vo.EvalTargetItemRecordVO;
import com.fykj.product.evaluation.modular.evaluating.itemscore.vo.FileInfo;
import com.fykj.product.evaluation.modular.evaluating.itemscore.vo.FillInfo;
import com.fykj.sample.cache.DictionaryCache;

/**
 * @author JIAZJ
 */
@Controller
@RequestMapping("/itemScore")
@ParamValidation4Controller
public class ItemScoreController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	 
	@Autowired
	private ItemScoreService itemScoreService;

	@Autowired
	private DictionaryCache dictionaryCache;
	
	@Autowired(required=false)
	private RptQuestionServiceApi rptQuestionService;

	@ResponseBody
	@RequestMapping("/updateSelfScore")
	public InvokeResult updateSelfScore(String itemId,String evalTargetId , String score) throws Exception {
		itemScoreService.updateScore(itemId,evalTargetId, 
				JStringUtils.isNullOrEmpty(score)?null:new BigDecimal(score), ScoreType.SELF);
		return InvokeResult.success(true);
	}
	
	@ResponseBody
	@RequestMapping("/updateExpertScore")
	public InvokeResult updateExpertScore(String itemId,String evalTargetId , String score) throws Exception {
		itemScoreService.updateScore(itemId,evalTargetId, 
				JStringUtils.isNullOrEmpty(score)?null:new BigDecimal(score), ScoreType.EXPERT);
		return InvokeResult.success(true);
	}
	
	@ResponseBody
	@RequestMapping("/updateEndScore")
	public InvokeResult updateEndScore(String itemId,String evalTargetId , String score) throws Exception {
		itemScoreService.updateScore(itemId,evalTargetId, 
				JStringUtils.isNullOrEmpty(score)?null:new BigDecimal(score), ScoreType.END);
		return InvokeResult.success(true);
	}
	
	@ResponseBody
	@RequestMapping("/getItemScoreById")
	public InvokeResult getItemScoreById(String id) throws Exception {
		ItemScore itemScore = itemScoreService.getItemScoreById(id);
		return InvokeResult.success(itemScore);
	}
	
	
	private List<Object> fillInfos(String itemId,String evalTargetId,RptQueAnsStisWraperVO rptQueAnsStisWraperVO){
		List<Object> objects=new ArrayList<>();
		List<FillInfo> fillInfos=new ArrayList<>();
		objects.add(fillInfos);
//		RptQueAnsStisWraperVO rptQueAnsStisWraperVO= rptQuestionService.getQueAnsStisReport(itemId);
		int i=0;
		for(ReportQuestionOutVO reportQuestionOutVO:rptQueAnsStisWraperVO.getQueList() ){
			FillInfo fillInfo=new FillInfo();
			fillInfos.add(fillInfo);
			fillInfo.setAsk(reportQuestionOutVO.getQuestionContent());
			fillInfo.setAskKey("问题"+(++i));
			boolean file=FillType.FILL_ATTACH.getKey()
					.equals(reportQuestionOutVO.getQuestionType());
			if(file){
				//file
				fillInfo.setType("1");
			}else{
				fillInfo.setType("0");
			}
			List<ReportAnswerOutWraperVO> reportAnswerOutWraperVOs=rptQueAnsStisWraperVO.getAnsList();
			for(ReportAnswerOutWraperVO answerOutWraperVO:reportAnswerOutWraperVOs){
				if(evalTargetId.equals(answerOutWraperVO.getTargetId())){
					objects.add(answerOutWraperVO.getRemark().getRemark());
					List<ReportAnswerOutVO> reportAnswerOutVOs=answerOutWraperVO.getAnswerList();
					for(ReportAnswerOutVO reportAnswerOutVO:reportAnswerOutVOs){
						if(reportQuestionOutVO.getId().equals(
								reportAnswerOutVO.getRptQuestionId())){
							if(file){
								List<FileInfo> fileInfos=new ArrayList<>();
								if(reportAnswerOutVO.getResources()!=null)
								for(RptResource resource:reportAnswerOutVO.getResources()){
									FileInfo fileInfo=new FileInfo();
									fileInfo.setFileName(resource.getOrgName());
									fileInfo.setFilePath(resource.getFilePath());
									fileInfos.add(fileInfo);
								}
								fillInfo.setAnswer(fileInfos);
							}else{
								String desc="";
								if(reportAnswerOutVO.getRptQueOptIds()!=null)
								for(RptAnswerOption rptAnswerOption: reportAnswerOutVO.getRptQueOptIds()){
									desc=desc+","+rptAnswerOption.getOptionContent();
								}
								fillInfo.setAnswer(desc);
							}
						}
					}
				}
			}
		}
		if(objects.size()<2){
			objects.add("");
		}
		return objects;
	}

	@ResponseBody
	@RequestMapping("/getAllEvalTargetByItem")
	public InvokeResult getAllEvalTargetByItem(EvalTargetItemCriteriaInVO evalTargetItemCriteriaInVO) {
		List<EvalTargetItemRecordVO>  evalTargetItemRecordVOs=
				itemScoreService.getAllEvalTargetByItem(evalTargetItemCriteriaInVO);
		RptQueAnsStisWraperVO rptQueAnsStisWraperVO= rptQuestionService.getQueAnsStisReport(evalTargetItemCriteriaInVO.getItemId());
		for(EvalTargetItemRecordVO evalTargetItemRecordVO:evalTargetItemRecordVOs){
			List<Object> objects=fillInfos(evalTargetItemCriteriaInVO.getItemId(),
					evalTargetItemRecordVO.getEvalTargetId(),rptQueAnsStisWraperVO
					);
			evalTargetItemRecordVO.setFillInfos((List<FillInfo>) objects.get(0));
			evalTargetItemRecordVO.setRemark((String) objects.get(1));
			evalTargetItemRecordVO.setCategoryName(
					dictionaryCache.getDictDataName(EVAL_T_C.TYPE_NAME, 
							evalTargetItemRecordVO.getCategory()));
		}
		return InvokeResult.success(evalTargetItemRecordVOs);
	}
}
