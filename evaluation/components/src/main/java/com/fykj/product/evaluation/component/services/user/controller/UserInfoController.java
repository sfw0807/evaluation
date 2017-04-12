package com.fykj.product.evaluation.component.services.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.SessionUser;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.sample.sysrole.vo.SysRoleGetOutVO;

@Controller
@RequestMapping("/userinfo")
@ParamValidation4Controller
public class UserInfoController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@ResponseBody
	@RequestMapping("/online")
	public InvokeResult current() throws Exception {
		
		SessionUser sessionUser= ServerSessionHolder.getSessionUser();
		List<SysRoleGetOutVO> roles= (List<SysRoleGetOutVO>) ServerSessionHolder.get("roles");
		Map<String, Object> info=new HashMap<>();
		info.put("userId", sessionUser.getId());
		info.put("userName", sessionUser.getUserName());
		info.put("userNatureName", sessionUser.getNatureName());
		info.put("roles", roles);
		info.put("userTypes", ServerSessionHolder.get("userTypes"));
		return InvokeResult.success(info);
	}
	
	
	
}
