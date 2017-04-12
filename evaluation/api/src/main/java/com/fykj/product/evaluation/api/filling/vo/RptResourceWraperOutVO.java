package com.fykj.product.evaluation.api.filling.vo;

import java.util.List;
import java.util.Map;

public class RptResourceWraperOutVO {
	
	private List<RptResourceOutVO> resList;
	
	private String rptAnswerId;
	
	private String rptPubAnswerId;
	
	private Map<String, String> checkResIds;

	public List<RptResourceOutVO> getResList() {
		return resList;
	}

	public void setResList(List<RptResourceOutVO> resList) {
		this.resList = resList;
	}

	public String getRptAnswerId() {
		return rptAnswerId;
	}

	public void setRptAnswerId(String rptAnswerId) {
		this.rptAnswerId = rptAnswerId;
	}

	public String getRptPubAnswerId() {
		return rptPubAnswerId;
	}

	public void setRptPubAnswerId(String rptPubAnswerId) {
		this.rptPubAnswerId = rptPubAnswerId;
	}

	public Map<String, String> getCheckResIds() {
		return checkResIds;
	}

	public void setCheckResIds(Map<String, String> checkResIds) {
		this.checkResIds = checkResIds;
	}
	
}
