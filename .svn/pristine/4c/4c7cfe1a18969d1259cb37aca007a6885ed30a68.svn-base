package com.fykj.product.evaluation.api.filling.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

@Entity
@Table(name = "t_rpt_answer_resource")
public class RptResource extends AbstractEntity  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8457805695148256794L;

	@Column(name = "file_path",length = 1000)
	private String filePath;
	
	@Column(name = "file_name",length = 1000)
	private String fileName;
	
	@Column(name = "org_name",length = 1000)
	private String orgName;
	
	@Column(name = "target_id",length = 40)
	private String targetId;
	
	@Column(name = "project_id",length = 40)
	private String projectId;


	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

}
