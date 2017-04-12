package com.fykj.product.evaluation.api.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;
import com.fykj.product.evaluation.common.constant.RptCommonConstants;

/**
 * @author Erik_Yim
 *
 *答卷
 */
@Entity
@Table(name = "t_rpt_pub_answer")
public class RptPublishAnswer extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1529337132975989983L;
	
	@Column(name = "rpt_id", length=40, nullable=false)
	private String rptId; //关联填报ID

	@Column(name = "remark", length=1000)
	private String remark;
	
	@Column(name = "score")
	private float score;
	
	@Column(name = "target_id", length = 40)
	private String targetId;

	@Column(name = "submit_flag", length = 6)
	private String submitFlag = RptCommonConstants.UN_COMMIT_FLAG; //提交答卷标志 0未完成  1完成

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

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getSubmitFlag() {
		return submitFlag;
	}

	public void setSubmitFlag(String submitFlag) {
		this.submitFlag = submitFlag;
	}
}
