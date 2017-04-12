package com.fykj.product.evaluation.modular.evaluating.scoreitem.service.impl;

import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fykj.product.evaluation.api.filling.model.RptPublishAnswer;
import com.fykj.product.evaluation.api.filling.service.RptAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.service.RptPubAnswerServiceApi;
import com.fykj.product.evaluation.api.filling.service.RptQuestionServiceApi;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerInWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportAnswerOutWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsOutVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQueAnsWraperVO;
import com.fykj.product.evaluation.api.filling.vo.ReportQuestionOutVO;
import com.fykj.product.evaluation.api.scoreitem.model.IntervalScore;
import com.fykj.product.evaluation.api.scoreitem.service.FillScoreItemServiceApi;
import com.fykj.product.evaluation.api.scoreitem.service.IntervalScoreServiceApi;
import com.fykj.product.evaluation.common.constant.FillType;

/**
 * ClassName: FillScoreItemServiceImpl  
 * (填报得分项)
 * @author zhangtian  
 * @version
 */
@Service
public class FillScoreItemServiceImpl implements FillScoreItemServiceApi {
	@Autowired
	private RptQuestionServiceApi rptQuestionServiceApi ;
	@Autowired
	private IntervalScoreServiceApi intervalScoreServiceApi ;
	@Autowired
	private RptAnswerServiceApi rptAnswerServiceApi;
	@Autowired
	private RptPubAnswerServiceApi rptPubAnswerServiceApi ;
	
	@Override
	public List<ReportQuestionOutVO> getFillScoreItemOptions(String scoreItemId, String queryType) {
		List<ReportQuestionOutVO> list = rptQuestionServiceApi.getRptQuestionByScoreItemId(scoreItemId, queryType) ;
		if(CollectionUtils.isNotEmpty(list)) {
			Closure intervalScoresClouse = new Closure() {
				@Override
				public void execute(Object obj) {
					if(obj.getClass().isAssignableFrom(ReportQuestionOutVO.class)) {
						ReportQuestionOutVO reportQuestionOutVO = (ReportQuestionOutVO)obj ;
						if(StringUtils.equals(FillType.FILL_ANSWER.getKey(), reportQuestionOutVO.getQuestionType())) {
							List<IntervalScore> intervalScores = intervalScoreServiceApi.getIntervalScoreByScoreFormId(reportQuestionOutVO.getId()) ;
							if(CollectionUtils.isNotEmpty(intervalScores)) {
								reportQuestionOutVO.setIntervalScores(intervalScores);
								reportQuestionOutVO.setHasIntervalScores(true);
							}else {
								reportQuestionOutVO.setHasIntervalScores(false);
							}
						}else {
							reportQuestionOutVO.setHasIntervalScores(false);
						}
					}
				}
			};
			CollectionUtils.forAllDo(list, intervalScoresClouse);
		}
		return list ;
	}
	
	@Override
	public ReportQueAnsWraperVO getFillScoreItemOptionsAll(String scoreItemId, String targetId, String projectId) {
		ReportQueAnsWraperVO reportQueAnsWraperVO = rptQuestionServiceApi.getRptQueAnsByScoreItemId(scoreItemId, targetId ,projectId) ;
		if(reportQueAnsWraperVO != null) {
			if(CollectionUtils.isNotEmpty(reportQueAnsWraperVO.getOutVO())) {
				Closure intervalScoresClouse = new Closure() {
					@Override
					public void execute(Object obj) {
						if(obj.getClass().isAssignableFrom(ReportQueAnsOutVO.class)) {
							ReportQueAnsOutVO reportQueAnsOutVO = (ReportQueAnsOutVO)obj ;
							if(StringUtils.equals(FillType.FILL_ANSWER.getKey(), reportQueAnsOutVO.getQuestionType())) {
								List<IntervalScore> intervalScores = intervalScoreServiceApi.getIntervalScoreByScoreFormId(reportQueAnsOutVO.getId()) ;
								if(CollectionUtils.isNotEmpty(intervalScores)) {
									reportQueAnsOutVO.setIntervalScores(intervalScores);
									reportQueAnsOutVO.setHasIntervalScores(true);
								}else {
									reportQueAnsOutVO.setHasIntervalScores(false);
								}
							}else {
								reportQueAnsOutVO.setHasIntervalScores(false);
							}
						}
					}
				};
				CollectionUtils.forAllDo(reportQueAnsWraperVO.getOutVO(), intervalScoresClouse);
			}
		}
		return reportQueAnsWraperVO ;
	}

	@Override
	public void saveScoreItemOptions(ReportAnswerInWraperVO wraper, String rptId, String targetId) {
		// 获取pubAnswerId答卷ID
		RptPublishAnswer rptPublishAnswer = new RptPublishAnswer() ;
		rptPublishAnswer.setRptId(rptId);
		rptPublishAnswer.setTargetId(targetId);
		final String rptPubAnswerId = rptPubAnswerServiceApi.savePubAnswer(rptPublishAnswer) ;
		wraper.setRptPubAnswerId(rptPubAnswerId);
		
		List<ReportAnswerInVO> answers = wraper.getAnswers() ;
		Closure answersClouse = new Closure() {
			@Override
			public void execute(Object obj) {
				if(obj.getClass().isAssignableFrom(ReportAnswerInVO.class)) {
					ReportAnswerInVO answerInVO = (ReportAnswerInVO)obj ;
					answerInVO.setRptPubAnswerId(rptPubAnswerId);
				}
			}
		};
		CollectionUtils.forAllDo(answers, answersClouse);
		// 保存答卷
		rptAnswerServiceApi.saveOrUpdateAnswers(wraper);
	}

	@Override
	public ReportAnswerOutWraperVO getAnswerByScoreId(String targetId, String scoreItemId, String projectId) {
		ReportAnswerOutWraperVO answerOutWraperVO = rptAnswerServiceApi.getAnswerByScoreId(scoreItemId, targetId, projectId) ;
		return answerOutWraperVO ;
	}
}
