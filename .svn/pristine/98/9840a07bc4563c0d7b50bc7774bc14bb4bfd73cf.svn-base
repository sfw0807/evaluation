package com.fykj.product.evaluation.modular.evaluating.project.controller;

import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.JPageUtil;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.platform.util.JDateUtils;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.product.evaluation.common.constant.SplitCharacter;
import com.fykj.product.evaluation.modular.evaluating.Codes.Project.P_STATUS;
import com.fykj.product.evaluation.modular.evaluating.project.model.Project;
import com.fykj.product.evaluation.modular.evaluating.project.service.ProjectService;
import com.fykj.product.evaluation.modular.evaluating.project.vo.*;
import com.fykj.sample.cache.DictionaryCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/project")
@ParamValidation4Controller
public class ProjectController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private DictionaryCache dictionaryCache;
	
	
	@Autowired
	private ProjectService projectService;

	@ResponseBody
	@RequestMapping("/saveProject")
	public InvokeResult saveProject(ProjectAddInVO projectAddInVO) throws Exception {
		Project project = Copy.simpleCopy(projectAddInVO, Project.class);
		project.setStartDate(JDateUtils.parseDate(projectAddInVO.getStartDateStr()));
		project.setEndDate(JDateUtils.parseDate(projectAddInVO.getEndDateStr()));
		projectService.saveProject(project);
		return InvokeResult.success(project.getId());
	}

	@ResponseBody
	@RequestMapping("/updateProject")
	public InvokeResult updateProject(@RequestParam()ProjectEditInVO projectEditInVO) throws Exception {
		Project project = Copy.simpleCopy(projectEditInVO, Project.class);
		projectService.updateProject(project);
		return InvokeResult.success(project.getId());
	}

	@ResponseBody
	@RequestMapping("/getProjectById")
	public InvokeResult getProjectById(String id) throws Exception {
		Project project = projectService.getProjectById(id);
		ProjectDetailOutVO projectDetailOutVO = null;
		if (project != null) {
			projectDetailOutVO = Copy.simpleCopy(project, ProjectDetailOutVO.class);
			projectDetailOutVO.setStartDateStr(JDateUtils.format(projectDetailOutVO.getStartDate()));
			projectDetailOutVO.setEndDateStr(JDateUtils.format(projectDetailOutVO.getEndDate()));
			projectDetailOutVO.setStatusName(
					dictionaryCache.getDictDataName(P_STATUS.TYPE_NAME,
							projectDetailOutVO.getStatus())); 
		}
		return InvokeResult.success(projectDetailOutVO);
	}

	@ResponseBody
	@RequestMapping("/deleteProjectById")
	public InvokeResult deleteProjectById(String ids) throws Exception {
		String[] arr = ids.split(SplitCharacter.SPLIT_COMMA.key);
		projectService.deleteProjects(arr);
		return InvokeResult.success(true);
	}

	@ResponseBody
	@RequestMapping("/getProjectsByPage")
	public InvokeResult getProjectsByPage(ProjectCriteriaInVO projectCriteriaInVO,
			SimplePageRequestVO simplePageRequestVO) throws Exception {
		JPage<Project> page = projectService.getProjects(projectCriteriaInVO,
				new SimplePageRequest(simplePageRequestVO.getPage(), simplePageRequestVO.getSize()));
		List<Project> content = page.getContent();
		List<ProjectRecordOutVO> outContent = new ArrayList<ProjectRecordOutVO>();
		for (Project project : content) {
			ProjectRecordOutVO projectRecordOutVO= Copy.simpleCopy(project, ProjectRecordOutVO.class);
			projectRecordOutVO.setStartDateStr(JDateUtils.format(projectRecordOutVO.getStartDate()));
			projectRecordOutVO.setEndDateStr(JDateUtils.format(projectRecordOutVO.getEndDate()));
			projectRecordOutVO.setDateScopeStr(projectRecordOutVO.getStartDateStr()
					+SplitCharacter.SPLIT_TILDE.key+projectRecordOutVO.getEndDateStr());
			projectRecordOutVO.setStatusName(
					dictionaryCache.getDictDataName(P_STATUS.TYPE_NAME,
							projectRecordOutVO.getStatus())); 
			outContent.add(projectRecordOutVO);
		}
		JPageUtil.replaceConent(page, outContent);
		return InvokeResult.success(page);
	}


	@ResponseBody
	@RequestMapping("/getProjects")
	public InvokeResult getProjects(ProjectCriteriaInVO projectCriteriaInVO) throws Exception {
		return InvokeResult.success(projectService.getProjects(projectCriteriaInVO));
	}


	@ResponseBody
	@RequestMapping("/publish")
	public InvokeResult publish(Project project) throws Exception {
		projectService.publish(project);
		return InvokeResult.success(true);
	}

	@ResponseBody
	@RequestMapping("/next2Expert")
	public InvokeResult next2Expert(Project project) throws Exception {
		projectService.next2Expert(project);
		return InvokeResult.success(true);
	}

	@ResponseBody
	@RequestMapping("/next2End")
	public InvokeResult next2End(Project project) throws Exception {
		projectService.next2End(project);
		return InvokeResult.success(true);
	}

	@ResponseBody
	@RequestMapping("/next2Archive")
	public InvokeResult next2Archive(Project project) throws Exception {
		projectService.next2Archive(project);
		return InvokeResult.success(true);
	}
	
	
	
	
	
	
	
	
	
	
	

}
