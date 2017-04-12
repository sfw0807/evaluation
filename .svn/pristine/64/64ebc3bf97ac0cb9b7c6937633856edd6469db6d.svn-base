package com.fykj.product.evaluation.modular.filling.service.impl;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.api.filling.model.RptRemark;
import com.fykj.product.evaluation.api.filling.service.StdRptQuestionServiceApi;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.modular.filling.service.RptAnswerService;
import com.fykj.product.evaluation.modular.filling.service.RptQuestionService;
import com.fykj.product.evaluation.modular.filling.service.RptRemarkService;
import com.fykj.product.evaluation.modular.filling.service.StdRptQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;


/**
 * The type StdRptQuestion service.
 */
@Service
public class StdRptQuestionServiceImpl extends ServiceSupport implements StdRptQuestionService,StdRptQuestionServiceApi {


    @Autowired
    RptQuestionService rptQuestionService;

    @Autowired
    RptAnswerService rptAnswerService;

    @Autowired
    RptRemarkService rptRemarkService;

    /**
     * Gets rpt question by project id.
     *
     * @param projectId the project id
     * @return the rpt question by project id
     */
    @Override
    public List<ReportQuestionOutVO> getRptQuestionByProjectId(String projectId) {
        String sql = "SELECT rd.rptQuesionId FROM ReportDetail rd inner join ReportQuestion rq on rq.id =  rd.rptQuesionId " +
                " inner join PublishReport pr on pr.id = rd.rptId where pr.projectId = :projectId order by rq.questionCode asc,rq.questionType asc";
        Map<String, Object> params = new WeakHashMap<String, Object>();
        params.put("projectId", projectId);

        List<String> queIds = jpqlQuery().setJpql(sql)
                .setParams(params)
                .models();
        List<ReportQuestionOutVO> queList = new ArrayList<ReportQuestionOutVO>();
        if(queIds != null && queIds.size() > 0) {
            for(String queId : queIds) {
                queList.add(rptQuestionService.getRptQueByPkQueryType(queId, RptCommonConstants.QUERY_TYPE_DETAIL));
            }
        }
        return queList;
    }

    /**
     * Gets rpt que with answer.
     *
     * @param targetId  the target id
     * @param projectId the project id
     * @return the rpt que with answer
     */
    @Override
    public ReportQueAnsWraperVO getRptQueWithAnswer(String targetId, String projectId) {
        ReportQueAnsWraperVO vo = new ReportQueAnsWraperVO();
        List<ReportQuestionOutVO> queList = this.getRptQuestionByProjectId(projectId);
        List<ReportQueAnsOutVO> outVOList = new ArrayList<ReportQueAnsOutVO>();
        for(ReportQuestionOutVO queVO : queList) {
            ReportQueAnsOutVO queAnsVO = buildRptQueAnsOutVO(queVO);
            ReportAnswerOutVO ansOutVO = rptAnswerService.getAnswerByQuestionId(queAnsVO.getId(), targetId, projectId);
            queAnsVO.setAnsOutVO(ansOutVO);
            outVOList.add(queAnsVO);
        }

        vo.setOutVO(outVOList);
        RptRemark remark = rptRemarkService.getRemarkByProIdAndTargetId(projectId, targetId);
        vo.setRemark(remark);
        return vo;
    }

    private ReportQueAnsOutVO buildRptQueAnsOutVO(ReportQuestionOutVO vo){
        ReportQueAnsOutVO outVO = Copy.simpleCopy(vo, ReportQueAnsOutVO.class);
        outVO.setOptions(vo.getOptions());
        return outVO;
    }
}
