package com.fykj.product.evaluation.modular.filling.service;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.EvalTargetCriteriaInVO;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.EvalTargetDetailOutVO;
import com.fykj.product.evaluation.modular.filling.model.FillProjectEvalTarget;
import com.fykj.product.evaluation.modular.filling.vo.FillEvalTargetAccountOutVO;
import com.fykj.product.evaluation.modular.filling.vo.FillProjectEvalTargetOutVO;

import java.util.List;

/**
 * Created by liwang on 2017/4/10.
 */
public interface FillProjectEvalTargetService {
    public void saveBatch(String projectId, String evalTargetsStr);

    public List<FillProjectEvalTarget> getByProject(String projectId);

    public List<FillEvalTargetAccountOutVO> getAccountOutByProject(String projectId);

    public List<FillProjectEvalTargetOutVO> getOutByProjectId(String projectId);

    public JPage<EvalTargetDetailOutVO> getBindEvalTargetOnFillProject(EvalTargetCriteriaInVO evalTargetCriteriaInVO, SimplePageRequest simplePageRequest);
}
