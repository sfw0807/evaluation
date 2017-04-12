package com.fykj.product.evaluation.api.scoreitem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.product.evaluation.api.scoreitem.service.UserRoleServiceApi;
import com.fykj.product.evaluation.api.scoreitem.vo.SysRoleCriteriaInVO;
import com.fykj.product.evaluation.api.scoreitem.vo.SysUserRoleOutVO;

@Controller
@ParamValidation4Controller
@RequestMapping(value = "/userRole/")
public class UserRoleController {
	@Autowired
	private UserRoleServiceApi userRoleServiceApi ;
	
	@RequestMapping("getUserRoles")
	@ResponseBody
	public InvokeResult getUserRoles(SysRoleCriteriaInVO criteriaInVO){
		List<SysUserRoleOutVO> sysRoles = userRoleServiceApi.getSysUserRole(criteriaInVO) ;
		return InvokeResult.success(sysRoles) ;
	}
}
