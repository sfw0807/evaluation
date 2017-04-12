package com.fykj.product.evaluation.api.scoreitem.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;
/**
 * ClassName: ScoreRoles  
 * (区间评分规则)
 * @author zhangtian  
 * @version
 */
@Entity
@Table(name = "t_interval_score")
public class IntervalScore extends AbstractEntity {

	private static final long serialVersionUID = 1L;
	@Column(name = "upper_limit")
	private BigDecimal upperLimit ;// 区间记分上限
	@Column(name = "low_limit")
	private BigDecimal lowLimit ;// 区间记分下限
	@Column(name = "score")
	private BigDecimal score ;// 得分
	@Column(name = "score_item_id")
	private String scoreItemId ;// 关联得分项的外键ID
	@Column(name = "form_id")
	private String formId ;// 关联表单，即选项的ID

	public BigDecimal getUpperLimit() {
		return upperLimit;
	}

	public void setUpperLimit(BigDecimal upperLimit) {
		this.upperLimit = upperLimit;
	}

	public BigDecimal getLowLimit() {
		return lowLimit;
	}

	public void setLowLimit(BigDecimal lowLimit) {
		this.lowLimit = lowLimit;
	}

	public BigDecimal getScore() {
		return score;
	}

	public void setScore(BigDecimal score) {
		this.score = score;
	}

	public String getScoreItemId() {
		return scoreItemId;
	}

	public void setScoreItemId(String scoreItemId) {
		this.scoreItemId = scoreItemId;
	}

	public String getFormId() {
		return formId;
	}

	public void setFormId(String formId) {
		this.formId = formId;
	}
}
