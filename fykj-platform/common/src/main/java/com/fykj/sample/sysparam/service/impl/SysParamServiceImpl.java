package com.fykj.sample.sysparam.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.sample.sysparam.model.SysParam;
import com.fykj.sample.sysparam.service.SysParamService;
import com.fykj.sample.sysparam.vo.SysParamCriteriaInVO;

@Service
public class SysParamServiceImpl extends ServiceSupport implements SysParamService {

	private SingleEntityManager<SysParam> internalSysParamServiceImpl = SingleEntityManagerGetter.get()
			.getInstance(SysParam.class);

	@Override
	public void saveSysParam(SysParam sysParam) {
		String code = sysParam.getCode();
		if (exists(code)) {
			throw new BusinessException("系统参数 code [" + code + "] 已存在");
		}
		internalSysParamServiceImpl.saveOnly(sysParam);
	}

	@Override
	public boolean exists(String code) {
		SysParam param = internalSysParamServiceImpl.singleEntityQuery2().conditionDefault().equals("code", code)
				.ready().model();
		return param != null;
	}

	@Override
	public void updateSysParam(SysParam sysParam) {
		SysParam dbSysParam = getSysParamById(sysParam.getId());
		// dbSysParam.setCode(sysParam.getCode());
		dbSysParam.setValue(sysParam.getValue());
		dbSysParam.setDesc(sysParam.getDesc());

		internalSysParamServiceImpl.updateOnly(dbSysParam);
	}

	@Override
	public void deleteSysParam(SysParam sysParam) {
		internalSysParamServiceImpl.delete(sysParam);
	}

	@Override
	public void deleteSysParamById(String id) {
		internalSysParamServiceImpl.delete(id);
	}

	@Override
	public SysParam getSysParamById(String id) {
		return internalSysParamServiceImpl.getById(id);
	}

	@Override
	public JPage<SysParam> getSysParams(SysParamCriteriaInVO sysParamCriteriaInVO,
			SimplePageRequest simplePageRequest) {
		return internalSysParamServiceImpl.singleEntityQuery2().conditionDefault()
				.likes("code", sysParamCriteriaInVO.getCode()).likes("value", sysParamCriteriaInVO.getValue())
				.likes("desc", sysParamCriteriaInVO.getDesc()).ready().modelPage(simplePageRequest);
	}

	@Override
	public void deleteSysParams(String[] ids) {
		for (String id : ids) {
			deleteSysParamById(id);
		}
	}
	
	@Override
	public List<SysParam> loadSysParam(){
		return internalSysParamServiceImpl.singleEntityQuery2().conditionDefault().ready().models();
	}

}
