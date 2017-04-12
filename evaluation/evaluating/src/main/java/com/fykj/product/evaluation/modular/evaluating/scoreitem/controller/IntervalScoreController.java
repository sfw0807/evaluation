package com.fykj.product.evaluation.modular.evaluating.scoreitem.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.service.IntervalScoreServiceApi;
import com.fykj.product.evaluation.api.scoreitem.vo.IntervalScoreInVO;

@Controller
@RequestMapping("/intervalScore/")
@ParamValidation4Controller
public class IntervalScoreController {
	@Autowired(required = false)
	private IntervalScoreServiceApi intervalScoreService ;
	
	/**
	 *  saveIntervalScore:(批量保存区间评分信息). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param intervalScoreInVOs
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "saveIntervalScore", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult saveIntervalScore(List<IntervalScoreInVO> intervalScoreInVOs) throws Exception {
		List<IntervalScore> intervalScores = new ArrayList<IntervalScore>() ;
		for(IntervalScoreInVO vo : intervalScoreInVOs) {
			IntervalScore intervalScore = Copy.simpleCopy(vo, IntervalScore.class) ;
			intervalScores.add(intervalScore) ;
		}
		intervalScoreService.saveIntervalScore(intervalScores);
		return InvokeResult.success(intervalScores.size()) ;
	}
	
	/**
	 *  updateIntervalScore:(批量更新区间评分信息). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param intervalScoreInVOs
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "updateIntervalScore", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult updateIntervalScore(List<IntervalScoreInVO> intervalScoreInVOs, String scoreRuleType) throws Exception {
		List<IntervalScore> intervalScores = new ArrayList<IntervalScore>() ;
		for(IntervalScoreInVO vo : intervalScoreInVOs) {
			IntervalScore intervalScore = Copy.simpleCopy(vo, IntervalScore.class) ;
			intervalScores.add(intervalScore) ;
		}
		intervalScoreService.updateIntervalScore(intervalScores, scoreRuleType);
		return InvokeResult.success(intervalScores.size()) ;
	}
	
	/**
	 *  getIntervalScores:(获取区间评分列表). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param intervalScoreInVOs
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "getIntervalScores", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult getIntervalScores(String formId) throws Exception {
		List<IntervalScore> intervalScores = intervalScoreService.getIntervalScoreByScoreFormId(formId) ;
		return InvokeResult.success(intervalScores) ;
	}
}
