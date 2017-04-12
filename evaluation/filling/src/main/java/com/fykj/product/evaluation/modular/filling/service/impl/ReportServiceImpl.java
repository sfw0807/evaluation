package com.fykj.product.evaluation.modular.filling.service.impl;

import java.util.Map;
import java.util.WeakHashMap;

import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.api.filling.service.ReportServiceApi;
import com.fykj.product.evaluation.api.filling.vo.PublishReportInVO;
import com.fykj.product.evaluation.modular.filling.model.PublishReport;
import com.fykj.product.evaluation.modular.filling.service.ReportService;

@Service
public class ReportServiceImpl extends ServiceSupport implements ReportService,ReportServiceApi {


	private SingleEntityManager<PublishReport> reportServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(PublishReport.class);
	
	@Override
	public boolean isExistsProjectReport(String projectId, int rptType) {

		String sql = "SELECT count(r) FROM PublishReport r where r.projectId = :projectId and r.rptType = :rptType";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("projectId", projectId);
		params.put("rptType", rptType);
		
		int rptReportCount = jpqlQuery().setJpql(sql)
				.setParams(params)
				.model();
		
		return rptReportCount == 1 ? true : false;
	}

	@Override
	public String savePublishReport(PublishReportInVO vo) {
		
		PublishReport report = Copy.simpleCopy(vo, PublishReport.class);
		reportServiceImpl.saveOnly(report);
		return report.getId();
		
	}

	@Override
	public String getPubReportByProjectAndType(String projectId, int rptType) {

		String sql = "SELECT r.id FROM PublishReport r where r.projectId = :projectId and r.rptType = :rptType";
		Map<String, Object> params = new WeakHashMap<String, Object>();
		params.put("projectId", projectId);
		params.put("rptType", rptType);
		
		String id = jpqlQuery().setJpql(sql)
				.setParams(params)
				.model();
		
		return id;
	}
	
	
	
	

	
}
