package com.fykj.product.evaluation.api.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

@Entity
@Table(name = "t_rpt_answer_ref_resource")
public class RptAnsRefResource extends AbstractEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1922056837555762999L;

	@Column(name = "resource_id",length = 40)
	private String resourceId;
	
	@Column(name = "rpt_answer_id",length = 40)
	private String rptAnswerId;
	
	@Column(name = "score_item_id",length = 40)
	private String scoreItemId;
	
	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	public String getRptAnswerId() {
		return rptAnswerId;
	}

	public void setRptAnswerId(String rptAnswerId) {
		this.rptAnswerId = rptAnswerId;
	}

	public String getScoreItemId() {
		return scoreItemId;
	}

	public void setScoreItemId(String scoreItemId) {
		this.scoreItemId = scoreItemId;
	}

}
