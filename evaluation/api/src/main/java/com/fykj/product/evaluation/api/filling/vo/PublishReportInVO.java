package com.fykj.product.evaluation.api.filling.vo;

public class PublishReportInVO {

	private int rptType; //类型 0填报  1问卷
	
	private String projectId;
	
	private String rptComment;
	
	private String rptPath;
	
	private String remark;
	
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
