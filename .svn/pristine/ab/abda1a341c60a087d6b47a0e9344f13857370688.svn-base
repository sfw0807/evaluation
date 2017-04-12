package com.fykj.product.evaluation.modular.filling.service.impl;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.api.filling.model.ReportDetail;
import com.fykj.product.evaluation.api.filling.model.ReportQuestion;
import com.fykj.product.evaluation.api.filling.model.RptQuestionOption;
import com.fykj.product.evaluation.api.filling.model.RptRemark;
import com.fykj.product.evaluation.api.filling.service.RptQuestionServiceApi;
import com.fykj.product.evaluation.api.filling.vo.*;
import com.fykj.product.evaluation.api.scoreitem.model.ScoreItem;
import com.fykj.product.evaluation.api.scoreitem.service.ScoreItemServiceApi;
import com.fykj.product.evaluation.common.constant.FillType;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.modular.filling.service.RptAnswerService;
import com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService;
import com.fykj.product.evaluation.modular.filling.service.RptQuestionService;
import com.fykj.product.evaluation.modular.filling.service.RptRemarkService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

@Service
public class RptQuestionServiceImpl extends ServiceSupport implements RptQuestionService,RptQuestionServiceApi  {

	
	@Autowired
	RptAnswerService rptAnswerService;
	
	@Autowired
	RptRemarkService rptRemarkService;
	
	@Autowired
	RptPubAnswerService rptPubAnswerService;
	
	@Autowired
	ScoreItemServiceApi scoreItemService;
	
	private SingleEntityManager<ReportQuestion> reportQuestionServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(ReportQuestion.class);

	private SingleEntityManager<RptQuestionOption> rptQuestionOptionImpl = SingleEntityManagerGetter.get()
			.getInstance(RptQuestionOption.class);
	
	private SingleEntityManager<ReportDetail> rptDetailImpl = SingleEntityManagerGetter.get()
			.getInstance(ReportDetail.class);
	
	@Override
	public String saveOrUpdateRptQuestion(ReportQuestionInVO reportQuestionInVO) {

		//判断有id是更新操作，就是先删除再保存
		if (StringUtils.isNotBlank(reportQuestionInVO.getQueId()) && !StringUtils.equals(reportQuestionInVO.getQueId(),"null")) {
			deleteQuestionByPK(reportQuestionInVO.getQueId());
		}

		ReportQuestion question = Copy.simpleCopy(reportQuestionInVO, ReportQuestion.class);
		saveRptQuestionContent(question);
		String queType = reportQuestionInVO.getQuestionType();
		
		if(queType.equals(FillType.FILL_SINGLE.getKey()) || queType.equals(FillType.FILL_MULTI.getKey())) {
			saveQuestionOptions(reportQuestionInVO.getOptions(), question.getId());
		}
		
		ReportDetail rptDetail = reportQuestionInVO.getRptDetail();
		rptDetail.setRptQuesionId(question.getId());
		saveRptDetail(rptDetail);
		return question.getId();
	}

	
	@Override
	public void saveQuestionOptions(List<RptQuestionOption> options, String questionId) {
		if(options == null)
			return;
		
		for(RptQuestionOption option : options) {
			option.setRptQuestionId(questionId);
			rptQuestionOptionImpl.saveOnly(option);
		}
	}


	@Override
	public List<ReportQuestionOutVO> getRptQuestionByScoreItemId(String scoreItemId, String queryType) {
		
		String sql = "SELECT q.id FROM ReportQuestion q where q.scoreItemId = :scoreItemId order by q.questionCode asc,q.questionType asc";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("scoreItemId", scoreItemId);
		
		List<String> queIds = jpqlQuery().setJpql(sql)
				.setParams(params)
				.models();
		List<ReportQuestionOutVO> queList = new ArrayList<ReportQuestionOutVO>();
		if(queIds != null && queIds.size() > 0) {
			for(String queId : queIds) {
				queList.add(getRptQueByPkQueryType(queId, queryType));
			}
		}
		return queList;
	}


	@Override
	public void deleteQuestionByScoreItemId(String scoreItemId) {
		/*String sql= internalMeasureItemServiceImpl.selectCause()
				+" from MeasureItem  "
				+ " where projectId = :projectId and isAvailable=1";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("projectId", projectId);
		
		List<MeasureItemDetailOutVO> list = 
				jpqlQuery().setJpql(sql)
				.setParams(params)
				.models(MeasureItemDetailOutVO.class);
		return JTreeUtils.buildTree("-1", list, JTreeNodeMenu.class);*/
		
	/*	return internalScoreItemServiceImpl
				.singleEntityQuery2()
				.conditionDefault()
				.equals("parentQuota", scorepointCriteriaInVo.getParentQuota())
				.ready()
				.order()
				.asc("serialNumber")
				.ready()
				.modelPage(simplePageRequest);*/
		
		List<ReportQuestion> questionList = reportQuestionServiceImpl.singleEntityQuery2()
		.conditionDefault()
		.equals("scoreItemId", scoreItemId)
		.ready()
		.models();
		deleteQuestions(questionList);
	}
	
	@Override
	public void deleteQuestionByPK(String queId) {

		ReportQuestion que = reportQuestionServiceImpl.getById(queId, ReportQuestion.class);
		if(FillType.FILL_SINGLE.getKey().equals(que.getQuestionType()) || FillType.FILL_MULTI.getKey().equals(que.getQuestionType())) {
			deleteOptionsByQueId(que.getId());
		}
		deleteRptDetailByQueId(queId);
		reportQuestionServiceImpl.deletePermanently(que);
	}

