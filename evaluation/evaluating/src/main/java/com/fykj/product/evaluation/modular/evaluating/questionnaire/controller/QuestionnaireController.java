package com.fykj.product.evaluation.modular.evaluating.questionnaire.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.product.evaluation.api.filling.model.RptQuestionOption;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.service.ScoreItemServiceApi;
import com.fykj.product.evaluation.common.constant.FillType;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.common.constant.SplitCharacter;
import com.fykj.product.evaluation.modular.evaluating.questionnaire.service.QuestionnaireService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  问卷管理（列表/新增/编辑/删除）
 * Created by zhangtian on 2017/4/7.
 */
@RestController
@ParamValidation4Controller
@RequestMapping(value="questionnaire")
public class QuestionnaireController {

    @Autowired
    private QuestionnaireService questionnaireService ;

    @Autowired
    private ScoreItemServiceApi scoreItemService ;

    /**
     * 列表显示所有的问卷习题
     * @param projectId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getQuestionnaireList", method = RequestMethod.POST)
    public InvokeResult getQuestionnaireList(@RequestParam String projectId) throws  Exception{
        List<ReportQuestionOutVO> quesLists = questionnaireService.getRptQuestionByProjectId(projectId);
        return InvokeResult.success(quesLists) ;
    }

    @RequestMapping(value = "saveOrUpdateQuestionnaires", method = RequestMethod.POST)
    public InvokeResult saveOrUpdateQuestionnaires(String paramsJson) throws  Exception{
        JSONObject jsonObject = JSONObject.parseObject(paramsJson);
        String queId = jsonObject.getString("quesId") ;
        String fillType = jsonObject.getString("fillType") ;
        String projectId = jsonObject.getString("projectId") ;
        ReportQuestionInVO reportQuestionInVO = new ReportQuestionInVO() ;
        reportQuestionInVO.setQueId(queId);
        reportQuestionInVO.setQuestionType(fillType);
        reportQuestionInVO.setQuestionCode(jsonObject.getIntValue("scoreItemFillSerialNumber"));
        reportQuestionInVO.setQuestionContent(jsonObject.getString("scoreItemFillDesc"));
        List<IntervalScore> intervals = new ArrayList<IntervalScore>() ;
        // 单选或多选
        if(StringUtils.equals(fillType, FillType.FILL_SINGLE.getKey())
                || StringUtils.equals(fillType, FillType.FILL_MULTI.getKey())) {
            List<RptQuestionOption> options = new ArrayList<RptQuestionOption>() ;
            JSONArray nameArr = null ;
            JSONArray contentArr = null ;
            JSONArray scoreArr = null ;

            try {
                nameArr = jsonObject.getJSONArray("optionsName") ;
                contentArr = jsonObject.getJSONArray("optionsContent") ;
                scoreArr = jsonObject.getJSONArray("optionsScore") ;
            }catch (JSONException e) {
                String nameStr = jsonObject.getString("optionsName");
                String contentStr = jsonObject.getString("optionsContent") ;
                String scoreStr = jsonObject.getString("optionsScore");

                nameArr = new JSONArray() ;
                nameArr.add(nameStr) ;
                contentArr = new JSONArray() ;
                contentArr.add(contentStr) ;
                scoreArr = new JSONArray() ;
                scoreArr.add(scoreStr) ;
            }

            for(int i = 0; i < nameArr.size(); i++){
                RptQuestionOption opt = new RptQuestionOption() ;
                opt.setOptName(nameArr.getString(i));
                opt.setOptContent(contentArr.getString(i)) ;
                opt.setOptScore(scoreArr.getFloatValue(i));
                options.add(opt) ;
            }
            reportQuestionInVO.setOptions(options);
        } else if(StringUtils.equals(fillType, FillType.FILL_ANSWER.getKey())) {// 问答题
            // 问答记分
            if(StringUtils.equals(jsonObject.getString("quesGetScore"), FillType.FillAnswerGetScore.GET_SCORE.getKey())) {
                // 保存区间记分
                JSONArray upperArray = null ;
                JSONArray lowerArray = null ;
                JSONArray scores = null ;
                try {
                    upperArray = jsonObject.getJSONArray("upperSection") ;
                    lowerArray = jsonObject.getJSONArray("lowerSection") ;
                    scores = jsonObject.getJSONArray("sectionScore") ;
                }catch (JSONException | ClassCastException e){
                    BigDecimal upperStr = jsonObject.getBigDecimal("upperSection") ;
                    BigDecimal lowerStr = jsonObject.getBigDecimal("lowerSection") ;
                    BigDecimal scoresStr = jsonObject.getBigDecimal("sectionScore") ;
                    upperArray = new JSONArray() ;
                    upperArray.add(upperStr) ;
                    lowerArray = new JSONArray() ;
                    lowerArray.add(lowerStr) ;
                    scores = new JSONArray() ;
                    scores.add(scoresStr) ;
                }
                for(int i = 0; i < upperArray.size(); i++) {
                    IntervalScore intervalScore = new IntervalScore() ;
                    intervalScore.setUpperLimit(upperArray.getBigDecimal(i));
                    intervalScore.setLowLimit(lowerArray.getBigDecimal(i));
                    intervalScore.setScore(scores.getBigDecimal(i));

                    intervals.add(intervalScore) ;
                }
            } else {//问答不计分
                // 问答不计分什么都不用做了
            }
        }else {// 附件
            // 附件类型什么都不用做了
        }

        String rptId = scoreItemService.getPubReportByProjectAndType(projectId, RptCommonConstants.RPT_TYPE_RPT) ;
        String quesId = scoreItemService.saveOrUpdateScoreItemFills(reportQuestionInVO, intervals, rptId, projectId) ;
        return InvokeResult.success(quesId) ;
    }

    /**
     * 根据ID删除问卷中的问题
     * @param quesIds
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "deleteQuestionById", method = RequestMethod.POST)
    public InvokeResult deleteQuestionById(@RequestParam String quesIds) throws  Exception{
        String[] arr = quesIds.split(SplitCharacter.SPLIT_DOUBLE.key) ;
        scoreItemService.deleteQuestionByIds(arr);
        return  InvokeResult.success(true) ;
    }

    /**
     * 根据问题ID查询问题，数据回显使用
     * @param quesId
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "getQuestionById", method = RequestMethod.POST)
    public InvokeResult getQuestionById(@RequestParam String quesId) throws  Exception {
        ReportQuestionOutVO reportQuestionOutVO = questionnaireService.getQuestionById(quesId) ;
        return InvokeResult.success(reportQuestionOutVO) ;
    }
}
