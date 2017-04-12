package com.fykj.product.evaluation.modular.filling.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.product.evaluation.api.filling.model.RptRemark;
import com.fykj.product.evaluation.modular.filling.service.RptPubAnswerService;
import com.fykj.product.evaluation.modular.filling.service.RptRemarkService;

@Service
public class RptRemarkServiceImpl extends ServiceSupport implements RptRemarkService {
	
	@Autowired
	RptPubAnswerService rptPubAnswerService;

	private SingleEntityManager<RptRemark> rptRemarkServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(RptRemark.class);
	
	@Override
	public String saveRptRemark(RptRemark remark) {
		rptRemarkServiceImpl.saveOnly(remark);
		return remark.getId();
	}

	@Override
	public List<RptRemark> getRemarkByScoreItemId(String scoreItemId) {
		return rptRemarkServiceImpl
		.singleEntityQuery2()
		.conditionDefault()
		.equals("scoreItemId", scoreItemId)
		.ready()
		.order()
		.asc("createDate")
		.ready()
		.models();
	}

	@Override
	public RptRemark getRemarkByScoreItemId(String scoreItemId, String targetId) {
		
		Map<String, Object> params = new HashMap<String, Object>() ;
		params.put("scoreItemId", scoreItemId);
		params.put("targetId", targetId);
		String sql = "select rr.id as id ,rr.rptPubAnswerId as rptPubAnswerId, rr.remark as remark  "
				+ " from RptRemark rr inner join RptPublishAnswer ra on rr.rptPubAnswerId = ra.id"
				+ " where rr.isAvailable = 1 and rr.scoreItemId = :scoreItemId and ra.targetId = :targetId " ;
		
		List<RptRemark> rptRemarks = jpqlQuery()
										.setJpql(sql)
										.setParams(params)
										.models(RptRemark.class) ;
		
		if(CollectionUtils.isNotEmpty(rptRemarks)){
			return rptRemarks.get(0) ;
		}
		return new RptRemark();
	}

	@Override
	public RptRemark getRptRemarkByPK(String rptRemarkId) {
		return rptRemarkServiceImpl.getById(rptRemarkId, RptRemark.class);
	}

	@Override
	public void updateRptRemarkByPK(RptRemark remark) {
		rptRemarkServiceImpl.updateOnly(remark);
	}

	@Override
	public RptRemark getRemarkByProIdAndTargetId(String projectId, String targetId) {
		Map<String, Object> params = new HashMap<String, Object>() ;
		params.put("projectId", projectId);
		params.put("targetId", targetId);
		String sql = "select rr.id as id ,rr.rptPubAnswerId as rptPubAnswerId, rr.remark as remark  "
				+ " from RptRemark rr inner join RptPublishAnswer ra on rr.rptPubAnswerId = ra.id"
				+ " inner join PublishReport pr on pr.id = ra.rptId "
				+ " where rr.isAvailable = 1 and ra.targetId = :targetId pr.projectId = :projectId"
				+ " order by rr.createDate asc" ;

		List<RptRemark> rptRemarks = jpqlQuery()
				.setJpql(sql)
				.setParams(params)
				.models(RptRemark.class) ;

		if(CollectionUtils.isNotEmpty(rptRemarks)){
			return rptRemarks.get(0) ;
		}
		return new RptRemark();


	}

}
