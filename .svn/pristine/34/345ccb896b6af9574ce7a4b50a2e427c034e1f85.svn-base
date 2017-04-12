package com.fykj.product.evaluation.modular.filling.service.impl;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.Copy;
import com.fykj.platform.util.JDateUtils;
import com.fykj.platform.util.JStringUtils;
import com.fykj.product.evaluation.modular.filling.Codes;
import com.fykj.product.evaluation.modular.filling.model.FillAccount;
import com.fykj.product.evaluation.modular.filling.model.FillProject;
import com.fykj.product.evaluation.modular.filling.service.FillProjectEvalTargetService;
import com.fykj.product.evaluation.modular.filling.service.FillProjectService;
import com.fykj.product.evaluation.modular.filling.vo.FillAccountProjectsVO;
import com.fykj.product.evaluation.modular.filling.vo.FillProjectEditInVO;
import com.fykj.product.evaluation.modular.filling.vo.FillProjectAddInVO;
import com.fykj.product.evaluation.modular.filling.vo.FillProjectCriteriaInVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * Created by liwang on 2017/4/6.
 */
@Service
public class FillProjectServiceImpl extends ServiceSupport implements FillProjectService {
    private SingleEntityManager<FillProject> internalProjectServiceImpl = SingleEntityManagerGetter.get()
            .getInstance(FillProject.class);

    @Autowired
    private FillProjectEvalTargetService fillProjectEvalTargetService;
    @Override
    public JPage<FillProject> getProjects(FillProjectCriteriaInVO projectCriteriaInVO, SimplePageRequest simplePageRequest) {
        return internalProjectServiceImpl.singleEntityQuery2().conditionDefault()
                .largerAndEquals("startDate",
                        JStringUtils.isNullOrEmpty(projectCriteriaInVO.getStartDateStr())?null:
                                JDateUtils.parseDate(projectCriteriaInVO.getStartDateStr()))
                .smallerAndEqual("endDate",
                        JStringUtils.isNullOrEmpty(projectCriteriaInVO.getEndDateStr())?null:
                                JDateUtils.parseDate(projectCriteriaInVO.getEndDateStr()))
                .likes("name",
                        JStringUtils.isNullOrEmpty(projectCriteriaInVO.getName())?null:
                                projectCriteriaInVO.getName())
                .in("status",
                        JStringUtils.isNullOrEmpty(projectCriteriaInVO.getStatus())?null :
                                Arrays.asList(statuses(projectCriteriaInVO.getStatus())))
                .ready().modelPage(simplePageRequest);
    }

    private String[] statuses(String status){
        return JStringUtils.isNullOrEmpty(status)?null: status.split(",");
    }

    @Override
    public void saveProject(FillProject project) {
        if (JStringUtils.isNullOrEmpty(project.getName())) {
            throw new BusinessException("项目名称不能为空");
        }
        project.setStatus(Codes.FillProject.STATUS.NEW);
        internalProjectServiceImpl.saveOnly(project);

    }

    @Override
    public FillProject saveProject(FillProjectAddInVO projectAddInVO) {
        FillProject project = Copy.simpleCopy(projectAddInVO, FillProject.class);
        project.setStartDate(JDateUtils.parseDate(projectAddInVO.getStartDateStr()));
        project.setEndDate(JDateUtils.parseDate(projectAddInVO.getEndDateStr()));
        saveProject(project);
        fillProjectEvalTargetService.saveBatch(project.getId(), projectAddInVO.getEvalTargetsStr());

        return project;
    }

    @Override
    public FillProject updateProject(FillProjectEditInVO projecEditInVO) {
        FillProject project = Copy.simpleCopy(projecEditInVO, FillProject.class);
        project.setStartDate(JDateUtils.parseDate(projecEditInVO.getStartDateStr()));
        project.setEndDate(JDateUtils.parseDate(projecEditInVO.getEndDateStr()));
        internalProjectServiceImpl.updateOnly(project);
        fillProjectEvalTargetService.saveBatch(project.getId(), projecEditInVO.getEvalTargetsStr());

        return project;
    }

    @Override
    public void deleteById(String[] ids) {
        for(String id : ids){
            internalProjectServiceImpl.delete(id, FillProject.class);
        }
    }

    /**
     * 根据传入账号查询项目
     *
     * @param account
     * @return
     */
    @Override
    public FillAccountProjectsVO findByAccount(String account) {
        String sql0 = " select fa from FillAccount fa where a.isAvailable = 1 and fa.account = :account";
        Map<String, Object> params = new WeakHashMap<String, Object>();
        params.put("account", account);
        FillAccount fillAccount = jpqlQuery().setJpql(sql0)
                .setParams(params)
                .model();

        String sql = " select fp from FillProject fp, FillAccount fa where fa.fillProjectId = fp.id and fa.account = :account and fp.status = :publish";
        params.put("publish", Codes.FillProject.STATUS.PUBLISH);

        List<FillProject> list = jpqlQuery().setJpql(sql)
                .setParams(params)
                .models();

        FillAccountProjectsVO vo = new FillAccountProjectsVO(fillAccount, list);
        return vo;
    }

    @Override
    public FillProject findById(String id) {
        return internalProjectServiceImpl.getById(id, FillProject.class);
    }
}
