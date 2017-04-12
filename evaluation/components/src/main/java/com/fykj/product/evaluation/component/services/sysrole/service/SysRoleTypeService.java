package com.fykj.product.evaluation.component.services.sysrole.service;

import com.fykj.product.evaluation.component.services.sysrole.model.SysRoleType;

/**
 * ClassName: SysRoleTypeService  
 * (角色类型管理)
 * @author zhangtian  
 * @version
 */
public interface SysRoleTypeService {

	// 保存角色类型
	public void saveSysRoleType(SysRoleType sysRoleType);
	
	// 更新角色类型
	public void updateSysRoleType(SysRoleType sysRoleType);
	
	// 根据ID查询角色类型
	public SysRoleType getSysRoleTypeById(String id) ;
	
	// 查询角色的绑定类型
	public SysRoleType getSysRoleTypeByRoleId(String roleId) ;
	
	// 删除角色绑定的类型
	public void deleteSysRoleTypeByRoleId(String roleId) ;
}
