/**
 * 
 */
package com.fykj.sample.menu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.sample.menu.model.SysMenu;
import com.fykj.sample.menu.service.MenuService;
import com.fykj.sample.menu.vo.SysMenuEditInVO;
import com.fykj.sample.menu.vo.SysMenuSaveInVO;
import com.fykj.sample.tree.JTreeNode;

/**
 * @author zhengzw
 *
 */
@Controller
@RequestMapping("/sysmenu")
@ParamValidation4Controller
public class MenuController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private MenuService menuService;

	/**
	 * 角色列表页面
	 */
	@RequestMapping("/toMenuList")
	public String toMenuList() {
		return "menu/menu-list";
	}

	/**
	 * 添加角色页面
	 */
	@RequestMapping("/toMenuAdd")
	public String toMenuAdd() {
		return "menu/menu-add";
	}

	/**
	 * 编辑角色页面
	 */
	@RequestMapping("/toMenuEdit")
	public String toMenuEdit(String id, Model model) {
		SysMenu sysMenu = menuService.getMenuById(id);
		model.addAttribute("menu", sysMenu);
		return "menu/menu-edit";
	}
	
	/**
	 * 获取父节点id
	 * @param id
	 * @return
	 */
	@RequestMapping("/getParentId")
	@ResponseBody
	public InvokeResult getParentId(String id) {
		try {
			Map<String, String> result = new HashMap<String, String>() ;
			result.put("parentId", id) ;
			return InvokeResult.success(result) ;
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 根据用户获取菜单
	 * 
	 * @return
	 */
	@RequestMapping("/loadMenuTreeUser")
	@ResponseBody
	public InvokeResult loadMenuTreeUser() {
		try {
//			List<JTreeNode> list = (List<JTreeNode>) ServerSessionHolder.get("menus");
			//TODO remove later
			String userId = ServerSessionHolder.getSessionUser().getId();
			List<JTreeNode> list = menuService.getMenuTreeByUser(userId);
			return InvokeResult.success(list);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 加载菜单数
	 * 
	 * @return
	 */
	@RequestMapping("/loadMenuTree")
	@ResponseBody
	public InvokeResult loadMenuTree() {
		try {
			List<JTreeNode> list = menuService.getMenuTree();
			return InvokeResult.success(list);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 保存菜单
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/saveMenu")
	@ResponseBody
	public InvokeResult saveMenu(SysMenuSaveInVO vo) {
		try {
			SysMenu sysMenu = menuService.saveMenu(Copy.simpleCopy(vo, SysMenu.class));
			return InvokeResult.success(sysMenu);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 根据ID获取菜单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getMenuById")
	@ResponseBody
	public InvokeResult getMenuById(String id) {
		try {
			SysMenu sysMenu = menuService.getMenuById(id);
			return InvokeResult.success(sysMenu);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 编辑菜单
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/editMenu")
	@ResponseBody
	public InvokeResult editMenu(SysMenuEditInVO vo) {
		try {
			menuService.editMenu(Copy.simpleCopy(vo, SysMenu.class));
			return InvokeResult.success(true);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 删除菜单
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/deleteMenu")
	@ResponseBody
	public InvokeResult deleteMenu(String id) {
		try {
			menuService.deleteMenu(id);
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
