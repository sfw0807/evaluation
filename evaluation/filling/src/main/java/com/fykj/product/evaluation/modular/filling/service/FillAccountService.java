package com.fykj.product.evaluation.modular.filling.service;

import com.fykj.product.evaluation.modular.evaluating.evaltarget.model.EvalTarget;
import com.fykj.product.evaluation.modular.filling.model.FillAccount;
import com.fykj.product.evaluation.modular.filling.vo.FillAccountExpVO;

import java.util.List;

/**
 * Created by liwang on 2017/4/6.
 */
public interface FillAccountService {
    public void createFillAcc(String projectId);
    public List<FillAccountExpVO> getByProject(String projectId);
    public FillAccount getByAccProId(String account, String fillProjectId);
    public void bindEvalTarget(String ids, String fillProjectId);
    public void unBindEvalTarget(String ids, String fillProjectId);
    public List<EvalTarget> getUnBoundEvalTargets(String fillProjectId);
    public List<EvalTarget> getBoundEvalTargets(String fillProjectId);
}
