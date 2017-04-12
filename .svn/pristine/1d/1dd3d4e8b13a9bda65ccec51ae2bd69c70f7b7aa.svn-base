package com.fykj.product.evaluation.modular.filling.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.api.filling.model.ReportAnswer;
import com.fykj.product.evaluation.api.filling.model.RptAnswerOption;
import com.fykj.product.evaluation.api.filling.model.RptRemark;
import com.fykj.product.evaluation.api.filling.model.RptResource;
import com.fykj.product.evaluation.api.filling.service.RptAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.vo.CalcuateScoreOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutWraperVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceCriteriaInVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceInVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceOutVO;
import com.fykj.product.evaluation.api.filling.vo.RptResourceWraperOutVO;
import com.fykj.product.evaluation.common.constant.FillType;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;
import com.fykj.product.evaluation.modular.filling.service.RptAnswerService;
import com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService;
import com.fykj.product.evaluation.modular.filling.service.RptRemarkService;
import com.fykj.product.evaluation.modular.filling.service.RtpResourceService;

@Service
public class RptAnswerServiceImpl extends ServiceSupport implements RptAnswerService,RptAnswerServiceApi {
	
	@Autowired
	RptRemarkService rptRemarkService;
	
	@Autowired
	RtpResourceService rtpResourceService;
	
	@Autowired
	RptPubAnswerService rptPubAnswerService;
	
	private SingleEntityManager<ReportAnswer> reportAnswerImpl = SingleEntityManagerGetter.get()
			.getInstance(ReportAnswer.class);
	
	private SingleEntityManager<RptAnswerOption> rptAnswerOptionImpl = SingleEntityManagerGetter.get()
			.getInstance(RptAnswerOption.class);

	@Override
	public String saveOrUpdateAnswer(ReportAnswer rptAnswer, List<RptAnswerOption> rptAnsOpts, String queType) {
		
		//判断是否已经答过题
		if(StringUtils.isNotBlank(rptAnswer.getId())) {
			updateAnswer(rptAnswer, rptAnsOpts, queType);
		} else {
			reportAnswerImpl.saveOnly(rptAnswer);
			//如果是单选或者多选需要保存问答选项
			if(queType.equals(FillType.FILL_SINGLE.getKey()) || queType.equals(FillType.FILL_MULTI.getKey())) {
				saveAnsOpts(rptAnswer.getId(), rptAnsOpts);
			}
		}
		return rptAnswer.getId();
	}

	private void saveAnsOpts(String rptAnswerId, List<RptAnswerOption> rptAnsOpts) {
		for(RptAnswerOption rptAnsOpt : rptAnsOpts) {
			rptAnsOpt.setRptAnswerId(rptAnswerId);
			rptAnswerOptionImpl.saveOnly(rptAnsOpt);
		}
	}

	@Override
	public void saveOrUpdateAnswers(List<ReportAnswerInVO> answers) {
		for(ReportAnswerInVO in : answers) {
			ReportAnswer rptAnswer = Copy.simpleCopy(in, ReportAnswer.class);
			List<RptAnswerOption> rptAnsOpts = buildRptAnsOpts(in);
			saveOrUpdateAnswer(rptAnswer, rptAnsOpts, in.getQuestionType());
		}
		
	}

	private List<RptAnswerOption> buildRptAnsOpts(ReportAnswerInVO in) {
		String queType = in.getQuestionType();
		List<RptAnswerOption> res = null;
		if(queType.equals(FillType.FILL_SINGLE.getKey()) || queType.equals(FillType.FILL_MULTI.getKey())) {
			res = new ArrayList<RptAnswerOption>();
			for(String rptQueOptId : in.getRptQueOptIds()) {
				RptAnswerOption tmp = new RptAnswerOption();
				tmp.setRptQueOptId(rptQueOptId);
				res.add(tmp);
			}
			
		} 
		return res;
		
	}

	private void updateAnswer(ReportAnswer rptAnswer, List<RptAnswerOption> rptAnsOpts, String queType) {
		
		ReportAnswer old = reportAnswerImpl.getById(rptAnswer.getId());
		old.setScore(rptAnswer.getScore());
		//TODO 需要把更新的内容放在这里
		if(queType.equals(FillType.FILL_SINGLE.getKey()) || queType.equals(FillType.FILL_MULTI.getKey())) {
			delAnsOptsByAnsId(rptAnswer.getId());
			saveAnsOpts(rptAnswer.getId(), rptAnsOpts);
		} else {
			old.setAnswer(rptAnswer.getAnswer()); //问答
		}
		
		reportAnswerImpl.saveOnly(old);
	}
	
	
	private void delAnsOptsByAnsId(String rptAnswerId) {
		String sql = "delete RptAnswerOption ro where ro.rptAnswerId = :rptAnswerId";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("rptAnswerId", rptAnswerId);
		jpqlQuery().setJpql(sql)
		.setParams(params)
		.setUpdate(true)
		.model();
	}

