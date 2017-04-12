package com.fykj.product.evaluation.modular.filling.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fykj.platform.kernel._c.model.AbstractEntity;

/**
 * @author Erik_Yim
 *
 * 填报项目
 */
@Entity
@Table(name = "t_rpt_pub_project")
@Deprecated
public class RptPubProject extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4043444145671871755L;

	@Column(name = "project_name",length=500)
	private String projectName;
	
	@Column(name = "remark",length=1000)
	private String remark;
	
	@Column(name = "project_start_date")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date projectStartDate;
	
	@Column(name = "project_end_date")
	@Temporal(value=TemporalType.TIMESTAMP)
	private Date projectEndDate;
	
	@Column(name = "publish_status")
	private int publishStatus = 0;
	
	@Column(name = "build_status")
	private int buildStatus = 0;
	
	@Column(name = "prompt")
	private int prompt = 0; //通知方式

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getProjectStartDate() {
		return projectStartDate;
	}

	public void setProjectStartDate(Date projectStartDate) {
		this.projectStartDate = projectStartDate;
	}

	public Date getProjectEndDate() {
		return projectEndDate;
	}

	public void setProjectEndDate(Date projectEndDate) {
		this.projectEndDate = projectEndDate;
	}

	public int getPrompt() {
		return prompt;
	}

	public void setPrompt(int prompt) {
		this.prompt = prompt;
	}

	public int getPublishStatus() {
		return publishStatus;
	}

	public void setPublishStatus(int publishStatus) {
		this.publishStatus = publishStatus;
	}

	public int getBuildStatus() {
		return buildStatus;
	}

	public void setBuildStatus(int buildStatus) {
		this.buildStatus = buildStatus;
	}
	
}
