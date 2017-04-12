package com.fykj.product.evaluation.modular.evaluating.scoreitem.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.product.evaluation.api.filling.model.RptPublishAnswer;
import com.fykj.product.evaluation.api.filling.service.RptAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.service.RptPubAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceCriteriaInVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceWraperOutVO;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.service.FillScoreItemServiceApi;
import com.fykj.product.evaluation.api.scoreitem.service.IntervalScoreServiceApi;
import com.fykj.product.evaluation.common.constant.FillType;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.model.EvalTarget;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.service.EvalTargetService;

@Controller
@RequestMapping("/fillScoreItem/")
@ParamValidation4Controller
public class FillScoreItemController {
	
	@Autowired
	private FillScoreItemServiceApi fillScoreItemServiceApi;
	
	@Autowired
	private IntervalScoreServiceApi intervalScoreServiceApi ;
	
	@Autowired
	private EvalTargetService evalTargetService ;
	
	@Autowired
	private RptAnswerServiceApi rptAnswerServiceApi ;
	
	@Autowired
	private RptPubAnswerServiceApi rptPubAnswerServiceApi ;
	
	/**
	 *  getFillScoreItemOptions:(获取得分点关联的选项列表). 
	 *  @return_type:void
	 *  @author zhangtian  
	 *  @throws Exception
	 */
	@RequestMapping(value = "getFillScoreItemOptions", method = RequestMethod.POST)
	@ResponseBody
	public List<ReportQuestionOutVO> getFillScoreItemOptions(@RequestParam String scoreItemId) throws Exception {
		List<ReportQuestionOutVO> result = fillScoreItemServiceApi.getFillScoreItemOptions(scoreItemId, RptCommonConstants.QUERY_TYPE_DETAIL) ;
		return result ;
	}
	
	/**
	 *  getFillScoreItemOptionsAll:(获取得分点关联的选项列表--带答案). 
	 *  @return_type:List<ReportQueAnsWraperVO>
	 *  @author zhangtian  
	 *  @param scoreItemId
	 *  @return
	 */
	@RequestMapping(value = "getFillScoreItemOptionsAll", method = RequestMethod.POST)
	@ResponseBody
	public ReportQueAnsWraperVO getFillScoreItemOptionsAll(String params) throws Exception {
		JSONObject jsonObject = JSONObject.parseObject(params) ;
		String userId = ServerSessionHolder.getSessionUser().getId() ;
		EvalTarget evalTarget = evalTargetService.getEvalTargetByUserId(userId) ;
		ReportQueAnsWraperVO result = fillScoreItemServiceApi.getFillScoreItemOptionsAll(jsonObject.getString("scoreItemId"), evalTarget.getId(), jsonObject.getString("projectId")) ;
		return result ; 
	}
	
