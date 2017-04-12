package com.fykj.product.evaluation.modular.filling.service;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.product.evaluation.modular.filling.model.FillProject;
import com.fykj.product.evaluation.modular.filling.vo.FillAccountProjectsVO;
import com.fykj.product.evaluation.modular.filling.vo.FillProjectEditInVO;
import com.fykj.product.evaluation.modular.filling.vo.FillProjectAddInVO;
import com.fykj.product.evaluation.modular.filling.vo.FillProjectCriteriaInVO;

/**
 * Created by liwang on 2017/4/6.
 */
public interface FillProjectService {
    public JPage<FillProject> getProjects(FillProjectCriteriaInVO projectCriteriaInVO,
                                          SimplePageRequest simplePageRequest);
    public void saveProject(FillProject project);
    public FillProject saveProject(FillProjectAddInVO projectAddInVO);
    public FillProject updateProject(FillProjectEditInVO projecEditInVO);
    public void deleteById(String[] ids);

    public FillAccountProjectsVO findByAccount(String account);

    public FillProject findById(String id);
}
