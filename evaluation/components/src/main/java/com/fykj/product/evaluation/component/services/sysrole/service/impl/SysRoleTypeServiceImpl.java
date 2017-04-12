package com.fykj.product.evaluation.component.services.sysrole.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.platform.kernel._c.service.SingleEntityManager;
import com.fykj.platform.kernel._c.service.SingleEntityManagerGetter;
import com.fykj.product.evaluation.component.services.sysrole.model.SysRoleType;
import com.fykj.product.evaluation.component.services.sysrole.service.SysRoleTypeService;

@Service
public class SysRoleTypeServiceImpl extends ServiceSupport implements SysRoleTypeService {

	private SingleEntityManager<SysRoleType> internalSysRoleTypeServiceImpl = SingleEntityManagerGetter
																				.get()
																				.getInstance(SysRoleType.class) ;
	
	@Override
	public void saveSysRoleType(SysRoleType sysRoleType) {
		internalSysRoleTypeServiceImpl.saveOnly(sysRoleType);
	}

	@Override
	public void updateSysRoleType(SysRoleType sr) {
		SysRoleType sysRoleType = getSysRoleTypeById(sr.getId()) ;
		sysRoleType.setSysRoleId(sr.getSysRoleId());
		sysRoleType.setRoleType(sr.getRoleType());
		internalSysRoleTypeServiceImpl.updateOnly(sysRoleType);
	}

	@Override
	public SysRoleType getSysRoleTypeById(String id) {
		return internalSysRoleTypeServiceImpl.getById(id);
	}

	@Override
	public SysRoleType getSysRoleTypeByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>() ;
		String sql = "select s.id as id, s.roleType as roleType ,s.sysRoleId as sysRoleId "
				+ " from SysRoleType s "
				+ " where s.sysRoleId = :sysRoleId and s.isAvailable = 1 " ;
		params.put("sysRoleId", roleId) ;
		List<SysRoleType> result = jpqlQuery().setJpql(sql).setParams(params).models(SysRoleType.class) ;
		if(CollectionUtils.isNotEmpty(result)){
			return result.get(0) ;
		}
		return null;
	}

	@Override
	public void deleteSysRoleTypeByRoleId(String roleId) {
		Map<String, Object> params = new HashMap<String, Object>() ;
		String sql = "delete from SysRoleType s "
				+ " where s.sysRoleId = :sysRoleId " ;
		params.put("sysRoleId", roleId) ;
		jpqlQuery().setJpql(sql).setParams(params).setUpdate(true).model() ;
	}
}
