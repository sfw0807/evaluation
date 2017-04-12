package com.fykj.product.evaluation.component.services.sysrole.service;

import java.util.List;

import com.fykj.product.evaluation.component.services.sysrole.model.SysRoleType;
import com.fykj.sample.sysrole.model.SysRole;
/**
 * ClassName: SysRoleInterface  
 * (系统角色调用接口)
 * @author zhangtian  
 * @version
 */
public interface SysRoleService {
	/**
	 *  getSysRoleByType:(根据角色类型选择下拉角色). 
	 *  角色类型有：
	 *  	管理员角色
	 *  	填报角色
	 *  	自评角色
	 *  	专家专家
	 *  	终评角色
	 *  @return_type:List<SysRole>
	 *  @author zhangtian  
	 *  @param sysRoleType
	 *  @return
	 */
	public List<SysRole> getSysRoleByType(String sysRoleType) ;
	
	/**
	 * getRoleTypeByUserId(根据用户ID获得该用户的角色类型)
	 * @param userId
	 * @return RoleType
	 */
	public List<SysRoleType> getSysRoleTypeByUserId(String userId);
}
