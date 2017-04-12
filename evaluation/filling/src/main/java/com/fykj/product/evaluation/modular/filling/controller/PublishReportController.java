package com.fykj.product.evaluation.modular.filling.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.modular.filling.service.ReportService;
import com.fykj.product.evaluation.modular.filling.service.RptQuestionService;

@Controller
@RequestMapping("/report")
@ParamValidation4Controller
public class PublishReportController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RptQuestionService rptQuestionService;
	
	@Autowired
	private ReportService reportService;

	@ResponseBody
	@RequestMapping("/saveRptQuestion")
	public InvokeResult saveRptQuestion(String jsonParams) throws Exception {
//		EvalTarget evalTarget = Copy.simpleCopy(evalTargetAddInVO, EvalTarget.class);
		ReportQuestionInVO reportQuestionInVO = JSONObject.parseObject(jsonParams, ReportQuestionInVO.class);
		String questionId = rptQuestionService.saveOrUpdateRptQuestion(reportQuestionInVO);
		return InvokeResult.success(questionId);
	}

	@ResponseBody
	@RequestMapping("/rptQuestion/{queId}")
	public InvokeResult getRptQuestionByPK(@PathVariable String queId) throws Exception {
		ReportQuestionOutVO vo = rptQuestionService.getRptQuestionByPK(queId);
		return InvokeResult.success(vo);
	}

	@ResponseBody
	@RequestMapping("/rptQuestions/{scoreItemId}")
	public InvokeResult getRptQuestionByScoreItemId(@PathVariable String scoreItemId) throws Exception {
		List<ReportQuestionOutVO> queList = rptQuestionService.getRptQuestionByScoreItemId(scoreItemId, RptCommonConstants.QUERY_TYPE_DETAIL);

		return InvokeResult.success(queList);
	}

	@ResponseBody
	@RequestMapping("/project/rptQuestions/{projectId}")
	public InvokeResult getRptQuestionByProjectId(@PathVariable String projectId) throws Exception {

//		List<ReportQuestionOutVO> queList = rptQuestionService.getRptQuestionByProjectId(projectId);
		
		return null;
	}


	/**
	 * @param projectId
	 * @param rptType
	 * @return 返回值如果是null则表示该问卷、填报不存在需要保存
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/getReportIdByProjectAndType")
	public InvokeResult getReportIdByProjectAndType(String projectId, int rptType) throws Exception {
		String id = reportService.getPubReportByProjectAndType(projectId, rptType);
		return InvokeResult.success(id);

	}
}
