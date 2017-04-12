package com.fykj.product.evaluation.modular.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;


/**
 * @author Erik_Yim
 *
 *填报/问卷实体
 */
@Entity
@Table(name = "t_rpt_pub_report")
public class PublishReport extends AbstractEntity {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4107151963110084572L;

	@Column(name = "rpt_type")
	private int rptType = 0; //类型 0填报  1问卷
	
	@Column(name = "project_id",length=40)
	private String projectId;
	
	@Column(name = "rpt_comment",length=40)
	private String rptComment;
	
	@Column(name = "rpt_path", length=400)
	private String rptPath;
	
	@Column(name = "remark", length=1000)
	private String remark;
	
	@Column(name = "rpt_source")
	private int rptSource;

	public int getRptType() {
		return rptType;
	}

	public void setRptType(int rptType) {
		this.rptType = rptType;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}


	public String getRptComment() {
		return rptComment;
	}

	public void setRptComment(String rptComment) {
		this.rptComment = rptComment;
	}

	public String getRptPath() {
		return rptPath;
	}

	public void setRptPath(String rptPath) {
		this.rptPath = rptPath;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getRptSource() {
		return rptSource;
	}

	public void setRptSource(int rptSource) {
		this.rptSource = rptSource;
	}

}
