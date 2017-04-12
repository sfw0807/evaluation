package com.fykj.product.evaluation.api.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fykj.platform.kernel._c.model.AbstractEntity;

@Entity
@Table(name = "t_rpt_answer_option")
public class RptAnswerOption extends AbstractEntity {

	/**
	 * 
	 */
	
	private static final long serialVersionUID = 3119097000446480635L;
	
	@Column(name = "rpt_que_opt_id", length=40)
	private String rptQueOptId; //关联选项ID
	
	@Column(name = "rpt_answer_id", length=40)
	private String rptAnswerId; //关联答题ID
	
	@Column(name = "remark", length=1000)
	private String remark; //关联答题ID
	
	
	@Transient
	private String optionContent;
	
	@Transient
	private String optionCode;

	public String getRptQueOptId() {
		return rptQueOptId;
	}

	public void setRptQueOptId(String rptQueOptId) {
		this.rptQueOptId = rptQueOptId;
	}

	public String getRptAnswerId() {
		return rptAnswerId;
	}

	public void setRptAnswerId(String rptAnswerId) {
		this.rptAnswerId = rptAnswerId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getOptionContent() {
		return optionContent;
	}

	public void setOptionContent(String optionContent) {
		this.optionContent = optionContent;
	}

	public String getOptionCode() {
		return optionCode;
	}

	public void setOptionCode(String optionCode) {
		this.optionCode = optionCode;
	}
	
}
