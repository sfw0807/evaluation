/**
 * 
 */
package com.fykj.sample.element.controller;

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
import com.fykj.sample.element.model.PageElement;
import com.fykj.sample.element.service.PageElementService;
import com.fykj.sample.element.vo.ElementAddInVO;
import com.fykj.sample.element.vo.ElementEditInVO;
import com.fykj.sample.element.vo.ElementPageInVO;
import com.fykj.sample.element.vo.ElementPageOutVO;

/**
 * @author zhengzw
 *
 */
@Controller
@RequestMapping("/element")
public class PageElementController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private PageElementService elementService;

	/**
	 * 元素列表页面
	 */
	@RequestMapping("/toElementList")
	public String toElementList() {
		return "element/element-list";
	}

	/**
	 * 添加元素页面
	 */
	@RequestMapping("/toElementAdd")
	public String toElementAdd() {
		return "element/element-add";
	}

	/**
	 * 编辑元素页面
	 */
	@RequestMapping("/toElementEdit")
	public String toElementEdit(String id, Model model) {
		ElementPageOutVO pageElement = elementService.getElementById(id);
		model.addAttribute("element", pageElement);
		return "element/element-edit";
	}

	/**
	 * 元素详情页面
	 */
	@RequestMapping("/toElementView")
	public String toElementView(String id, Model model) {
		ElementPageOutVO pageElement = elementService.getElementById(id);
		model.addAttribute("element", pageElement);
		return "element/element-view";
	}

	/**
	 * 保存元素
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/saveElement")
	@ResponseBody
	public InvokeResult saveElement(ElementAddInVO vo) {
		try {
			PageElement pageElement = elementService.saveElement(Copy.simpleCopy(vo, PageElement.class));
			return InvokeResult.success(pageElement);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 获取元素列表
	 * 
	 * @param vo
	 * @param pageVo
	 * @return
	 */
	@RequestMapping("/getElementPage")
	@ResponseBody
	public InvokeResult getElementPage(ElementPageInVO vo, SimplePageRequestVO pageVo) {
		try {
			JPage<ElementPageOutVO> page = elementService.getElementPage(vo,
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
	 * 根据ID获取元素
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getElementById")
	@ResponseBody
	public InvokeResult getElementById(String id) {
		try {
			ElementPageOutVO pageElement = elementService.getElementById(id);
			return InvokeResult.success(pageElement);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 编辑元素
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/editElement")
	@ResponseBody
	public InvokeResult editElement(ElementEditInVO vo) {
		try {
			elementService.editElement(Copy.simpleCopy(vo, PageElement.class));
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
	 * 删除元素
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteElement")
	@ResponseBody
	public InvokeResult deleteElement(String ids) {
		if (!StringUtils.isNotBlank(ids)) {
			return InvokeResult.failure("未获取页面元素信息");
		}
		try {
			String[] arr = ids.split(",");
			elementService.deleteElement(arr);
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
