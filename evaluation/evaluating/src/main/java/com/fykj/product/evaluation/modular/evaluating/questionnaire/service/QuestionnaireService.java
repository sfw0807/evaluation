package com.fykj.product.evaluation.modular.evaluating.questionnaire.service;

import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;

import java.util.List;

/**
 * Created by zhangtian on 2017/4/10.
 */
public interface QuestionnaireService {
    /**
     * 根据projectId查询问卷列表
     * @param projectId
     * @return
     */
    public List<ReportQuestionOutVO> getRptQuestionByProjectId(String projectId) ;

    /**
     * 根据quesId查询题目信息
     * @param quesId
     * @return
     */
    public ReportQuestionOutVO getQuestionById(String quesId);
}