	@Override
	public void saveOrUpdateAnswers(ReportAnswerInWraperVO wraper) {
		saveOrUpdateAnswers(wraper.getAnswers());
		
		//更新或者新增remark
		if(StringUtils.isNotBlank(wraper.getRemark())) {
			RptRemark remark = Copy.simpleCopy(wraper, RptRemark.class);
			if(StringUtils.isNotBlank(wraper.getRemarkId())) {
				remark.setId(wraper.getRemarkId());
			}
			saveOrUpdateRemark(remark);
		}
		
		
	}
	
	@Override
	public ReportAnswerOutWraperVO getAnswerByScoreId(String scoreItemId, String targetId, String projectId) {
		
		String rptPubAnsId = rptPubAnswerService.getRptPubAnsIdBytargetId(targetId, projectId);
		
		String sql = "select ra.id as id, ra.rptQuestionId as rptQuestionId, ra.rptPubAnswerId as rptPubAnswerId, ra.answer as answer, rq.questionType as questionType, ra.answerNum as answerNum "
				+ " from ReportAnswer ra left join ReportQuestion rq on rq.id = ra.rptQuestionId where ra.scoreItemId = :scoreItemId and ra.rptPubAnswerId = :rptPubAnswerId";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("scoreItemId", scoreItemId);
		params.put("rptPubAnswerId", rptPubAnsId);
		
		List<ReportAnswerOutVO> ansList = jpqlQuery().setJpql(sql)
				.setParams(params)
				.models(ReportAnswerOutVO.class);
		
		for(ReportAnswerOutVO vo : ansList) {
			if(FillType.FILL_SINGLE.getKey().equals(vo.getQuestionType()) || FillType.FILL_MULTI.getKey().equals(vo.getQuestionType())) {
				List<RptAnswerOption> rptQueOptIds = getAnsOptsByRptAnserId(vo.getId());
				vo.setRptQueOptIds(rptQueOptIds);
			} else if(FillType.FILL_ATTACH.getKey().equals(vo.getQuestionType())) {
				List<RptResource> resources = rtpResourceService.getResByRptAnswerId(vo.getId());
				vo.setResources(resources);
			}
		}
		
		ReportAnswerOutWraperVO wrapper = new ReportAnswerOutWraperVO();
		wrapper.setAnswerList(ansList);
		wrapper.setRemark(getRemark(scoreItemId, targetId));
		return wrapper;
		
		
	}
	
	private List<RptAnswerOption> getAnsOptsByRptAnserId(String rptAnswerId) {
		List<RptAnswerOption> ansList =	rptAnswerOptionImpl.singleEntityQuery2()
		.conditionDefault()
		.equals("rptAnswerId",rptAnswerId)
		.ready()
		.models();
		
		if(ansList == null) {
			return new ArrayList<RptAnswerOption>();
		}
		return ansList;
	}
	
	private RptRemark getRemark(String scoreItemId, String targetId) {
		return rptRemarkService.getRemarkByScoreItemId(scoreItemId, targetId);
	}
	
	private String saveOrUpdateRemark(RptRemark remark) {
		if(StringUtils.isNotBlank(remark.getId())) {
			RptRemark remarkOld = rptRemarkService.getRptRemarkByPK(remark.getId());
			if(remarkOld == null) {
				rptRemarkService.saveRptRemark(remark);
			} else {
				remarkOld.setRemark(remark.getRemark());
				rptRemarkService.updateRptRemarkByPK(remarkOld);
			}
			
		} else {
			rptRemarkService.saveRptRemark(remark);
		}
		
		return remark.getId();
	}

	@Override
	public ReportAnswerOutVO getAnswerByQuestionId(String queId, String targetId, String projectId) {
		
		String rptPubAnsId = rptPubAnswerService.getRptPubAnsIdBytargetId(targetId, projectId);
		
		String sql = "select ra.id as id, ra.rptQuestionId as rptQuestionId, ra.rptPubAnswerId as rptPubAnswerId, ra.answer as answer, rq.questionType as questionType "
				+ " from ReportAnswer ra left join ReportQuestion rq on rq.id = ra.rptQuestionId where ra.rptQuestionId = :rptQuestionId and ra.rptPubAnswerId = :rptPubAnswerId";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("rptQuestionId", queId);
		params.put("rptPubAnswerId", rptPubAnsId);
		
		List<ReportAnswerOutVO> ansList = jpqlQuery().setJpql(sql)
				.setParams(params)
				.models(ReportAnswerOutVO.class);
		
		for(ReportAnswerOutVO vo : ansList) {
			if(FillType.FILL_SINGLE.getKey().equals(vo.getQuestionType()) || FillType.FILL_MULTI.getKey().equals(vo.getQuestionType())) {
				List<RptAnswerOption> rptQueOptIds = getAnsOptsByRptAnserId(vo.getId());
				vo.setRptQueOptIds(rptQueOptIds);
			} else if(FillType.FILL_ATTACH.getKey().equals(vo.getQuestionType())) {
				List<RptResource> resources = rtpResourceService.getResByRptAnswerId(vo.getId());
				vo.setResources(resources);
			}
		}
		
		
		if(CollectionUtils.isNotEmpty(ansList)) {
			return ansList.get(0);
		} 
		return null;
		
	}

