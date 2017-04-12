package com.fykj.product.evaluation.component.services.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.SessionUser;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;

/**
 * ClassName: CommonLoginController  
 * (获取基本信息)
 * @author zhangtian  
 * @version
 */
@Controller
@RequestMapping("/common/data/")
@ParamValidation4Controller
public class CommonDataController {
	
	/**
	 *  getLoginInfo:(获取登录用户信息). 
	 *  @return_type:SessionUser
	 *  @author zhangtian  
	 *  @return
	 *  @throws Exception
	 */
	@RequestMapping(value = "loginInfo")
	@ResponseBody
	public SessionUser getLoginInfo() throws Exception {
		return ServerSessionHolder.getSessionUser() ;
	}
}
