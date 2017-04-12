package com.fykj.product.evaluation.modular.filling.controller;

import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.JPageUtil;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.platform.util.JDateUtils;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.product.evaluation.common.constant.SplitCharacter;
import com.fykj.product.evaluation.modular.filling.Codes;
import com.fykj.product.evaluation.modular.filling.model.FillProject;
import com.fykj.product.evaluation.modular.filling.service.FillProjectEvalTargetService;
import com.fykj.product.evaluation.modular.filling.service.FillProjectService;
import com.fykj.product.evaluation.modular.filling.vo.*;
import com.fykj.sample.cache.DictionaryCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.ws.spi.Invoker;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liwang on 2017/4/6.
 */
@Controller
@RequestMapping("/fillProject")
@ParamValidation4Controller
public class FillProjectController {

    @Autowired
    private DictionaryCache dictionaryCache;

    @Autowired
    FillProjectService fillProjectService;

    @Autowired
    FillProjectEvalTargetService fillProjectEvalTargetService;

    @ResponseBody
    @RequestMapping("/getProjectsByPage")
    public InvokeResult getProjectsByPage(FillProjectCriteriaInVO projectCriteriaInVO,
                                          SimplePageRequestVO simplePageRequestVO) throws Exception {
        JPage<FillProject> page = fillProjectService.getProjects(projectCriteriaInVO,
                new SimplePageRequest(simplePageRequestVO.getPage(), simplePageRequestVO.getSize()));
        List<FillProject> content = page.getContent();
        List<FillProjectRecordOutVO> outContent = new ArrayList<FillProjectRecordOutVO>();
        for (FillProject project : content) {
            FillProjectRecordOutVO projectRecordOutVO = Copy.simpleCopy(project, FillProjectRecordOutVO.class);
            projectRecordOutVO.setStartDateStr(JDateUtils.format(projectRecordOutVO.getStartDate()));
            projectRecordOutVO.setEndDateStr(JDateUtils.format(projectRecordOutVO.getEndDate()));
            projectRecordOutVO.setDateScopeStr(projectRecordOutVO.getStartDateStr()
                    + SplitCharacter.SPLIT_TILDE.key + projectRecordOutVO.getEndDateStr());
            projectRecordOutVO.setStatusName(
                    dictionaryCache.getDictDataName(Codes.FillProject.STATUS.TYPE_NAME,
                            projectRecordOutVO.getStatus()));
            projectRecordOutVO.setSearchTypeStr(
                    dictionaryCache.getDictDataName(Codes.FillProject.SEARCH_TYPE.TYPE_NAME,
                            String.valueOf(projectRecordOutVO.getSearchType()))
            );
            outContent.add(projectRecordOutVO);
        }
        JPageUtil.replaceConent(page, outContent);
        return InvokeResult.success(page);
    }

    @ResponseBody
    @RequestMapping("/saveProject")
    public InvokeResult saveProject(FillProjectAddInVO projectAddInVO) throws Exception {
        FillProject project = fillProjectService.saveProject(projectAddInVO);
        return InvokeResult.success(project.getId());
    }

    @ResponseBody
    @RequestMapping("/updateProject")
    public InvokeResult updateProject(FillProjectEditInVO projectEditInVO) throws Exception {
        FillProject project = fillProjectService.updateProject(projectEditInVO);
        return InvokeResult.success(project.getId());
    }

    @ResponseBody
    @RequestMapping("/deleteById")
    public InvokeResult deleteById(String ids) throws Exception {
        String[] arr = ids.split(SplitCharacter.SPLIT_COMMA.key);
        fillProjectService.deleteById(arr);
        return InvokeResult.success(true);
    }

    @ResponseBody
    @RequestMapping("/getFillProjectById")
    public InvokeResult getFillProjectById(String id){
        FillProject fillProject = fillProjectService.findById(id);
        FillProjectRecordOutVO projectRecordOutVO = Copy.simpleCopy(fillProject, FillProjectRecordOutVO.class);

        projectRecordOutVO.setStartDateStr(JDateUtils.format(projectRecordOutVO.getStartDate()));
        projectRecordOutVO.setEndDateStr(JDateUtils.format(projectRecordOutVO.getEndDate()));
        projectRecordOutVO.setStatusName(
                dictionaryCache.getDictDataName(Codes.FillProject.STATUS.TYPE_NAME,
                        projectRecordOutVO.getStatus()));
        projectRecordOutVO.setSearchTypeStr(
                dictionaryCache.getDictDataName(Codes.FillProject.SEARCH_TYPE.TYPE_NAME,
                        String.valueOf(projectRecordOutVO.getSearchType())));
        return InvokeResult.success(projectRecordOutVO);
    }

    @ResponseBody
    @RequestMapping("/getEvalTargetByProjectId")
    public InvokeResult getEvalTargetByProjectId(String projectId){
        List<FillProjectEvalTargetOutVO> list = fillProjectEvalTargetService.getOutByProjectId(projectId);
        return InvokeResult.success(list);
    }
}
