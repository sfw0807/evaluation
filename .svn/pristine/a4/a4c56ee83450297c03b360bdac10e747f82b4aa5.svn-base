package com.fykj.product.evaluation.modular.evaluating.questionnaire.service.impl;

import com.fykj.product.evaluation.api.filling.model.RptPublishAnswer;
import com.fykj.product.evaluation.api.filling.service.RptPubAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.service.StdRptAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInWraperVO;
import com.fykj.product.evaluation.modular.evaluating.questionnaire.service.FillQuestionnaireService;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhangtian on 2017/4/12.
 */
@Service
public class FillQuestionnaireServiceImpl implements FillQuestionnaireService {

    @Autowired
    private RptPubAnswerServiceApi rptPubAnswerServiceApi ;

    @Autowired
    private StdRptAnswerServiceApi stdRptAnswerServiceApi ;

    @Override
    public void saveQuestionnaireOptions(ReportAnswerInWraperVO wraper, String rptId, String targetId) {
        // 获取pubAnswerId答卷ID
        RptPublishAnswer rptPublishAnswer = new RptPublishAnswer() ;
        rptPublishAnswer.setRptId(rptId);
        rptPublishAnswer.setTargetId(targetId);
        final String rptPubAnswerId = rptPubAnswerServiceApi.savePubAnswer(rptPublishAnswer) ;
        wraper.setRptPubAnswerId(rptPubAnswerId);

        List<ReportAnswerInVO> answers = wraper.getAnswers() ;
        Closure answersClouse = new Closure() {
            @Override
            public void execute(Object obj) {
                if(obj.getClass().isAssignableFrom(ReportAnswerInVO.class)) {
                    ReportAnswerInVO answerInVO = (ReportAnswerInVO)obj ;
                    answerInVO.setRptPubAnswerId(rptPubAnswerId);
                }
            }
        };
        CollectionUtils.forAllDo(answers, answersClouse);
        // 保存答卷
        stdRptAnswerServiceApi.saveOrUpdateAnswers(wraper);
    }
}
