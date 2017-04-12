package com.fykj.product.evaluation.api.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

@Entity
@Table(name = "t_rpt_remark")
public class RptRemark extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5256422758639612758L;

	@Column(name = "rpt_pub_answer_id",length=40)
	private String rptPubAnswerId;
	
	@Column(name = "score_item_id",length=40)
	private String scoreItemId;
	
	@Column(name = "remark",length=1000)
	private String remark;

	public String getRptPubAnswerId() {
		return rptPubAnswerId;
	}

	public void setRptPubAnswerId(String rptPubAnswerId) {
		this.rptPubAnswerId = rptPubAnswerId;
	}

	public String getScoreItemId() {
		return scoreItemId;
	}

	public void setScoreItemId(String scoreItemId) {
		this.scoreItemId = scoreItemId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
