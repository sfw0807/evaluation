package com.fykj.product.evaluation.api.filling.vo;

import java.util.List;

import com.fykj.product.evaluation.api.filling.model.RptResource;

public class RptResourceInVO {

	private String rptAnswerId;
	
	private String projectId;
	
	private String scoreItemId;
	
	private String targetId;
	
	private String projectFilePath;
	
	private String rptQuestionId;
	
	private String rptPubAnswerId;
	
	private List<String> refResourceIds; //参考的关联表
	
	private List<RptResource> uploadFileList; //本次上传文件列表
	
	private boolean updateFlg = true;

	public String getRptAnswerId() {
		return rptAnswerId;
	}

	public void setRptAnswerId(String rptAnswerId) {
		this.rptAnswerId = rptAnswerId;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public String getScoreItemId() {
		return scoreItemId;
	}

	public void setScoreItemId(String scoreItemId) {
		this.scoreItemId = scoreItemId;
	}

	public String getProjectFilePath() {
		return projectFilePath;
	}

	public void setProjectFilePath(String projectFilePath) {
		this.projectFilePath = projectFilePath;
	}

	public String getTargetId() {
		return targetId;
	}

	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}

	public List<String> getRefResourceIds() {
		return refResourceIds;
	}

	public void setRefResourceIds(List<String> refResourceIds) {
		this.refResourceIds = refResourceIds;
	}

	public List<RptResource> getUploadFileList() {
		return uploadFileList;
	}

	public void setUploadFileList(List<RptResource> uploadFileList) {
		this.uploadFileList = uploadFileList;
	}

	public String getRptQuestionId() {
		return rptQuestionId;
	}

	public void setRptQuestionId(String rptQuestionId) {
		this.rptQuestionId = rptQuestionId;
	}

	public String getRptPubAnswerId() {
		return rptPubAnswerId;
	}

	public void setRptPubAnswerId(String rptPubAnswerId) {
		this.rptPubAnswerId = rptPubAnswerId;
	}

	public boolean isUpdateFlg() {
		return updateFlg;
	}

	public void setUpdateFlg(boolean updateFlg) {
		this.updateFlg = updateFlg;
	}

	@Override
	public String toString() {
		return "RptResourceInVO [rptAnswerId=" + rptAnswerId + ", projectId=" + projectId + ", scoreItemId="
				+ scoreItemId + ", targetId=" + targetId + ", projectFilePath=" + projectFilePath + ", rptQuestionId="
				+ rptQuestionId + ", rptPubAnswerId=" + rptPubAnswerId + ", refResourceIds=" + refResourceIds
				+ ", uploadFileList=" + uploadFileList + ", updateFlg=" + updateFlg + "]";
	}
	
} 
