package com.fykj.product.evaluation.modular.filling.service.impl;

import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.api.filling.model.RptAnsRefResource;
import com.fykj.product.evaluation.api.filling.model.RptResource;
import com.fykj.product.evaluation.api.filling.service.RtpResourceServiceApi;
import com.fykj.product.evaluation.api.filling.vo.RptResourceInVO;
import com.fykj.product.evaluation.modular.filling.service.RtpResourceService;

@Service
public class RtpResourceServiceImpl extends ServiceSupport implements RtpResourceService,RtpResourceServiceApi {
	

	private SingleEntityManager<RptResource> rtpResourceServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(RptResource.class);
	
	private SingleEntityManager<RptAnsRefResource> rtpRefResourceServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(RptAnsRefResource.class);
	

	@Override
	public List<RptResource> getResByProjectIdAndUserId(String projectId, String userId) {
		
		List<RptResource> resList = rtpResourceServiceImpl.singleEntityQuery2()
				.conditionDefault()
				.equals("projectId", projectId)
				.equals("creatorId", userId)
				.ready()
				.order()
				.asc("orgName")
				.ready()
				.models();
		
		return resList;
	}

	@Override
	public List<RptResource> getResByRptAnswerId(String rptAnswerId) {

		String sql = "select pre.id as id, pre.orgName as orgName, pre.filePath as filePath from ReportAnswer ra left join RptAnsRefResource pr on ra.id = pr.rptAnswerId "
				+ " left join RptResource pre on pr.resourceId = pre.id where ra.id = :rptAnswerId order by pre.orgName asc";
		Map<String, Object> params = new WeakHashMap<String, Object>() ;
		params.put("rptAnswerId", rptAnswerId);
		List<RptResource> resList = jpqlQuery().setJpql(sql).setParams(params).models(RptResource.class);
		return resList;
	}

	@Override
	public void saveOrUpdateRtpResource(RptResourceInVO vo) {
		
		this.deleteRptResourceByRptAnsID(vo.getRptAnswerId());
		
		if(CollectionUtils.isNotEmpty(vo.getUploadFileList())) {
			this.saveRtpAnsRes(vo);
		}
		
		if(CollectionUtils.isNotEmpty(vo.getRefResourceIds())) {
			this.saveRptAnsAndRef(vo);
		}
		
	}
	
	/**
	 * 将参考文件关系保存
	 * @param vo
	 */
	private void saveRptAnsAndRef(RptResourceInVO vo) {
		for(String resourceId : vo.getRefResourceIds()) {
			if(StringUtils.isNotBlank(resourceId)){
				this.saveRptAnsRefResource(buildRptAnsRefResource(resourceId, vo));
			}
		}
	}
	
	/**
	 * 将上传的附件保存到附件表并保存关系
	 * @param vo
	 */
	private void saveRtpAnsRes(RptResourceInVO vo) {
		
		for(RptResource res : vo.getUploadFileList()) {
			rtpResourceServiceImpl.saveOnly(res);
			this.saveRptAnsRefResource(buildRptAnsRefResource(res.getId(), vo));
		}
		
	}
	
	private RptAnsRefResource buildRptAnsRefResource(String resourceId, RptResourceInVO vo) {
		RptAnsRefResource rptAnsRefResource = Copy.simpleCopy(vo, RptAnsRefResource.class);
		rptAnsRefResource.setResourceId(resourceId);
		return rptAnsRefResource;
	}
	
	private void saveRptAnsRefResource(RptAnsRefResource rptAnsRefResource) {
		rtpRefResourceServiceImpl.saveOnly(rptAnsRefResource);
	}
	

	private void deleteRptResourceByRptAnsID(String rptAnsId) {
		String sql = "delete from RptAnsRefResource r where r.rptAnswerId = :rptAnswerId";
		Map<String, Object> params = new WeakHashMap<String, Object>() ;
		params.put("rptAnswerId", rptAnsId);
		jpqlQuery().setJpql(sql).setParams(params).setUpdate(true).model() ;
			
	}

}
