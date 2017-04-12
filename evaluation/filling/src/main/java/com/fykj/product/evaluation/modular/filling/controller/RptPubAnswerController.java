package com.fykj.product.evaluation.modular.filling.controller;

import com.alibaba.fastjson.JSONObject;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionInVO;
import com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Erik_Yim on 2017/4/12.
 */
@Controller
@RequestMapping("/pubAnswer")
@ParamValidation4Controller
public class RptPubAnswerController {

    @Autowired
    RptPubAnswerService rptPubAnswerService;

    /**
     * 根据项目id获取该项目有多少份答卷
     *
     * @param projectId the project id
     * @return the invoke result
     * @throws Exception the exception
     */
    @ResponseBody
    @RequestMapping("/count/{projectId}")
    public InvokeResult countPubAnswerByProjectId(@PathVariable String projectId) throws Exception {
        int count = rptPubAnswerService.countPubAnswerByProjectId(projectId);
        Map<String, Integer> resultMap = new HashMap<String, Integer>();
        resultMap.put("count", count);
        return InvokeResult.success(resultMap);
    }
}
