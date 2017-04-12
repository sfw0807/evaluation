package com.fykj.product.evaluation.api.filling.service;

import com.fykj.product.evaluation.api.filling.vo.PublishReportInVO;

public interface ReportServiceApi {

	
	/**
	 * @param projectId
	 * @param rptType
	 * @return
	 * 
	 * 判断是否存在该项目
	 */
	boolean isExistsProjectReport(String projectId, int rptType);
	
	String savePublishReport(PublishReportInVO vo);
	
	String getPubReportByProjectAndType(String projectId, int rptType);
}
