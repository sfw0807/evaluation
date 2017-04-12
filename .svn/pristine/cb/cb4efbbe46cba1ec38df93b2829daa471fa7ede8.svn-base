package com.fykj.product.evaluation.api.filling.service;

import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;

import java.util.List;

/**
 * Created by Erik_Yim on 2017/4/7.
 */
public interface StdRptQuestionServiceApi {


    /**
     * 根据projectid获取题目
     *
     * @param projectId the project id
     * @return rpt question by project id
     */
    List<ReportQuestionOutVO> getRptQuestionByProjectId(String projectId);

    /**
     * 获取题目及答案
     *
     * @param targetId  the target id
     * @param projectId the project id
     * @return the rpt que with answer
     */
    ReportQueAnsWraperVO getRptQueWithAnswer(String targetId, String projectId);

}
