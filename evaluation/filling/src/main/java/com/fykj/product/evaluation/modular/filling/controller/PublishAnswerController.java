package com.fykj.product.evaluation.modular.filling.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.api.filling.model.RptPublishAnswer;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInVO;
import com.fykj.product.evaluation.modular.filling.service.RptAnswerService;
import com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService;
import com.fykj.product.evaluation.modular.filling.vo.RptPublishAnswerInVO;

@Controller
@RequestMapping("/answer")
@ParamValidation4Controller
public class PublishAnswerController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	private RptPubAnswerService rptPubAnswerService;
	
	@Autowired
	private RptAnswerService rptAnswerService;
	
	
	@ResponseBody
	@RequestMapping("/saveRptPubAnswer")
	public InvokeResult saveRptPubAnswer(String jsonParams) throws Exception {
		RptPublishAnswerInVO rptPublishAnswerInVO = JSONObject.parseObject(jsonParams, RptPublishAnswerInVO.class);
		RptPublishAnswer rptPublishAnswer = Copy.simpleCopy(rptPublishAnswerInVO, RptPublishAnswer.class);
		rptPubAnswerService.savePubAnswer(rptPublishAnswer);
		return InvokeResult.success(rptPublishAnswer.getId());
	}
	
	@ResponseBody
	@RequestMapping("/saveRptAnswer")
	public InvokeResult saveRptAnswer(String jsonParams) throws Exception {
//		ReportAnswer reportAnswer = Copy.simpleCopy(reportAnswerInVO, ReportAnswer.class);
//		RptAnswerOption rtpAnsOpt = new RptAnswerOption();
//		rtpAnsOpt.setRptQueOptId(reportAnswerInVO.getRptQueOptId());
//		rptAnswerService.saveAnswer(reportAnswer, rtpAnsOpt);
		List<ReportAnswerInVO> reportAnswerInVOs = JSONObject.parseArray(jsonParams, ReportAnswerInVO.class);
		System.out.println("" + reportAnswerInVOs);
		
		return InvokeResult.success("123123");
	}
	

}
