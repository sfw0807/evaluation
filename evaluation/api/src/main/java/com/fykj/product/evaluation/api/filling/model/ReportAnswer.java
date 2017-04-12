package com.fykj.product.evaluation.api.filling.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;


/**
 * @author Erik_Yim
 *
 *答题
 */
@Entity
@Table(name = "t_rpt_answer")
public class ReportAnswer extends AbstractEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6166757251279008119L;

	@Column(name = "rpt_question_id", length=40)
	private String rptQuestionId; //关联题目ID
	
	@Column(name = "rpt_pub_answer_id", length=40)
	private String rptPubAnswerId; //关联答卷ID
	
	@Column(name = "answer", length=1000)
	private String answer;
	
	@Column(name = "score")
	private float score;
	
	@Column(name = "score_item_id", length = 40)
	private String scoreItemId;
	
	@Column(name = "answer_num")
	private BigDecimal answerNum;
	
	
	public String getRptQuestionId() {
		return rptQuestionId;
	}

	public void setRptQuestionId(String rptQuestionId) {
		this.rptQuestionId = rptQuestionId;
	}

	public String getRptPubAnswerId() {
		return rptPubAnswerId;
	}

	public void setRptPubAnswerId(String rptPubAnswerId) {
		this.rptPubAnswerId = rptPubAnswerId;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getScoreItemId() {
		return scoreItemId;
	}

	public void setScoreItemId(String scoreItemId) {
		this.scoreItemId = scoreItemId;
	}

	public void setAnswerNum(BigDecimal answerNum) {
		this.answerNum = answerNum;
	}

}
