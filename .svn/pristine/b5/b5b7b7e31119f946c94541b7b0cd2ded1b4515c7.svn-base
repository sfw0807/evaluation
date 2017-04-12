package com.fykj.product.evaluation.modular.filling.service.impl;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.JStringUtils;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.model.EvalTarget;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.service.EvalTargetService;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.EvalTargetCriteriaInVO;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.EvalTargetDetailOutVO;
import com.fykj.product.evaluation.modular.filling.model.FillProjectEvalTarget;
import com.fykj.product.evaluation.modular.filling.service.FillProjectEvalTargetService;
import com.fykj.product.evaluation.modular.filling.vo.FillEvalTargetAccountOutVO;
import com.fykj.product.evaluation.modular.filling.vo.FillProjectEvalTargetOutVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liwang on 2017/4/10.
 */
@Service
public class FillProjectEvalTargetServiceImpl  extends ServiceSupport implements FillProjectEvalTargetService {
    private SingleEntityManager<FillProjectEvalTarget> internalServiceImpl = SingleEntityManagerGetter.get()
            .getInstance(FillProjectEvalTarget.class);

    @Autowired
    private EvalTargetService evalTargetService;

    @Override
    public void saveBatch(String projectId, String evalTargetsStr) {
        if(StringUtils.isNotEmpty(evalTargetsStr)){
            //删除旧的对应关系
            List<FillProjectEvalTarget> oldList = internalServiceImpl.singleEntityQuery2().conditionDefault()
                    .equals("fillProjectId", projectId)
                    .ready().models();
            if(oldList != null && oldList.size() > 0){
                internalServiceImpl.deleteAllByModels(oldList, FillProjectEvalTarget.class);
            }
            //添加新的关系
            String[] targetIds = evalTargetsStr.split("\\,");
            for (String targetId : targetIds) {
                FillProjectEvalTarget entity = new FillProjectEvalTarget();
                entity.setEvalTargetId(targetId);
                entity.setFillProjectId(projectId);
                internalServiceImpl.saveOnly(entity);
            }
        }
    }

    @Override
    public List<FillProjectEvalTarget> getByProject(String projectId) {
        return internalServiceImpl.singleEntityQuery2().conditionDefault()
                .equals("fillProjectId",
                        projectId)
                .ready().models();
    }

    /**
     * 查询每个参与对象的账号数
     * @param projectId
     * @return
     */
    @Override
    public List<FillEvalTargetAccountOutVO> getAccountOutByProject(String projectId) {
        List<FillEvalTargetAccountOutVO> result = new ArrayList<>();
        List<FillProjectEvalTarget> list = getByProject(projectId);
        for(FillProjectEvalTarget fillProjectEvalTarget : list){
            EvalTarget evalTarget = evalTargetService.getEvalTargetById(fillProjectEvalTarget.getEvalTargetId());
            if(evalTarget != null){
                FillEvalTargetAccountOutVO outVO = new FillEvalTargetAccountOutVO();
                outVO.setSchName(evalTarget.getName());
                Map<String, Object> params=new HashMap<>();
                params.put("fillProjectId", projectId);
                params.put("evalTargetId", evalTarget.getId());
                String sql=" select count(fa.id) from FillAccount fa "
                        + " where fa.isAvailable=1 "
                        + " and fa.fillProjectId = :fillProjectId"
                        + " and fa.evalTargetId =:evalTargetId";
                 Long num = jpqlQuery().setJpql(sql)
                        .setParams(params).model();
                outVO.setAccNum(String.valueOf(num));
                result.add(outVO);
            }

        }
        return result;
    }

    @Override
    public List<FillProjectEvalTargetOutVO> getOutByProjectId(String projectId) {
        List<FillProjectEvalTargetOutVO> result = new ArrayList<>();
        List<FillProjectEvalTarget> list = getByProject(projectId);
        for(FillProjectEvalTarget fillProjectEvalTarget : list){
            FillProjectEvalTargetOutVO vo = new FillProjectEvalTargetOutVO();
            vo.setEvalTargetId(fillProjectEvalTarget.getEvalTargetId());
            EvalTarget evalTarget = evalTargetService.getEvalTargetById(fillProjectEvalTarget.getEvalTargetId());
            vo.setEvalTargetName(evalTarget.getName());
            result.add(vo);
        }
        return result;
    }

    public JPage<EvalTargetDetailOutVO> getBindEvalTargetOnFillProject(EvalTargetCriteriaInVO evalTargetCriteriaInVO, SimplePageRequest simplePageRequest) {
        Map<String, Object> params=new HashMap<>();
        params.put("fillProjectId", evalTargetCriteriaInVO.getProjectId());
        String sql=" select a.* from EvalTarget a, FillProjectEvalTarget b"
                + " where a.isAvailable=1 "
                + " and b.isAvailable=1"
                + " and a.id = b.evalTargetId"
                + " and b.fillProjectId=:fillProjectId";
        return jpqlQuery().setJpql(sql)
                .setParams(params)
                .modelPage(simplePageRequest, EvalTargetDetailOutVO.class);
    }
}
