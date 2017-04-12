package com.fykj.platform.web.platsupport.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel._c.Version;
import com.fykj.platform.kernel._c.model.InvokeResult;

@Controller
@RequestMapping("/platformsupport")
public class PlatformController {
	
	@ResponseBody
	@RequestMapping("/getVersion")
	public InvokeResult getVersion(){
		return InvokeResult.success(Version.getVersion());
	}
	
}
