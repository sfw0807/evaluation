package com.fykj.sample.sysrole.controller;

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
import com.fykj.platform.util.Copy;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.sample.element.vo.SysRoleElementOutVo;
import com.fykj.sample.sysrole.model.SysRole;
import com.fykj.sample.sysrole.model.SysRoleMenu;
import com.fykj.sample.sysrole.service.SysRoleService;
import com.fykj.sample.sysrole.vo.SysRoleAddInVO;
import com.fykj.sample.sysrole.vo.SysRoleAssignMenuInVO;
import com.fykj.sample.sysrole.vo.SysRoleCriteriaInVO;
import com.fykj.sample.sysrole.vo.SysRoleElementInVO;
import com.fykj.sample.sysrole.vo.SysRoleGetOutVO;
import com.fykj.sample.sysrole.vo.SysRoleModifyInVO;
import com.fykj.sample.sysrole.vo.SysRoleResourcesInVO;
import com.fykj.sample.urlresources.vo.SysRoleResourcesOutVo;

/**
 * 
 * @author gejj
 *
 */
@Controller
@RequestMapping("/sysrole")
public class SysRoleController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysRoleService sysRoleService;

	/**
	 * 角色列表页面
	 */
	@RequestMapping("/toRoleList")
	public String toRoleList() {
		return "sys/role/sysrole-list";
	}

	/**
	 * 添加角色页面
	 */
	@RequestMapping("/toRoleAdd")
	public String toRoleAdd() {
		return "sys/role/sysrole-add";
	}

	/**
	 * 编辑角色页面
	 */
	@RequestMapping("/toRoleEdit")
	public String toRoleEdit(String id, Model model) {
		SysRole sysRole = sysRoleService.getById(id);
		model.addAttribute("role", sysRole);
		return "sys/role/sysrole-edit";
	}

	/**
	 * 角色详情页面
	 */
	@RequestMapping("/toRoleView")
	public String toRoleView(String id, Model model) {
		SysRole sysRole = sysRoleService.getById(id);
		model.addAttribute("role", sysRole);
		return "sys/role/sysrole-view";
	}

	/**
	 * 角色分配资源页面
	 */
	@RequestMapping("/toRoleResources")
	public String toRoleResources() {
		return "sys/role/sysrole-resources";
	}

	/**
	 * 角色分配菜单页面
	 */
	@RequestMapping("/toRoleMenu")
	public String toRoleMenu() {
		return "sys/role/sysrole-menu";
	}

	/**
	 * 角色分配元素页面
	 */
	@RequestMapping("/toRoleElement")
	public String toRoleElement() {
		return "sys/role/sysrole-element";
	}

	/**
	 * 新增角色
	 * 
	 * @param sysRoleAddInVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveSysRole")
	@ResponseBody
	public InvokeResult saveSysRole(SysRoleAddInVO sysRoleAddInVO) {
		try {
			SysRole sysRole = Copy.simpleCopy(sysRoleAddInVO, SysRole.class);
			sysRoleService.save(sysRole);
			return InvokeResult.success(sysRole.getId());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 查询角色分页
	 * 
	 * @param serviceContext
	 * @param sysRoleCriteriaInVO
	 * @param simplePageRequestVO
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getPage")
	@ResponseBody
	public InvokeResult getPage(SysRoleCriteriaInVO sysRoleCriteriaInVO, SimplePageRequestVO simplePageRequestVO) {
		try {
			JPage<SysRole> page = sysRoleService.getPage(sysRoleCriteriaInVO,
					new SimplePageRequest(simplePageRequestVO.getPage(), simplePageRequestVO.getSize()));
			List<SysRole> content = page.getContent();
			List<SysRoleGetOutVO> outContent = new ArrayList<SysRoleGetOutVO>();
			for (SysRole sysRole : content) {
				SysRoleGetOutVO sysRoleGetOutVO = Copy.simpleCopy(sysRole, SysRoleGetOutVO.class);
				outContent.add(sysRoleGetOutVO);
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
	 * 根据ID获取角色
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getById")
	@ResponseBody
	public InvokeResult getById(String id) throws Exception {
		try {
			SysRole sysRole = sysRoleService.getById(id);
			SysRoleGetOutVO sysRoleGetOutVO = Copy.simpleCopy(sysRole, SysRoleGetOutVO.class);
			return InvokeResult.success(sysRoleGetOutVO);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 编辑角色
	 * 
	 * @param sysRoleModifyInVO
	 * @return
	 */
	@RequestMapping("/modify")
	@ResponseBody
	public InvokeResult modifyRole(SysRoleModifyInVO sysRoleModifyInVO) {
		try {
			SysRole sysRole = sysRoleService.getById(sysRoleModifyInVO.getId());
			Copy.simpleCopyExcludeNull(sysRoleModifyInVO, sysRole);
			sysRoleService.modify(sysRole);
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
	 * 删除角色
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public InvokeResult delete(String ids) {
		if (StringUtils.isBlank(ids)) {
			return InvokeResult.failure("为获取角色信息");
		}
		try {
			String[] idarry = ids.split(",");
			sysRoleService.delete(idarry);
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
	 * 角色分配菜单
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/assignMenu")
	@ResponseBody
	public InvokeResult assignMenu(SysRoleAssignMenuInVO vo) {
		try {
			String[] selected = vo.getSelected().split(",");
			String[] undetermined = vo.getUndetermined().split(",");
			sysRoleService.assignRoleMenu(vo.getRoleId(), selected, undetermined);
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
	 * 获取已授权角色菜单
	 * 
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/getAssignRoleMenu")
	@ResponseBody
	public InvokeResult getAssignRoleMenu(String roleId) {
		try {
			List<SysRoleMenu> list = sysRoleService.getAssignRoleMenu(roleId);
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
	 * 获取已授权角色资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSysRoleURLResources_grant")
	@ResponseBody
	public InvokeResult getAssignRoleURLResources_grant(String id) {
		try {
			List<SysRoleResourcesOutVo> list = sysRoleService.getRoleGrantResources(id);
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
	 * 获取未授权角色资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSysRoleURLResources_not_grant")
	@ResponseBody
	public InvokeResult getSysRoleURLResources_not_grant(String id) {
		try {
			List<SysRoleResourcesOutVo> list = sysRoleService.getRoleNotGrantResources(id);
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
	 * 删除角色资源
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteRoleResources")
	@ResponseBody
	public InvokeResult deleteRoleResources(String ids) {
		try {
			if (StringUtils.isBlank(ids)) {
				return InvokeResult.failure("未获取角色资源信息");
			}
			String[] arr = ids.split(",");
			sysRoleService.deleteRoleResources(arr);
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
	 * 添加角色资源
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/addRoleResources")
	@ResponseBody
	public InvokeResult addRoleResources(SysRoleResourcesInVO vo) {
		try {
			String[] arr = vo.getResourcesId().split(",");
			sysRoleService.saveSysRoleResource(vo.getRoleId(), arr);
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
	 * 获取已授权角色元素
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSysRoleElement_grant")
	@ResponseBody
	public InvokeResult getSysRoleElement_grant(String id) {
		try {
			List<SysRoleElementOutVo> list = sysRoleService.getRoleGrantElement(id);
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
	 * 获取未授权角色元素
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSysRoleElement_not_grant")
	@ResponseBody
	public InvokeResult getSysRoleElement_not_grant(String id) {
		try {
			List<SysRoleElementOutVo> list = sysRoleService.getRoleNotGrantElement(id);
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
	 * 删除角色元素
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/deleteRoleElement")
	@ResponseBody
	public InvokeResult deleteRoleElement(String ids) {
		if (StringUtils.isBlank(ids)) {
			return InvokeResult.failure("未获取角色元素信息");
		}
		try {
			String[] arr = ids.split(",");
			sysRoleService.deleteRoleElement(arr);
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
	 * 添加角色元素
	 * 
	 * @param vo
	 * @return
	 */
	@RequestMapping("/addRoleElement")
	@ResponseBody
	public InvokeResult addRoleElement(SysRoleElementInVO vo) {
		try {
			String[] arr = vo.getElementId().split(",");
			sysRoleService.saveSysRoleElement(vo.getRoleId(), arr);
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
	 *  getSysRoles:(查询角色  不分页). 
	 *  @return_type:InvokeResult
	 *  @author zhangtian  
	 *  @param sysRoleCriteriaInVO
	 *  @return
	 */
	@RequestMapping("/getSysRoles")
	@ResponseBody
	public InvokeResult getSysRoles(SysRoleCriteriaInVO sysRoleCriteriaInVO) {
		try {
			List<SysRole> sysRoles = sysRoleService.getSysRoles(sysRoleCriteriaInVO) ;
			return InvokeResult.success(sysRoles) ;
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}
}
