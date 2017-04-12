package com.fykj.product.evaluation.modular.filling.service.impl;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.model.EvalTarget;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.service.EvalTargetService;
import com.fykj.product.evaluation.modular.filling.Codes;
import com.fykj.product.evaluation.modular.filling.model.FillAccount;
import com.fykj.product.evaluation.modular.filling.model.FillProject;
import com.fykj.product.evaluation.modular.filling.model.FillProjectEvalTarget;
import com.fykj.product.evaluation.modular.filling.service.FillAccountService;
import com.fykj.product.evaluation.modular.filling.service.FillProjectEvalTargetService;
import com.fykj.product.evaluation.modular.filling.service.FillProjectService;
import com.fykj.product.evaluation.modular.filling.vo.FillAccountExpVO;
import com.fykj.sample.cache.DictionaryCache;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by liwang on 2017/4/6.
 */
@Service
public class FillAccountServiceImpl extends ServiceSupport implements FillAccountService {
    private SingleEntityManager<FillAccount> internalServiceImpl = SingleEntityManagerGetter.get()
            .getInstance(FillAccount.class);

    @Autowired
    private DictionaryCache dictionaryCache;
    @Autowired
    private FillProjectEvalTargetService fillProjectEvalTargetService;
    @Autowired
    private FillProjectService fillProjectService;
    @Override
    public void createFillAcc(String projectId) {
        FillProject fillProject = fillProjectService.findById(projectId);
        //如果已存在该项目账号，全部删除
        List<FillAccount> oldList = internalServiceImpl.singleEntityQuery2().condition()
                .equals("fillProjectId", projectId)
                .ready().models();
        if(oldList != null && oldList.size() > 0){
            internalServiceImpl.deleteAllByModels(oldList, FillAccount.class);
        }
        //创建账号
        String type = String.valueOf(fillProject.getSearchType());
        List<FillProjectEvalTarget> fpetList = fillProjectEvalTargetService.getByProject(projectId);
        for (FillProjectEvalTarget fpet : fpetList) {
            Random   ran   =   new   Random(System.currentTimeMillis());
            if (Codes.FillProject.SEARCH_TYPE.UN_ACCOUNT.equals(type)) {//如果是统一账号，为每个参与对象生成一个账号
                createAccount(ran, fpet, type);
            }
            if (Codes.FillProject.SEARCH_TYPE.TEMP_ACCOUNT.equals(type)) {//如果是临时账号，为每个参数与对象生成若干个账号
                int num = fillProject.getTemAccNum();
                for (int i = 0; i < num; i++) {
                    createAccount(ran, fpet, type);
                }
            }
        }
    }

    public void createAccount(Random ran, FillProjectEvalTarget fillProjectEvalTarget, String type) {
        FillAccount fillAccount = new FillAccount();
        fillAccount.setFillProjectId(fillProjectEvalTarget.getFillProjectId());
        fillAccount.setAccount(getRandomAcc(ran));
        fillAccount.setPwd(getRandomPwd());
        fillAccount.setType(Integer.valueOf(type));
        fillAccount.setEvalTargetId(fillProjectEvalTarget.getEvalTargetId());
        internalServiceImpl.saveOnly(fillAccount);
    }

    @Override
    public List<FillAccountExpVO> getByProject(String fillProjectId) {
        List<FillAccountExpVO> result = new ArrayList<>();
        //查询项目参与对象
        List<EvalTarget> evalTargets = getTargetsByFillProjectId(fillProjectId);
        for (EvalTarget evalTarget : evalTargets) {
            List<FillAccount> fillAccounts = getFillAccounts(fillProjectId, evalTarget.getId());
            for(FillAccount fillAccount : fillAccounts){
                FillAccountExpVO vo = new FillAccountExpVO();
                String type = dictionaryCache.getDictDataName(com.fykj.product.evaluation.modular.evaluating.Codes.Project.EVAL_T_C.TYPE_NAME, evalTarget.getCategory());
                vo.setTargetType(type);
                vo.setSchName(evalTarget.getName());
                //TODO detail url
                vo.setNetAddr("//TODO detail url");
                vo.setAccount(fillAccount.getAccount());
                vo.setPwd(fillAccount.getPwd());
                result.add(vo);
            }

        }
        return result;
    }
    //查询项目参与对象
    public List<EvalTarget> getTargetsByFillProjectId(String fillProjectId){
        String sql = "SELECT et FROM EvalTarget et, FillProjectEvalTarget fe"
                + " where et.isAvailable = 1"
                + " and fe.isAvailable = 1"
                + " et.id = fe.evalTargetId and fe.fillProjectId = :fillProjectId";
        Map<String, Object> params = new WeakHashMap<String, Object>();
        params.put("fillProjectId", fillProjectId);

        List<EvalTarget> list = jpqlQuery().setJpql(sql)
                .setParams(params)
                .models();
        return list;
    }
    //查询项目参与对象的账号
    public List<FillAccount> getFillAccounts(String fillProjectId, String evalTargetId){
        String sql = "SELECT fa FROM FillAccount fa" +
                " where fa.isAvailable=1" +
                " and fa.evalTargetId=:evalTargetId and fa.fillProjectId=:fillProjectId";
        Map<String, Object> params = new WeakHashMap<String, Object>();
        params.put("fillProjectId", fillProjectId);
        params.put("evalTargetId", evalTargetId);

        List<FillAccount> list = jpqlQuery().setJpql(sql)
                .setParams(params)
                .models();
        return list;
    }

    /**
     * 生成时间戳账号
     * @return
     */
    public String getRandomAcc(Random ran) {
        long acc = Math.abs(ran.nextLong());
        return String.valueOf(acc);
    }

    /**
     * UUID 前8位
     * @return
     */
    public String getRandomPwd() {
        return UUID.randomUUID().toString().substring(0, 7);
    }

    /**
     * 选中账号插入fillAccount表
     * @param evalTargetIds
     * @param fillProjectId
     */
    @Override
    public void bindEvalTarget(String evalTargetIds, String fillProjectId) {
        if(StringUtils.isNotEmpty(evalTargetIds)){
            String[] ids = evalTargetIds.split("\\,");
            for(String id : ids){
                FillAccount fillAccount = new FillAccount();
                fillAccount.setType(Integer.valueOf(Codes.FillProject.SEARCH_TYPE.TRUE_ACCOUNT));
                fillAccount.setAccount(id);
                internalServiceImpl.saveOnly(fillAccount);
            }
        }
    }

    /**
     * 解除绑定
     * 从FillAccount中删除数据
     * @param fillAccountIds
     */
    @Override
    public void unBindEvalTarget(String fillAccountIds) {
        if(StringUtils.isNotEmpty(fillAccountIds)){
            String[] ids = fillAccountIds.split("\\,");
            for(String id : ids){
                FillAccount fillAccount = internalServiceImpl.getById(id);
                internalServiceImpl.delete(fillAccount);
            }
        }
    }
}
