package com.fykj.product.evaluation.modular.evaluating.itemscore.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;


@Entity
@Table(name = "t_item_score")
public class ItemScore extends AbstractEntity {

	/**
	 * 被评对象
	 */
	@Column(name = "eval_targetid")
	private String evalTargetId ;
	
	/**
	 * 得分项
	 */
	@Column(name = "itemid")
	private String itemId ;
	
	@Column(name = "projectid")
	private String projectId;
	
	/**
	 * 自评分
	 */
	@Column(name = "self_score")
	private BigDecimal selfScore ;
	
	/**
	 * 填报打分
	 */
	@Column(name = "fill_score")
	private BigDecimal fillScore ;
	
	/**
	 * 问卷打分
	 */
	@Column(name = "survey_score")
	private BigDecimal surveyScore ;
	
	
	@Column(name = "expert_score")
	private BigDecimal expertScore ;
	
	/**
	 * 终评打分
	 */
	@Column(name = "end_score")
	private BigDecimal endScore ;   
	
	/**
	 * 最终得分
	 */
	@Column(name = "final_score")
	private BigDecimal finalScore ;
	

	public String getEvalTargetId() {
		return evalTargetId;
	}

	public void setEvalTargetId(String evalTargetId) {
		this.evalTargetId = evalTargetId;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public BigDecimal getSelfScore() {
		return selfScore;
	}

	public void setSelfScore(BigDecimal selfScore) {
		this.selfScore = selfScore;
	}

	public BigDecimal getFillScore() {
		return fillScore;
	}

	public void setFillScore(BigDecimal fillScore) {
		this.fillScore = fillScore;
	}

	public BigDecimal getSurveyScore() {
		return surveyScore;
	}

	public void setSurveyScore(BigDecimal surveyScore) {
		this.surveyScore = surveyScore;
	}

	public BigDecimal getExpertScore() {
		return expertScore;
	}

	public void setExpertScore(BigDecimal expertScore) {
		this.expertScore = expertScore;
	}

	public BigDecimal getEndScore() {
		return endScore;
	}

	public void setEndScore(BigDecimal endScore) {
		this.endScore = endScore;
	}

	public BigDecimal getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(BigDecimal finalScore) {
		this.finalScore = finalScore;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
