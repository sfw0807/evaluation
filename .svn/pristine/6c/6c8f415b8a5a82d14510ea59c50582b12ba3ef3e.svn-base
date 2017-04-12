package com.fykj.product.evaluation.api.scoreitem.service;
import java.util.List;
/**
 * ClassName: UserRoleServiceApi  
 * (用户角色判断API)
 * @author zhangtian  
 * @version
 */

import com.fykj.product.evaluation.api.scoreitem.vo.SysRoleCriteriaInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.SysUserRoleOutVO;
import com.fykj.product.evaluation.api.scoreitem.vo.UserRoleOutVO;
public interface UserRoleServiceApi {
	// 根据登录用户ID查询其所属的角色集合
	public List<UserRoleOutVO> getUserRolesByUserId(String userId) ;
	// 根据条件获取角色信息
	public List<SysUserRoleOutVO> getSysUserRole(SysRoleCriteriaInVO criteriaInVO) ;
}
