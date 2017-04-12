package com.fykj.product.evaluation.modular.evaluating.project.service;

import java.util.List;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.product.evaluation.modular.evaluating.project.model.Project;
import com.fykj.product.evaluation.modular.evaluating.project.vo.ProjectCriteriaInVO;

public interface ProjectService {

	void saveProject(Project project);
	
	void updateProject(Project project);
	
	void deleteProject(Project project);
	
	void deleteProjectById(String id);
	
	void deleteProjects(String [] ids);
	
	Project getProjectById(String id);
	
	JPage<Project> getProjects(ProjectCriteriaInVO projectCriteriaInVO , SimplePageRequest simplePageRequest);
	 
//	boolean exists(String name);
	
	List<Project> getProjects(ProjectCriteriaInVO projectCriteriaInVO);
	
	void publish(Project project);
	
	void next2Expert(Project project);
	
	void next2End(Project project);
	
	void next2Archive(Project project);
	
	
	
	
	
	
}
