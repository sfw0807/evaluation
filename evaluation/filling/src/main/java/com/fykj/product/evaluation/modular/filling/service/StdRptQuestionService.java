package com.fykj.product.evaluation.modular.filling.service;

import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;

import java.util.List;

/**
 * Created by Erik_Yim on 2017/4/7.
 * 独立运行填报项目问题服务接口
 */
public interface StdRptQuestionService {


    /**
     * Gets rpt question by project id.
     *
     * @param projectId the project id
     * @return the rpt question by project id
     */
    List<ReportQuestionOutVO> getRptQuestionByProjectId(String projectId);

    ReportQueAnsWraperVO getRptQueWithAnswer(String targetId, String projectId);

}
