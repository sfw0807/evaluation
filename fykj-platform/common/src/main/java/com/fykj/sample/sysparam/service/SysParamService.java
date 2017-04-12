package com.fykj.sample.sysparam.service;

import java.util.List;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.sample.sysparam.model.SysParam;
import com.fykj.sample.sysparam.vo.SysParamCriteriaInVO;

public interface SysParamService {

	void saveSysParam(SysParam sysParam);
	
	void updateSysParam(SysParam sysParam);
	
	void deleteSysParam(SysParam sysParam);
	
	void deleteSysParamById(String id);
	
	void deleteSysParams(String [] ids);
	
	SysParam getSysParamById(String id);
	
	JPage<SysParam> getSysParams(SysParamCriteriaInVO sysParamCriteriaInVO, SimplePageRequest simplePageRequest);
	
	boolean exists(String code);
	
	List<SysParam> loadSysParam();
}