	private void deleteRptDetailByQueId(String queId) {
		String sql = "delete from ReportDetail rd where rd.rptQuesionId = :rptQuesionId";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("rptQuesionId", queId);
		jpqlQuery().setJpql(sql)
				.setParams(params)
				.setUpdate(true)
				.model();
	}


	@Override
	public ReportQuestionOutVO getRptQuestionByPK(String queId) {
		
		ReportQuestionOutVO question = getRptQueByPkQueryType(queId, RptCommonConstants.QUERY_TYPE_DETAIL);
		return question;
	}

	@Override
	public ReportQuestionOutVO getRptQueByPkQueryType(String queId, String queryType) {
		
		String sql = "SELECT q.id as id, q.questionContent as questionContent, q.questionType as questionType, q.requiredStatus as requiredStatus, q.questionCode as questionCode, rd.rptId as rptId  FROM ReportQuestion q "
				+ " left join ReportDetail rd on q.id = rd.rptQuesionId where q.id = :id";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("id", queId);
		
		ReportQuestionOutVO question = jpqlQuery().setJpql(sql)
				.setParams(params)
				.model(ReportQuestionOutVO.class);
		
		if(RptCommonConstants.QUERY_TYPE_DETAIL.equals(queryType) && (
				FillType.FILL_SINGLE.getKey().equals(question.getQuestionType()) || FillType.FILL_MULTI.getKey().equals(question.getQuestionType()))) {
			List<RptQuestionOption> options = getOptionsByQueId(queId);
			question.setOptions(options);
		}
		
		return question;
	}
	
	private void deleteQuestions(List<ReportQuestion> questionList) {
		for(ReportQuestion que : questionList) {
			deleteOptionsByQueId(que.getId());
			reportQuestionServiceImpl.deletePermanently(que);
		}
	}
	
	private void deleteOptionsByQueId(String queId) {
		String sql = "delete RptQuestionOption ro where ro.rptQuestionId = :queId";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("queId", queId);
		jpqlQuery().setJpql(sql)
		.setParams(params)
		.setUpdate(true)
		.model();
		
	}
	
	private List<RptQuestionOption> getOptionsByQueId(String queId) {
		return rptQuestionOptionImpl
		.singleEntityQuery2()
		.conditionDefault()
		.equals("rptQuestionId", queId)
		.ready()
		.order()
		.asc("optName")   //optName是1 2 3 4 或者 A B C D
		.ready()
		.models();
		
	}
	

	/**
	 * @return
	 * 保存题干
	 */
	private void saveRptQuestionContent(ReportQuestion question) {
		reportQuestionServiceImpl.saveOnly(question);
	}
	
	private void saveRptDetail(ReportDetail rptDetail) {
		rptDetailImpl.saveOnly(rptDetail);
	}
	
	@Override
	public ReportQueAnsWraperVO getRptQueAnsByScoreItemId(String scoreItemId, String targetId, String projectId) {
		
//		String rptPubAnsId = rptPubAnswerService.getRptPubAnsIdBytargetId(targetId, projectId);
		
		ReportQueAnsWraperVO vo = new ReportQueAnsWraperVO();
		List<ReportQuestionOutVO> queList = this.getRptQuestionByScoreItemId(scoreItemId, RptCommonConstants.QUERY_TYPE_DETAIL);
		List<ReportQueAnsOutVO> outVOList = new ArrayList<ReportQueAnsOutVO>();
		for(ReportQuestionOutVO queVO : queList) {
			
			ReportQueAnsOutVO queAnsVO = buildRptQueAnsOutVO(queVO);
			ReportAnswerOutVO ansOutVO = rptAnswerService.getAnswerByQuestionId(queAnsVO.getId(), targetId, projectId);
			queAnsVO.setAnsOutVO(ansOutVO);
			outVOList.add(queAnsVO);
		}
		
		vo.setOutVO(outVOList);
		RptRemark remark = rptRemarkService.getRemarkByScoreItemId(scoreItemId, targetId);
		vo.setRemark(remark);
		return vo;
	}
	
	private ReportQueAnsOutVO buildRptQueAnsOutVO(ReportQuestionOutVO vo){
		ReportQueAnsOutVO outVO = Copy.simpleCopy(vo, ReportQueAnsOutVO.class);
		outVO.setOptions(vo.getOptions());
		return outVO;
	}


	@Override
	public RptQueAnsStisWraperVO getQueAnsStisReport(String scoreItemId) {
		
		ScoreItem scoreItem = scoreItemService.getScoreItemById(scoreItemId);
		RptQueAnsStisWraperVO resVO = new RptQueAnsStisWraperVO();
		List<ReportQuestionOutVO> queList = this.getRptQuestionByScoreItemId(scoreItemId, RptCommonConstants.QUERY_TYPE_BRIEF);
		resVO.setQueList(queList);
		
		
		
		List<ReportAnswerOutWraperVO> ansList = rptAnswerService.getAnswersByScoreId(scoreItemId, RptCommonConstants.OPT_DETAIL, scoreItem.getProjectId());
		resVO.setAnsList(ansList);
		return resVO;
	}

}
