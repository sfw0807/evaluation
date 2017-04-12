package com.fykj.product.evaluation.component.services.sysrole.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.product.evaluation.component.services.sysrole.model.SysRoleType;
import com.fykj.product.evaluation.component.services.sysrole.service.SysRoleService;
import com.fykj.sample.sysrole.model.SysRole;

@Service("_SysRoleServiceImpl")
public class SysRoleServiceImpl extends ServiceSupport implements SysRoleService {
	
	@Override
	public List<SysRole> getSysRoleByType(String sysRoleType) {
		Map<String, Object> params=new HashMap<>();
		params.put("roleType", sysRoleType);
		String jpql=
				"selct r.id  as id , "
				+ " r.name as  name , "
				+ " r.description as description"
				+" from t_sys_role_type a" 
				+" inner join t_sys_role r on a.sys_role_id = r.id"
				+" where a.role_type  = :roleType "
				+ " and a.is_available=1 ";
		
		List<SysRole> roles = nativeQuery()
										.setSql(jpql)
										.setParams(params)
										.models(SysRole.class);
		return roles;
	}
	
	@Override
	public List<SysRoleType> getSysRoleTypeByUserId(String userId){
		Map<String, Object> params=new HashMap<>();
		params.put("userId", userId);
		String jpql=
				" select a.sys_role_id as sysRoleId , "
				+ " a.role_type  as  roleType  "
				+" from t_sys_role_type a" 
				+" inner join t_sys_role r on a.sys_role_id = r.id"
				+" inner join t_sys_user_role ur on ur.role_id = r.id"
				+" inner join t_sys_user u on u.id = ur.user_id"
				+" where u.id  = :userId"
				+ " and a.is_available=1"
				+ " and r.is_available=1 "
				+ " and ur.is_available=1 "
				+ " and u.is_available=1";

		List<SysRoleType> roleTypes = nativeQuery()
										.setSql(jpql)
										.setParams(params)
										.models(SysRoleType.class); 
		return roleTypes ;
	}
}