	@RequestMapping(value = "saveScoreItemOptions", method = RequestMethod.POST)
	@ResponseBody
	public InvokeResult saveScoreItemOptions(String answerData) throws Exception {
		JSONObject jsonObject = JSONObject.parseObject(answerData) ;
		String formType = jsonObject.getString("formType") ;
		String rptId = "" ;
		String targetId = jsonObject.getString("targetId") ;
		// 保存备注基本信息
		String scoreItemId = jsonObject.getString("scoreItemId") ;
		String remarkOption = jsonObject.getString("remarkOption") ;
		String remarkId = jsonObject.getString("remarkId") ;
		ReportAnswerInWraperVO reportAnswerInWraperVO = new ReportAnswerInWraperVO() ;
		reportAnswerInWraperVO.setScoreItemId(scoreItemId);
		reportAnswerInWraperVO.setRemark(remarkOption);
		if(StringUtils.isNotBlank(remarkId) && !StringUtils.equals("null", remarkId)) {
			reportAnswerInWraperVO.setRemarkId(remarkId);
		}
		// 答题列表
		List<ReportAnswerInVO> reportAnswerInVOs = new ArrayList<ReportAnswerInVO>() ;
		
		// 单选题处理
		JSONArray singleOptions = jsonObject.getJSONArray("singleOptions") ;
		if(singleOptions != null && !singleOptions.isEmpty()) {
			for(int i = 0; i < singleOptions.size(); i++) {
				ReportAnswerInVO reportAnswerInVO = new ReportAnswerInVO() ;
				if(StringUtils.equals("edit", formType))
					reportAnswerInVO.setId(singleOptions.getJSONObject(i).getJSONObject("ansOutVO").getString("id"));
				reportAnswerInVO.setRptQuestionId(singleOptions.getJSONObject(i).getString("singleId"));
				if(StringUtils.isNotBlank(singleOptions.getJSONObject(i).getString("rptId")))
					rptId = singleOptions.getJSONObject(i).getString("rptId") ;
				reportAnswerInVO.setScore(singleOptions.getJSONObject(i).getFloatValue("singleScore"));
				List<String> opt = new ArrayList<String>() ;
				opt.add(singleOptions.getJSONObject(i).getString("singleOption")) ;
				reportAnswerInVO.setRptQueOptIds(opt);
				reportAnswerInVO.setQuestionType(FillType.FILL_SINGLE.getKey());
				reportAnswerInVO.setScoreItemId(scoreItemId);
				reportAnswerInVOs.add(reportAnswerInVO);
			}
		}
		
		// 多选题处理
		JSONArray multiOptions = jsonObject.getJSONArray("multiOptions") ;
		if(multiOptions != null && !multiOptions.isEmpty()) {
			for(int i = 0; i < multiOptions.size(); i++) {
				JSONArray js = multiOptions.getJSONArray(i) ;
				if(js != null && !js.isEmpty()) {
					String answerOutId = "" ;
					ReportAnswerInVO reportAnswerInVO = new ReportAnswerInVO() ;
					reportAnswerInVO.setRptQuestionId(js.getJSONObject(0).getString("multiId"));
					reportAnswerInVO.setScoreItemId(scoreItemId);
					
					List<String> opt = new ArrayList<String>() ;
					float scoresV = 0 ;
					for(int j = 0; j < js.size() ;j++) {
						if(StringUtils.isNotBlank(js.getJSONObject(j).getString("rptId")))
							rptId = js.getJSONObject(j).getString("rptId") ;
						if(StringUtils.equals("edit", formType)) {
							if(StringUtils.isNotBlank(js.getJSONObject(j).getJSONObject("ansOutVO").getString("id"))) 
								answerOutId = js.getJSONObject(j).getJSONObject("ansOutVO").getString("id") ;
						}
						scoresV +=js.getJSONObject(j).getFloatValue("multiScore") ;
						opt.add(js.getJSONObject(j).getString("multiOption")) ;
					}
					reportAnswerInVO.setScore(scoresV);// 累加总分
					reportAnswerInVO.setRptQueOptIds(opt);
					reportAnswerInVO.setQuestionType(FillType.FILL_MULTI.getKey());
					reportAnswerInVO.setId(answerOutId);
					
					reportAnswerInVOs.add(reportAnswerInVO);
				}
			}
		}
		
		// 问答题处理
		JSONArray answerOptions = jsonObject.getJSONArray("answerOptions") ;
		if(answerOptions != null && !answerOptions.isEmpty()) {
			for(int i = 0; i < answerOptions.size(); i++) {
				ReportAnswerInVO reportAnswerInVO = new ReportAnswerInVO() ;
				if(StringUtils.equals("edit", formType))
					reportAnswerInVO.setId(answerOptions.getJSONObject(i).getJSONObject("ansOutVO").getString("id"));
				reportAnswerInVO.setRptQuestionId(answerOptions.getJSONObject(i).getString("answerId"));
				if(StringUtils.isNotBlank(answerOptions.getJSONObject(i).getString("rptId")))
					rptId = answerOptions.getJSONObject(i).getString("rptId") ;
				// 根据题目ID与填报数值匹配区间分数
				if(answerOptions.getJSONObject(i).getBooleanValue("hasIntervalScore")) {
					reportAnswerInVO.setScore(getIntervalScore(answerOptions.getJSONObject(i).getString("answerId") ,answerOptions.getJSONObject(i).getFloatValue("answerOption")));
				}
				reportAnswerInVO.setAnswer(answerOptions.getJSONObject(i).getString("answerOption"));
				reportAnswerInVO.setQuestionType(FillType.FILL_ANSWER.getKey());
				reportAnswerInVO.setScoreItemId(scoreItemId);
				reportAnswerInVOs.add(reportAnswerInVO);
			}
		}
		// 附件处理
		
		
		// 保存填报答案
		reportAnswerInWraperVO.setAnswers(reportAnswerInVOs);
		fillScoreItemServiceApi.saveScoreItemOptions(reportAnswerInWraperVO, rptId, targetId) ;
		return InvokeResult.success(true) ;
	}
	
