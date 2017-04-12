/**
 * 
 */
package com.fykj.sample.login.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.SessionUser;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.product.evaluation.component.services.sysrole.model.SysRoleType;
import com.fykj.product.evaluation.component.services.sysrole.service.SysRoleService;
import com.fykj.sample.login.service.LoginService;
import com.fykj.sample.login.vo.LoginInVO;
import com.fykj.sample.sysuser.model.SysUser;

/**
 * @author zhengzw
 *
 */
@Controller
@RequestMapping("/login")
@ParamValidation4Controller
public class LoginController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private LoginService loginService;
	
	@Autowired(required=false)
	private SysRoleService sysRoleService;

	@RequestMapping("/userLogin")
	@ResponseBody
	public InvokeResult userLogin(LoginInVO inVO, HttpServletRequest request) throws Exception {

		SysUser sysUser = Copy.simpleCopy(inVO, SysUser.class);
//		String captcha = inVO.getCaptcha();
//		
//		if(!StringUtils.equals(request.getSession().getAttribute("validate_captcha_code"), captcha)){
//			throw new BusinessException("验证码错误!");
//		}
		
		loginService.userLogin(sysUser);
		SessionUser sessionUser=ServerSessionHolder.getSessionUser();
		///TODO only for test
//		if("admin".equals(sessionUser.getUserName())){
//			ServerSessionHolder.put("userTypes", new String[]{"0"});
//		}else{
//			ServerSessionHolder.put("userTypes", new String[]{"1","2","3"});
//		}
		
		List<String> types=new ArrayList<>();
		List<SysRoleType> roleTypes=sysRoleService.getSysRoleTypeByUserId(sessionUser.getId());
		for(SysRoleType sysRoleType:roleTypes){
			types.add(sysRoleType.getRoleType());
		}
		ServerSessionHolder.put("userTypes", types.toArray(new String[]{}));
		Map<String, Object> maps=new HashMap<>();
		maps.put("userInfo", sessionUser);
		maps.put("userTypes", ServerSessionHolder.get("userTypes"));
		
		return InvokeResult.success(maps);
	
	}

	@RequestMapping("loginout")
	@ResponseBody
	public InvokeResult loginout() throws Exception{
		ServerSessionHolder.removeServerSession();
		return InvokeResult.success(true);
	}
}
