package com.fykj.sample.sysparam.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.JPageUtil;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.sample.cache.SysParamCacheHelper;
import com.fykj.sample.sysparam.model.SysParam;
import com.fykj.sample.sysparam.service.SysParamService;
import com.fykj.sample.sysparam.vo.SysParamAddInVO;
import com.fykj.sample.sysparam.vo.SysParamCriteriaInVO;
import com.fykj.sample.sysparam.vo.SysParamDetailOutVO;
import com.fykj.sample.sysparam.vo.SysParamEditInVO;
import com.fykj.sample.sysparam.vo.SysParamRecordOutVO;

/**
 * @author JIAZJ
 */
@Controller
@RequestMapping("/sysparam")
@ParamValidation4Controller
public class SysParamController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysParamService sysParamService;

	/**
	 * 系统参数添加页面
	 */
	@RequestMapping(value = "/toSysParamAdd")
	public String toSysParamAdd() {
		return "sysparam/sysparam-add";
	}

	/**
	 * 系统参数列表页面
	 */
	@RequestMapping(value = "/toSysParamList")
	public String toSysParamList() {
		//fff
		return "sysparam/sysparam-list";
	}

	/**
	 * 系统参数编辑页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toSysParamEdit")
	public String toSysParamEdit(String id, Model model) {
		SysParam sysParam = sysParamService.getSysParamById(id);
		model.addAttribute("sysParam", sysParam);
		return "sysparam/sysparam-edit";
	}

	/**
	 * 系统参数详情页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toSysParamView")
	public String toSysParamView(String id, Model model) {
		SysParam sysParam = sysParamService.getSysParamById(id);
		model.addAttribute("sysParam", sysParam);
		return "sysparam/sysparam-view";
	}

	@ResponseBody
	@RequestMapping("/saveSysParam")
	public InvokeResult saveSysParam(SysParamAddInVO sysParamAddInVO) throws Exception {
		try {
			SysParam sysParam = Copy.simpleCopy(sysParamAddInVO, SysParam.class);
			sysParamService.saveSysParam(sysParam);
			return InvokeResult.success(sysParam.getId());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@ResponseBody
	@RequestMapping("/updateSysParam")
	public InvokeResult updateSysParam(SysParamEditInVO sysParamEditInVO) throws Exception {
		try {
			SysParam sysParam = Copy.simpleCopy(sysParamEditInVO, SysParam.class);
			sysParamService.updateSysParam(sysParam);
			return InvokeResult.success(sysParam.getId());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@ResponseBody
	@RequestMapping("/getSysParamById")
	public InvokeResult getSysParamById(String id) throws Exception {
		try {
			SysParam sysParam = sysParamService.getSysParamById(id);
			SysParamDetailOutVO sysParamDetailOutVO = null;
			if (sysParam != null) {
				sysParamDetailOutVO = Copy.simpleCopy(sysParam, SysParamDetailOutVO.class);
			}
			return InvokeResult.success(sysParamDetailOutVO);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@ResponseBody
	@RequestMapping("/deleteSysParamById")
	public InvokeResult deleteSysParamById(String ids) throws Exception {
		if (StringUtils.isBlank(ids)) {
			return InvokeResult.failure("未获取系统参数信息");
		}
		try {
			String[] arr = ids.split(",");
			sysParamService.deleteSysParams(arr);
			return InvokeResult.success(true);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	@ResponseBody
	@RequestMapping("/getSysParamsByPage")
	public InvokeResult getSysParamsByPage(SysParamCriteriaInVO carCriteriaInVO,
			SimplePageRequestVO simplePageRequestVO) throws Exception {
		try {
			JPage<SysParam> page = sysParamService.getSysParams(carCriteriaInVO,
					new SimplePageRequest(simplePageRequestVO.getPage(), simplePageRequestVO.getSize()));
			List<SysParam> content = page.getContent();
			List<SysParamRecordOutVO> outContent = new ArrayList<SysParamRecordOutVO>();
			for (SysParam sysParam : content) {
				SysParamRecordOutVO sysParamRecordOutVO = Copy.simpleCopy(sysParam, SysParamRecordOutVO.class);
				outContent.add(sysParamRecordOutVO);
			}
			JPageUtil.replaceConent(page, outContent);
			return InvokeResult.success(page);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 重新加载缓存
	 * 
	 * @return
	 */
	@RequestMapping("/loadCache")
	@ResponseBody
	public InvokeResult loadCache() {
		try {
			SysParamCacheHelper.load();
			return InvokeResult.success(true);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

}