	/**
	 *  getIntervalScore:(根据表单ID获取区间分数). 
	 *  @return_type:String
	 *  @author zhangtian  
	 *  @param intervalScores
	 *  @return
	 */
	private float getIntervalScore(String formId, final float numberScore) {
		List<IntervalScore> intervalScores = intervalScoreServiceApi.getIntervalScoreByScoreFormId(formId) ;
		float finalScore = 0 ;
		for(IntervalScore intervalScore : intervalScores) {
			if(numberScore >= intervalScore.getLowLimit().floatValue()
					&& numberScore <= intervalScore.getUpperLimit().floatValue()) {
				finalScore = intervalScore.getScore().floatValue() ;
			}
		}
		return finalScore ;
	}
	
	/**
	 *  getAnswerByScoreId:(根据ScoreItemId查询填报详情). 
	 *  @return_type:ReportAnswerOutWraperVO
	 *  @author zhangtian  
	 *  @param scoreItemId
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "getAnswerByScoreId", method = RequestMethod.POST)
	@ResponseBody
	public ReportAnswerOutWraperVO getAnswerByScoreId(@RequestParam String params) throws Exception {
		JSONObject jsonObject = JSONObject.parseObject(params) ;
		String userId = ServerSessionHolder.getSessionUser().getId() ;
		EvalTarget evalTarget = evalTargetService.getEvalTargetByUserId(userId) ;
		ReportAnswerOutWraperVO answerOutWraperVO = fillScoreItemServiceApi.getAnswerByScoreId(evalTarget.getId(), jsonObject.getString("scoreItemId"), jsonObject.getString("projectId")) ;
		return answerOutWraperVO ;
	}
	
	/**
	 *  getAlreadyAttach:(获取项目下已经上传的附件列表). 
	 *  @return_type:RptResource
	 *  @author zhangtian  
	 *  @param projectId
	 *  @return
	 */
	@RequestMapping(value = "getAttaches", method = RequestMethod.POST)
	@ResponseBody
	public RptResourceWraperOutVO getAlreadyAttaches(RptResourceCriteriaInVO criteriaInVO) throws Exception {
		RptResourceWraperOutVO resourceWraperOutVO = rptAnswerServiceApi.getRptResourceList(criteriaInVO) ;
		return resourceWraperOutVO ;
	}
	
	/**
	 *  getRptPubAnsId:(获取rptPubAnsId). 
	 *  @return_type:String
	 *  @author zhangtian  
	 *  @param jsonStr
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "getRptPubAnsId", method = RequestMethod.POST)
	@ResponseBody
	public String getRptPubAnsId(String jsonStr) throws Exception {
		JSONObject jsonObject = JSONObject.parseObject(jsonStr) ;
		String targetId = jsonObject.getString("targetId") ;
		String rptId = jsonObject.getString("rptId") ;
		String projectId = jsonObject.getString("projectId") ;
		
		RptPublishAnswer rptPublishAnswer = new RptPublishAnswer() ;
		rptPublishAnswer.setRptId(rptId);
		rptPublishAnswer.setTargetId(targetId);
		String rptPubAnswerId = rptPubAnswerServiceApi.getRptPubAnsIdBytargetId(targetId, projectId) ;
		if(StringUtils.isBlank(rptPubAnswerId)) {
			rptPubAnswerId  = rptPubAnswerServiceApi.savePubAnswer(rptPublishAnswer) ;
		}
		return rptPubAnswerId ;
	}
}
