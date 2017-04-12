package com.fykj.product.evaluation.component.services.sysrole.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.product.evaluation.component.services.sysrole.model.SysRoleType;
import com.fykj.product.evaluation.component.services.sysrole.service.SysRoleTypeService;

@Controller
@ParamValidation4Controller
@RequestMapping(value = "/sysRoleType/")
public class SysRoleTypeController {
	
	@Autowired
	private SysRoleTypeService sysRoleTypeService ;

	@RequestMapping(value = "mergeSysRoleType")
	@ResponseBody
	public InvokeResult mergeSysRoleType(SysRoleType sysRoleType) throws Exception {
		// 没有选择任何角色类型，则直接返回
		if(StringUtils.equals("-1", sysRoleType.getRoleType())){
			sysRoleTypeService.deleteSysRoleTypeByRoleId(sysRoleType.getSysRoleId());
			return InvokeResult.success(sysRoleType.getRoleType()) ;
		}
		
		if(StringUtils.isNotBlank(sysRoleType.getId())){// 判断ID已经存在，则更新
			sysRoleTypeService.updateSysRoleType(sysRoleType) ;
		}else {// ID不存在，则新增
			sysRoleTypeService.saveSysRoleType(sysRoleType) ;
		}
		return InvokeResult.success(sysRoleType.getId()) ;
	}
	
	@RequestMapping(value = "getSysRoleTypeByRoleId")
	@ResponseBody
	public InvokeResult getSysRoleTypeByRoleId(String roleId) throws Exception {
		SysRoleType sysRoleType = sysRoleTypeService.getSysRoleTypeByRoleId(roleId) ;
		return InvokeResult.success(sysRoleType) ;
	}
}
