package com.fykj.product.evaluation.modular.evaluating.project.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.JDateUtils;
import com.fykj.platform.util.JStringUtils;
import com.fykj.product.evaluation.api.scoreitem.model.ScoreItem;
import com.fykj.product.evaluation.api.scoreitem.service.ScoreItemServiceApi;
import com.fykj.product.evaluation.modular.evaluating.Codes.Project.P_STATUS;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.service.EvalTargetService;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.EvalTargetCriteriaInVO;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.EvalTargetDetailOutVO;
import com.fykj.product.evaluation.modular.evaluating.itemscore.model.ItemScore;
import com.fykj.product.evaluation.modular.evaluating.itemscore.service.ItemScoreService;
import com.fykj.product.evaluation.modular.evaluating.itemscore.service.ScoreType;
import com.fykj.product.evaluation.modular.evaluating.mitem.model.MeasureItem;
import com.fykj.product.evaluation.modular.evaluating.mitem.service.MeasureItemService;
import com.fykj.product.evaluation.modular.evaluating.project.model.Project;
import com.fykj.product.evaluation.modular.evaluating.project.service.ProjectService;
import com.fykj.product.evaluation.modular.evaluating.project.vo.ProjectCriteriaInVO;

@Service
public class ProjectServiceImpl extends ServiceSupport 
implements ProjectService {

	private SingleEntityManager<Project> internalProjectServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(Project.class);
	
	@Autowired
	private MeasureItemService measureItemService;
	
	@Autowired
	private ScoreItemServiceApi scoreItemServiceApi;
	
	@Autowired
	private EvalTargetService evalTargetService;
	
	@Autowired
	private ItemScoreService itemScoreService;

	@Override
	public void saveProject(Project project) {
		if (JStringUtils.isNullOrEmpty(project.getName())) {
			throw new BusinessException("项目名称不能为空");
		}
		project.setStatus(P_STATUS.NEW);
		internalProjectServiceImpl.saveOnly(project);
	}

//	@Override
//	public boolean exists(String code) {
//		Project param = internalProjectServiceImpl.singleEntityQuery2().conditionDefault().equals("code", code)
//				.ready().model();
//		return param != null;
//	}

	@Override
	public void updateProject(Project project) {
		Project dbProject = getProjectById(project.getId());
		// dbProject.setCode(project.getCode());
		dbProject.setStartDate(project.getStartDate());
		dbProject.setEndDate(project.getEndDate());
		dbProject.setScore(project.getScore());
		dbProject.setFlowId(project.getFlowId());
		dbProject.setDescription(project.getDescription());
		dbProject.setDicLocation(project.getDicLocation());
		internalProjectServiceImpl.updateOnly(dbProject);
	}

	@Override
	public void deleteProject(Project project) {
		internalProjectServiceImpl.delete(project);
	}

	@Override
	public void deleteProjectById(String id) {
		internalProjectServiceImpl.delete(id);
	}

	@Override
	public Project getProjectById(String id) {
		return internalProjectServiceImpl.getById(id);
	}

	private String[] statuses(String status){
		return JStringUtils.isNullOrEmpty(status)?null: status.split(",");
	}
	
	@Override
	public JPage<Project> getProjects(ProjectCriteriaInVO projectCriteriaInVO,
			SimplePageRequest simplePageRequest) {
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
	
	@Override
	public List<Project> getProjects(ProjectCriteriaInVO projectCriteriaInVO) {
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
				.equals("status", 
						JStringUtils.isNullOrEmpty(projectCriteriaInVO.getStatus())?null:
							projectCriteriaInVO.getStatus())
				.ready().models();
	}

	
	
	@Override
	public void deleteProjects(String[] ids) {
		for (String id : ids) {
			deleteProjectById(id);
		}
	}
	
	@Override
	public void publish(Project project) {
		
		List<MeasureItem> measureItems= measureItemService.children("-1", project.getId(), true);
		
		List<String> ids=new ArrayList<>();
		for(MeasureItem item:measureItems){
			ids.add(item.getId());
		}
		ids.add("");
		List<ScoreItem> scoreItems= scoreItemServiceApi.getScoreItemByItemMeasureIds(ids);
		
		EvalTargetCriteriaInVO criteriaInVO=new EvalTargetCriteriaInVO();
		criteriaInVO.setProjectId(project.getId());
		
		List<EvalTargetDetailOutVO> evalTargetDetailOutVOs=  evalTargetService.getBindEvalTargetOnProject(criteriaInVO);
		for(EvalTargetDetailOutVO detailOutVO:evalTargetDetailOutVOs){
			for(ScoreItem scoreItem:scoreItems){
				itemScoreService.addItemScore(scoreItem.getId(), detailOutVO.getId(),project.getId());
			}
		}
		Project dbProject=  internalProjectServiceImpl.getById(project.getId());
		dbProject.setStatus(P_STATUS.PUBLISH);
		internalProjectServiceImpl.updateOnly(dbProject);
	}
	
	@Override
	public void next2Expert(Project project) {
		Project dbProject=  internalProjectServiceImpl.getById(project.getId());
		dbProject.setStatus(P_STATUS.EXPERT_EVAL);
		internalProjectServiceImpl.updateOnly(dbProject);
	}
	
	@Override
	public void next2End(Project project) {
		Project dbProject=  internalProjectServiceImpl.getById(project.getId());
		dbProject.setStatus(P_STATUS.END_EVAL);
		internalProjectServiceImpl.updateOnly(dbProject);
	}
	
	
	@Override
	public void next2Archive(Project project) {
		Project dbProject=  internalProjectServiceImpl.getById(project.getId());
		dbProject.setStatus(P_STATUS.ARCHIVE);
		internalProjectServiceImpl.updateOnly(dbProject);
		List<ItemScore> itemScores=  itemScoreService.getItemScoreByProjectId(dbProject.getId());
		for(ItemScore itemScore:itemScores ){
			itemScoreService.updateScore(itemScore.getId(), 
					itemScore.getEndScore(), ScoreType.FINAL);
		}
	}
	
	
	
	
	
	
}