	@Override
	public List<ReportAnswerOutWraperVO> getAnswersByScoreId(String scoreItemId, String type, String projectId) {
		
		
		String sql = "select distinct rp.targetId from ReportAnswer ra "
				+ " left join RptPublishAnswer rp on ra.rptPubAnswerId = rp.id where ra.scoreItemId = :scoreItemId";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("scoreItemId", scoreItemId);
		
		List<String> targetIds = jpqlQuery().setJpql(sql)
				.setParams(params)
				.models();
		
		List<ReportAnswerOutWraperVO> ansList = new ArrayList<ReportAnswerOutWraperVO>();
		for(String targetId : targetIds){
			ReportAnswerOutWraperVO vo = this.getAnswerWithContByScoreId(scoreItemId, targetId, RptCommonConstants.OPT_DETAIL, projectId);// targetId
			vo.setTargetId(targetId);
			ansList.add(vo);
		}
		
		
		return ansList;
	
	}
	
	private ReportAnswerOutWraperVO getAnswerWithContByScoreId(String scoreItemId, String targetId, String type, String projectId) {
		
		String rptPubAnsId = rptPubAnswerService.getRptPubAnsIdBytargetId(targetId, projectId);
		
		String sql = "select ra.id as id, ra.rptQuestionId as rptQuestionId, ra.rptPubAnswerId as rptPubAnswerId, ra.answer as answer, rq.questionType as questionType, ra.answerNum as answerNum "
				+ " from ReportAnswer ra left join ReportQuestion rq on rq.id = ra.rptQuestionId where ra.scoreItemId = :scoreItemId and ra.rptPubAnswerId = :rptPubAnswerId";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("scoreItemId", scoreItemId);
		params.put("rptPubAnswerId", rptPubAnsId);
		
		List<ReportAnswerOutVO> ansList = jpqlQuery().setJpql(sql)
				.setParams(params)
				.models(ReportAnswerOutVO.class);
		
		for(ReportAnswerOutVO vo : ansList) {
			if(FillType.FILL_SINGLE.getKey().equals(vo.getQuestionType()) || FillType.FILL_MULTI.getKey().equals(vo.getQuestionType())) {
				List<RptAnswerOption> rptQueOptIds = null;
				if(RptCommonConstants.OPT_BRIEF.equals(type)) {
					rptQueOptIds = getAnsOptsByRptAnserId(vo.getId());
				} else if(RptCommonConstants.OPT_DETAIL.equals(type)) {
					rptQueOptIds = getAnsOptsWithContByRptAnserId(vo.getId());
				}
				vo.setRptQueOptIds(rptQueOptIds);
			} else if(FillType.FILL_ATTACH.getKey().equals(vo.getQuestionType())) {
				List<RptResource> resources = rtpResourceService.getResByRptAnswerId(vo.getId());
				vo.setResources(resources);
			}
		}
		
		ReportAnswerOutWraperVO wrapper = new ReportAnswerOutWraperVO();
		wrapper.setAnswerList(ansList);
		wrapper.setRemark(getRemark(scoreItemId, targetId));
		return wrapper;
		
		
	}
	
	/**
	 *  获取选择题题目答案及答案具体内容
	 * @param rptAnswerId
	 * @return
	 */
	private List<RptAnswerOption> getAnsOptsWithContByRptAnserId(String rptAnswerId) {
		
		String sql = "select ra.id as id,ra.rptQueOptId as rptQueOptId, ra.rptAnswerId as rptAnswerId, ro.optName as optionCode, ro.optContent as optionContent from RptAnswerOption ra left join RptQuestionOption ro on ra.rptQueOptId = ro.id "
					+ " where ra.rptAnswerId = :rptAnswerId order by ro.optName asc ";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("rptAnswerId", rptAnswerId);
		
		List<RptAnswerOption> ansList = jpqlQuery().setJpql(sql)
				.setParams(params)
				.models(RptAnswerOption.class);
		if(ansList == null) {
			return new ArrayList<RptAnswerOption>();
		}
		return ansList;
	}

