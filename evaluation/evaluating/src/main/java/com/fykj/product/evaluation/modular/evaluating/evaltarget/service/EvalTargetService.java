package com.fykj.product.evaluation.modular.evaluating.evaltarget.service;

import java.util.List;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.model.EvalTarget;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.EvalTargetCriteriaInVO;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.EvalTargetDetailOutVO;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.UserDetailCriteriaInVO;
import com.fykj.product.evaluation.modular.evaluating.evaltarget.vo.UserDetailVO;

public interface EvalTargetService {

	void saveEvalTarget(EvalTarget evalTarget);
	
	void updateEvalTarget(EvalTarget evalTarget);
	
	void deleteEvalTarget(EvalTarget evalTarget);
	
	void deleteEvalTargetById(String id);
	
	void deleteEvalTargets(String [] ids);
	
	EvalTarget getEvalTargetById(String id);
	
	JPage<EvalTarget> getEvalTargets(EvalTargetCriteriaInVO evalTargetCriteriaInVO , SimplePageRequest simplePageRequest);
	 
//	boolean exists(String name);
	
	boolean exists(String code);
	
	List<EvalTarget> getEvalTargets(EvalTargetCriteriaInVO evalTargetCriteriaInVO);
	
	JPage<UserDetailVO> getBindUser(UserDetailCriteriaInVO userDetailCriteriaInVO, SimplePageRequest simplePageRequest);
	
	JPage<UserDetailVO> getUnbindUser(UserDetailCriteriaInVO userDetailCriteriaInVO, SimplePageRequest simplePageRequest);
	
	void bindEvalTargetUser(String evalTargetId,String[] userIds);

	void unbindEvalTargetUser(String evalTargetId,String[] userIds);

	EvalTarget getEvalTargetByUserId(String userId);
	
	List<EvalTargetDetailOutVO> getBindEvalTargetOnProject(EvalTargetCriteriaInVO evalTargetCriteriaInVO);
	
	JPage<EvalTargetDetailOutVO> getBindEvalTargetOnProject(EvalTargetCriteriaInVO evalTargetCriteriaInVO, SimplePageRequest simplePageRequest);
	
	JPage<EvalTargetDetailOutVO> getUnbindEvalTargetOnProject(EvalTargetCriteriaInVO evalTargetCriteriaInVO, SimplePageRequest simplePageRequest);
	
	void bindEvalTargetProject(String[] evalTargetIds,String projectId);

	void unbindEvalTargetProject(String[] evalTargetIds,String projectId);
	
}
