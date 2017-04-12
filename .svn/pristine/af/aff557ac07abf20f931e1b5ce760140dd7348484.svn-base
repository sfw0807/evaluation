/**
 * 
 */
package com.fykj.sample.login.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
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

	@RequestMapping("")
	public String index() {
		if(ServerSessionHolder.isLogin()){
			return "redirect:/index";
		}
		return "login";
	}

	@RequestMapping("/userLogin")
	@ResponseBody
	public InvokeResult userLogin(LoginInVO inVO, HttpServletRequest request) {
		try {
			SysUser sysUser = Copy.simpleCopy(inVO, SysUser.class);
//			String captcha = inVO.getCaptcha();
//			
//			if(!StringUtils.equals(request.getSession().getAttribute("validate_captcha_code"), captcha)){
//				throw new BusinessException("验证码错误!");
//			}
			
			loginService.userLogin(sysUser);
			return InvokeResult.success(ServerSessionHolder.getSessionUser());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@RequestMapping("logout")
	public String logout() {
		ServerSessionHolder.removeServerSession();
		return "login";
	}
}
