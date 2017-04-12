/**
 * 
 */
package com.fykj.sample.urlresources.controller;

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
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.util.Copy;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.sample.urlresources.model.URLResources;
import com.fykj.sample.urlresources.service.URLResourcesService;
import com.fykj.sample.urlresources.vo.URLResourcesEidtInVO;
import com.fykj.sample.urlresources.vo.URLResourcesPageInVO;
import com.fykj.sample.urlresources.vo.URLResourcesSaveInVO;

/**
 * @author zhengzw
 *
 */
@Controller
@RequestMapping("/urlresources")
public class URLResourcesController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private URLResourcesService urlResourcesService;

	/**
	 * 资源列表页面
	 */
	@RequestMapping("/toResourcesList")
	public String toResourcesList() {
		return "urlresources/urlresources-list";
	}

	/**
	 * 添加资源页面
	 */
	@RequestMapping("/toResourcesAdd")
	public String toResourcesAdd() {
		return "urlresources/urlresources-add";
	}

	/**
	 * 编辑资源页面
	 */
	@RequestMapping("/toResourcesEdit")
	public String toResourcesEdit(String id, Model model) {
		URLResources res = urlResourcesService.getUrlResourcesById(id);
		model.addAttribute("resources", res);
		return "urlresources/urlresources-edit";
	}

	/**
	 * 资源详情页面
	 */
	@RequestMapping("/toResourcesView")
	public String toResourcesView(String id, Model model) {
		URLResources res = urlResourcesService.getUrlResourcesById(id);
		model.addAttribute("resources", res);
		return "urlresources/urlresources-view";
	}

	/**
	 * 获取资源列表
	 * 
	 * @param vo
	 * @param pageVo
	 * @return
	 */
	@RequestMapping("/getUrlResourcesPage")
	@ResponseBody
	public InvokeResult getUrlResourcesPage(URLResourcesPageInVO vo, SimplePageRequestVO pageVo) {
		try {
			JPage<URLResources> page = urlResourcesService.getUrlResourcesPage(vo,
					new SimplePageRequest(pageVo.getPage(), pageVo.getSize()));
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
	 * 根据ID获取资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getUrlResourcesById")
	@ResponseBody
	public InvokeResult getUrlResourcesById(String id) {
		try {
			URLResources res = urlResourcesService.getUrlResourcesById(id);
			return InvokeResult.success(res);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 保存资源
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/saveUrlResources")
	@ResponseBody
	public InvokeResult saveUrlResources(URLResourcesSaveInVO vo) {
		try {
			URLResources res = urlResourcesService.saveUrlResources(Copy.simpleCopy(vo, URLResources.class));
			return InvokeResult.success(res);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 编辑资源
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/editUrlResources")
	@ResponseBody
	public InvokeResult editUrlResources(URLResourcesEidtInVO vo) {
		try {
			urlResourcesService.editUrlResources(Copy.simpleCopy(vo, URLResources.class));
			return InvokeResult.success(vo.getId());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 删除资源
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteUrlResources")
	@ResponseBody
	public InvokeResult deleteUrlResources(String ids) {
		if (StringUtils.isBlank(ids)) {
			return InvokeResult.failure("获取资源信息失败!");
		}
		try {
			String[] arr = ids.split(",");
			urlResourcesService.deleteUrlResources(arr);
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
