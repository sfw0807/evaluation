package com.fykj.product.evaluation.modular.evaluating.scoreitem.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.product.evaluation.api.scoreitem.service.UserRoleServiceApi;
import com.fykj.product.evaluation.api.scoreitem.vo.SysRoleCriteriaInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.SysUserRoleOutVO;
import com.fykj.product.evaluation.api.scoreitem.vo.UserRoleOutVO;

@Service
public class UserRoleServiceImpl extends ServiceSupport implements UserRoleServiceApi {

	@Override
	public List<UserRoleOutVO> getUserRolesByUserId(String userId) {
		Map<String, Object> params = new HashMap<String, Object>();
		String sql = "select sr.id as roleId, sr.name as roleName, srt.roleType as roleType "
				+ " from SysUserRole sur "
				+ " join SysRole sr on sur.roleId = sr.id and sur.userId = :userId "
				+ " and sr.isAvailable = 1 and sur.isAvailable = 1 "
				+ " join SysRoleType srt on sr.id = srt.sysRoleId " ;
		
		params.put("userId", userId) ;
		
		List<UserRoleOutVO> userRoleOutVOs = jpqlQuery()
												.setJpql(sql)
												.setParams(params)
												.models(UserRoleOutVO.class) ;

		return userRoleOutVOs;
	}

	@Override
	public List<SysUserRoleOutVO> getSysUserRole(SysRoleCriteriaInVO criteriaInVO) {
		Map<String, Object> params = new HashMap<String, Object>() ;
			
		String sql = "select sr.id as id, sr.name as name "
				+ " from SysRole sr "
				+ " join SysRoleType srt on sr.id = srt.sysRoleId "
				+ " and srt.roleType = :roleType and sr.isAvailable =:isAvailable " ;
		
		params.put("roleType", criteriaInVO.getRoleType()) ;
		params.put("isAvailable", 1) ;
		List<SysUserRoleOutVO> sysUserRoleOutVOs = jpqlQuery()
													.setJpql(sql)
													.setParams(params)
													.models(SysUserRoleOutVO.class) ;
		return sysUserRoleOutVOs;
	}
}
