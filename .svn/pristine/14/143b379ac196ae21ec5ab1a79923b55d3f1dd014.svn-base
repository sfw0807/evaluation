package com.fykj.product.evaluation.modular.evaluating.scoreitem.service.impl;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.JStringUtils;
import com.fykj.product.evaluation.api.filling.model.ReportDetail;
import com.fykj.product.evaluation.api.filling.service.ReportServiceApi;
import com.fykj.product.evaluation.api.filling.service.RptQuestionServiceApi;
import com.fykj.product.evaluation.api.filling.vo.PublishReportInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.model.ScoreItem;
import com.fykj.product.evaluation.api.scoreitem.service.IntervalScoreServiceApi;
import com.fykj.product.evaluation.api.scoreitem.service.ScoreItemServiceApi;
import com.fykj.product.evaluation.api.scoreitem.service.UserRoleServiceApi;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemCriteriaInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.ScoreItemOutVO;
import com.fykj.product.evaluation.api.scoreitem.vo.UserRoleOutVO;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.modular.evaluating.mitem.service.MeasureItemService;
import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.functors.ChainedClosure;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ScoreItemServiceImpl extends ServiceSupport implements ScoreItemServiceApi {
	// 得分项基本信息
	private SingleEntityManager<ScoreItem> internalScoreItemServiceImpl = SingleEntityManagerGetter
																			.get()
																			.getInstance(ScoreItem.class) ;
	@Autowired
	private RptQuestionServiceApi rptQuestionServiceApi ;
	
	@Autowired
	private ReportServiceApi reportServiceApi ;
	
	@Autowired
	private MeasureItemService measureItemService ;
	
	@Autowired
	private IntervalScoreServiceApi intervalScoreService;

	@Autowired
	private UserRoleServiceApi userRoleServiceApi;
	
	@Override
	public void saveScoreItem(ScoreItem scoreItem) {
		internalScoreItemServiceImpl.saveOnly(scoreItem);
	}

	@Override
	public void updateScoreItem(ScoreItem sc) {
		ScoreItem dbScoreItem = getScoreItemById(sc.getId()) ;
		dbScoreItem.setScoreItemName(sc.getScoreItemName());
		dbScoreItem.setHighestScore(sc.getHighestScore());
		dbScoreItem.setSerialNumber(sc.getSerialNumber());
		dbScoreItem.setRemark(sc.getRemark());
		dbScoreItem.setScoreItemDesc(sc.getScoreItemDesc());
		dbScoreItem.setDataCollectionType(sc.getDataCollectionType());
		dbScoreItem.setFillPerson(sc.getFillPerson());
		dbScoreItem.setPreliminaryType(sc.getPreliminaryType());
		dbScoreItem.setSelfPerson(sc.getSelfPerson());
		dbScoreItem.setPreliminaryExpect(sc.getPreliminaryExpect());
		dbScoreItem.setFinalPerson(sc.getFinalPerson());
		internalScoreItemServiceImpl.updateOnly(dbScoreItem);
	}
	
	@Override
	public void deleteScoreItem(ScoreItem scoreItem) {
		internalScoreItemServiceImpl.delete(scoreItem);
		// 删除得分项关联的填报选项
		rptQuestionServiceApi.deleteQuestionByScoreItemId(scoreItem.getId());
	}

	@Override
	public void deleteScoreItemById(String id) {
		internalScoreItemServiceImpl.delete(id);
		// 删除得分项关联的填报选项
		rptQuestionServiceApi.deleteQuestionByScoreItemId(id);
	}

	@Override
	public void deleteScoreItems(String[] ids) {
		for(String id : ids) {
			deleteScoreItemById(id);
		}
	}

	@Override
	public ScoreItem getScoreItemById(String id) {
		return internalScoreItemServiceImpl.getById(id) ;
	}

	@Override
	public JPage<ScoreItem> getScoreItems(ScoreItemCriteriaInVO scorepointCriteriaInVo, SimplePageRequest simplePageRequest) {

		return internalScoreItemServiceImpl
					.singleEntityQuery2()
					.conditionDefault()
					.equals("projectId", scorepointCriteriaInVo.getProjectId())
					.equals("parentQuota", JStringUtils.isNullOrEmpty(scorepointCriteriaInVo.getParentQuota()) ? null : scorepointCriteriaInVo.getParentQuota())
					.ready()
					.order()
					.asc("serialNumber")
					.ready()
					.modelPage(simplePageRequest);
	}

	@Override
	public boolean isScoreItemExist(ScoreItemInVO scoreItemInVO) {
		List<ScoreItem> scorepoints = internalScoreItemServiceImpl
					.singleEntityQuery2()
					.conditionDefault()
					.equals("scoreItemName" ,JStringUtils.isNullOrEmpty(scoreItemInVO.getScoreItemName()) ? null : scoreItemInVO.getScoreItemName())
					.equals("id" ,JStringUtils.isNullOrEmpty(scoreItemInVO.getId()) ? null : scoreItemInVO.getId())
					.ready()
					.models() ;
		return scorepoints.isEmpty() ? false : true;
	}

	@Override
	public void deleteQuestionByIds(String[] arr) {
		for(String quesId : arr) {
			// 删除填报选项
			rptQuestionServiceApi.deleteQuestionByPK(quesId);
		}
	}

	@Override
	public List<ReportQuestionOutVO> getRptQuestionByScoreItemId(String scoreItemId, String queryType) {
		return rptQuestionServiceApi.getRptQuestionByScoreItemId(scoreItemId, queryType);
	}

	@Override
	public String saveOrUpdateScoreItemFills(ReportQuestionInVO reportQuestionInVO,List<IntervalScore> intervalScores, String rptId,String projectId) {
		
		if(StringUtils.isNotBlank(rptId)){
			reportQuestionInVO.setRptDetail(setReportDetail(rptId));
		} else {
			PublishReportInVO vo = new PublishReportInVO() ;
			vo.setRptType(RptCommonConstants.RPT_TYPE_RPT);
			vo.setProjectId(projectId);
			vo.setRptSource(RptCommonConstants.SOURCE_TYPE_OTHER);
			rptId = reportServiceApi.savePublishReport(vo) ;
			reportQuestionInVO.setRptDetail(setReportDetail(rptId));
		}
		
		String questionId = rptQuestionServiceApi.saveOrUpdateRptQuestion(reportQuestionInVO) ;
		// 保存区间记分
		if(CollectionUtils.isNotEmpty(intervalScores)){
			for(IntervalScore intervalScore : intervalScores) {
				intervalScore.setFormId(questionId);
			}
			intervalScoreService.saveIntervalScore(intervalScores);
		}
		return questionId;
	}
	
	private ReportDetail setReportDetail(String rptId) {
		ReportDetail rptDetail = new ReportDetail() ;
		rptDetail.setRptId(rptId);
		return rptDetail ;
	}

	@Override
	public boolean isLastItem(String parentQuotaId) {
		return measureItemService.ifContainsItem(parentQuotaId) ? false : true;
	}

	@Override
	public String getPubReportByProjectAndType(String projectId, int rptType) {
		return reportServiceApi.getPubReportByProjectAndType(projectId, rptType);
	}

	@Override
	public List<ScoreItem> getScoreItemByItemMeasureIds(List<String> itemMeasureIds) {
		return internalScoreItemServiceImpl
				.singleEntityQuery2()
				.conditionDefault()
				.in("parentQuota", itemMeasureIds)
				.ready()
				.models() ;
	}

	@Override
	public List<ScoreItemOutVO> getScoreItemByParentQuota(String parentQuota, final String userId, String targetId) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "select s.serialNumber as serialNumber,"
						+ " s.scoreItemName as scoreItemName, s.projectId as projectId,"
						+ " s.parentQuota as parentQuota, s.id as id, "
						+ " s.highestScore as highestScore, s.scoreItemDesc as scoreItemDesc, "
						+ " s.remark as remark, m.name as parentQuotaName,"
						+ " i.selfScore as selfScore, s.fillPerson as fillPerson,"
						+ " s.selfPerson as selfPerson, s.preliminaryExpect as preliminaryExpect,"
						+ " s.finalPerson as finalPerson "
						+ " from ScoreItem s join MeasureItem m on s.parentQuota = m.id "
						+ " and s.isAvailable = 1 and m.isAvailable = 1 "
						+ " left join ItemScore i on s.id = i.itemId and i.isAvailable = 1 and i.evalTargetId = :evalTargetId "
						+ " where s.parentQuota = :parentQuota  "
						+ " order by s.serialNumber asc " ;
		params.put("parentQuota", parentQuota) ;
		params.put("evalTargetId", targetId) ;
		
		List<ScoreItemOutVO> scoreItemOutVOs = jpqlQuery().setJpql(sql)
													.setParams(params)
													.models(ScoreItemOutVO.class) ;
		
		Closure hasFillItem = new Closure() {
			@Override
			public void execute(Object obj) {
				if(obj.getClass().isAssignableFrom(ScoreItemOutVO.class)) {
					ScoreItemOutVO sc = (ScoreItemOutVO) obj ;
					List<ReportQuestionOutVO> req = getRptQuestionByScoreItemId(sc.getId(), RptCommonConstants.QUERY_TYPE_BRIEF) ;
					if(CollectionUtils.isNotEmpty(req)) {
						sc.setHasFillItem(true);
					}else {
						sc.setHasFillItem(false);
					}
				}
			}
		};
		
		Closure userRoleClouse = new Closure() {
			@Override
			public void execute(Object obj) {
				if(obj.getClass().isAssignableFrom(ScoreItemOutVO.class)) {
					ScoreItemOutVO sc = (ScoreItemOutVO) obj ;
					List<UserRoleOutVO> userRoleOutVOs = userRoleServiceApi.getUserRolesByUserId(userId) ;
					if(CollectionUtils.isNotEmpty(userRoleOutVOs)) {
						for(UserRoleOutVO userRoleOutVO : userRoleOutVOs) {
							if(StringUtils.equals(userRoleOutVO.getRoleId(), sc.getFillPerson())) {// 填报角色
								sc.setReportType(true);
							}else if(StringUtils.equals(userRoleOutVO.getRoleId(), sc.getSelfPerson())) {// 自评角色
								sc.setSelfEvaType(true);
							}else if(StringUtils.equals(userRoleOutVO.getRoleId(), sc.getPreliminaryExpect())) {// 初评专家角色
								sc.setInitEva(true);
							}else if(StringUtils.equals(userRoleOutVO.getRoleId(), sc.getFinalPerson())){// 终评角色
								sc.setFinalEva(true);
							}
						}
					}
				}
			}
		};
		
		CollectionUtils.forAllDo(scoreItemOutVOs, ChainedClosure.getInstance(hasFillItem,userRoleClouse));
		return scoreItemOutVOs;
	}

	@Override
	public ReportQuestionOutVO getQuestionById(String quesId) {

		ReportQuestionOutVO reportQuestionOutVO = rptQuestionServiceApi.getRptQuestionByPK(quesId);
		if (reportQuestionOutVO != null) {
			List<IntervalScore> intervalScores = intervalScoreService.getIntervalScoreByScoreFormId(reportQuestionOutVO.getId()) ;
			if(CollectionUtils.isNotEmpty(intervalScores)) {
				reportQuestionOutVO.setHasIntervalScores(true);
				reportQuestionOutVO.setIntervalScores(intervalScores);
			}else{
				reportQuestionOutVO.setHasIntervalScores(false);
			}
		}
		return reportQuestionOutVO ;
	}
}
