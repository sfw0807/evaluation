package com.fykj.product.evaluation.modular.filling.service.impl;

import com.fykj.product.evaluation.api.filling.service.StdRptAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInWraperVO;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.modular.filling.service.RptAnswerService;
import com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService;
import com.fykj.product.evaluation.modular.filling.service.StdRptAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Erik_Yim on 2017/4/12.
 */
@Service
public class StdRptAnswerServiceImpl implements StdRptAnswerService,StdRptAnswerServiceApi {

    @Autowired
    RptAnswerService rptAnswerService;

    @Autowired
    RptPubAnswerService rptPubAnswerService;

    @Override
    public void saveOrUpdateAnswers(ReportAnswerInWraperVO wraper) {
        rptAnswerService.saveOrUpdateAnswers(wraper);
        rptPubAnswerService.updateSubmitFlagByPk(wraper.getRptPubAnswerId(), RptCommonConstants.COMMIT_FLAG);
    }
}
