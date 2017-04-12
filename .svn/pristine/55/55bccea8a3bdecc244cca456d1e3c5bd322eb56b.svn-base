package com.fykj.product.evaluation.modular.evaluating.questionnaire.service.impl;

import com.fykj.product.evaluation.api.filling.service.RptQuestionServiceApi;
import com.fykj.product.evaluation.api.filling.service.StdRptQuestionServiceApi;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.service.IntervalScoreServiceApi;
import com.fykj.product.evaluation.modular.evaluating.questionnaire.service.QuestionnaireService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangtian on 2017/4/10.
 */
@Service
public class QuestionnaireServiceImpl implements QuestionnaireService{
    @Autowired
    private StdRptQuestionServiceApi stdRptQuestionServiceApi;

    @Autowired
    private RptQuestionServiceApi rptQuestionServiceApi ;

    @Autowired
    private IntervalScoreServiceApi intervalScoreService;

    @Override
    public List<ReportQuestionOutVO> getRptQuestionByProjectId(String projectId) {
        List<ReportQuestionOutVO> reportQuestionOutVOS = stdRptQuestionServiceApi.getRptQuestionByProjectId(projectId) ;
        if (CollectionUtils.isNotEmpty(reportQuestionOutVOS)) {
            for(ReportQuestionOutVO reportQuestionOutVO : reportQuestionOutVOS) {
                if (reportQuestionOutVO != null) {
                    List<IntervalScore> intervalScores = intervalScoreService.getIntervalScoreByScoreFormId(reportQuestionOutVO.getId()) ;
                    if(CollectionUtils.isNotEmpty(intervalScores)) {
                        reportQuestionOutVO.setHasIntervalScores(true);
                        reportQuestionOutVO.setIntervalScores(intervalScores);
                    }else{
                        reportQuestionOutVO.setHasIntervalScores(false);
                    }
                }
            }
        }
        return reportQuestionOutVOS ;
    }

    @Override
    public ReportQuestionOutVO getQuestionById(String quesId) {
        ReportQuestionOutVO reportQuestionOutVO = rptQuestionServiceApi.getRptQuestionByPK(quesId);
        if (reportQuestionOutVO != null) {
            List<IntervalScore> intervalScores = intervalScoreService.getIntervalScoreByScoreFormId(reportQuestionOutVO.getId()) ;
            if(CollectionUtils.isNotEmpty(intervalScores)) {
                reportQuestionOutVO.setHasIntervalScores(true);
                reportQuestionOutVO.setIntervalScores(intervalScores);
            }else{
                reportQuestionOutVO.setHasIntervalScores(false);
            }
        }
        return reportQuestionOutVO ;
    }
}