	@Override
	public void saveOrUpdateRptResource(RptResourceInVO vo) {
		
		String rptAnsId = vo.getRptAnswerId();
		if(StringUtils.isBlank(rptAnsId)) {
			ReportAnswer reportAnswer = this.buildReportAnswer(vo);
			reportAnswerImpl.saveOnly(reportAnswer);
			vo.setRptAnswerId(reportAnswer.getId());
			vo.setUpdateFlg(false);
		} 
		
		rtpResourceService.saveOrUpdateRtpResource(vo);
		System.out.println("~~~~~~~~~~~RptResourceInVO" + vo);
	}
	
	private ReportAnswer buildReportAnswer(RptResourceInVO vo) {
		ReportAnswer rptAnswer = new ReportAnswer();
		rptAnswer.setRptQuestionId(vo.getRptQuestionId());
		rptAnswer.setRptPubAnswerId(vo.getRptPubAnswerId());
		rptAnswer.setScoreItemId(vo.getScoreItemId());
		rptAnswer.setScore(0);
		return rptAnswer;
	}

	@Override
	public RptResourceWraperOutVO getRptResourceList(RptResourceCriteriaInVO criteria) {
		RptResourceWraperOutVO out = new RptResourceWraperOutVO();
		String getAnsSql = "select ra.id as rptAnswerId from ReportAnswer ra inner join RptPublishAnswer rp on ra.rptPubAnswerId = rp.id"
				+ " inner join PublishReport pr on rp.rptId = pr.id where pr.projectId = :projectId and rp.targetId = :targetId "
				+ " and ra.rptQuestionId = :rptQuestionId ";
		
		Map<String, Object> paramsTmp = new WeakHashMap<String, Object>();
		paramsTmp.put("projectId", criteria.getProjectId());
		paramsTmp.put("targetId", criteria.getTargetId());
		paramsTmp.put("rptQuestionId", criteria.getRptQueId());
		System.out.println("~~~~~~~~~~~~~" + paramsTmp + "!!!getRptPubAnsId" +  criteria.getRptPubAnsId());
		List<String> rptAnswerIds = jpqlQuery().setJpql(getAnsSql)
				.setParams(paramsTmp)
				.models();
		if(CollectionUtils.isNotEmpty(rptAnswerIds)) {
			out.setRptAnswerId(rptAnswerIds.get(0));
		}
		
		
		String getResListsql = "select rr.id as id, rr.orgName as orgName from RptResource rr "
				+ " where rr.targetId = :targetId and rr.projectId = :projectId "
				+ " order by rr.orgName asc , rr.createDate desc";
//				+ " and ra.rptPubAnswerId = :rptPubAnswerId and ra.rptQuestionId = :rptQuestionId order by rr.orgName asc , rr.createDate desc";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("projectId", criteria.getProjectId());
		params.put("targetId", criteria.getTargetId());
//		params.put("rptPubAnswerId", criteria.getRptPubAnsId());
//		params.put("rptQuestionId", criteria.getRptQueId());
		
		List<RptResourceOutVO> resList = jpqlQuery().setJpql(getResListsql)
				.setParams(params)
				.models(RptResourceOutVO.class);
		out.setResList(resList);
		String getResChkSql = "select re.resourceId from RptAnsRefResource re left join ReportAnswer ra on re.rptAnswerId = ra.id"
					+ " where ra.rptQuestionId = :rptQuestionId";
			
		params = new WeakHashMap<String, Object>();
		params.put("rptQuestionId", criteria.getRptQueId());
		List<String> chkResIds = jpqlQuery().setJpql(getResChkSql)
				.setParams(params)
				.models();
		Map<String, String> map = new HashMap<String, String>();
		for (String resId : chkResIds) {
			map.put(resId, "1");
		}
		out.setCheckResIds(map);
		return out;
		 
		
		
	}

	@Override
	public CalcuateScoreOutVO calcScoreByScoreItem(String targetId, String scoreItemId) {
	
		String sql = "select ra.scoreItemId as scoreItemId, rp.targetId as targetId, sum(ra.score) as totalScore from ReportAnswer ra"
				+ " inner join RptPublishAnswer rp on ra.rptPubAnswerId = rp.id "
				+ " where ra.scoreItemId = :scoreItemId and rp.targetId = :targetId group by ra.scoreItemId,rp.targetId";
		
		Map<String, Object> paramsTmp = new WeakHashMap<String, Object>();
		paramsTmp.put("scoreItemId", scoreItemId);
		paramsTmp.put("targetId", targetId);
		CalcuateScoreOutVO outVO = jpqlQuery().setJpql(sql)
				.setParams(paramsTmp)
				.model(CalcuateScoreOutVO.class);
		return outVO;
	}
	
}
