package com.fykj.sample.login.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fykj.platform.kernel._c.ServerSessionHolder;

@Controller
@RequestMapping("/index")
public class IndexController {
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public String index(Model model) {
		model.addAttribute("user", ServerSessionHolder.getSessionUser());
		return "index";
	}

}
