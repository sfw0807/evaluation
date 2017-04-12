package com.fykj.product.evaluation.api.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

/**
 * @author Erik_Yim
 *
 *填报明细
 */
@Entity
@Table(name = "t_rpt_detail")
public class ReportDetail extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4450153925436513605L;
	
	@Column(name = "rpt_id", length=40)
	private String rptId;
	
	@Column(name = "remark", length=1000)
	private String remark;
	
	@Column(name = "rpt_quesion_id", length=40, nullable=false)
	private String rptQuesionId; //关联问题ID

	public String getRptId() {
		return rptId;
	}

	public void setRptId(String rptId) {
		this.rptId = rptId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRptQuesionId() {
		return rptQuesionId;
	}

	public void setRptQuesionId(String rptQuesionId) {
		this.rptQuesionId = rptQuesionId;
	}
	
}
