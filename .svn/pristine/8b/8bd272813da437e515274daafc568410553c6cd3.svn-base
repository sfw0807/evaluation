package com.fykj.product.evaluation.modular.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;


/**
 * @author Erik_Yim
 *
 * 填报人
 */
@Entity
@Table(name = "t_rpt_pub_people")
@Deprecated
public class RptPubPeople extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7902424541646008651L;

	@Column(name = "user_id",length=40)
	private String userId; //用户id
	
	@Column(name = "rpt_project_id",length=40)
	private String rptProjectId;
	
	@Column(name = "complete_status")
	private int completeStatus = 0;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRptProjectId() {
		return rptProjectId;
	}

	public void setRptProjectId(String rptProjectId) {
		this.rptProjectId = rptProjectId;
	}

	public int getCompleteStatus() {
		return completeStatus;
	}

	public void setCompleteStatus(int completeStatus) {
		this.completeStatus = completeStatus;
	}

}
