/**
 * 
 */
package com.fykj.sample.sysuser.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fykj.CodesTable;
import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.model.InvokeResult;
import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.platform.kernel.parameter.annotation.ParamValidation4Controller;
import com.fykj.platform.util.Copy;
import com.fykj.platform.web.model.SimplePageRequestVO;
import com.fykj.sample.cache.DictionaryCacheHelper;
import com.fykj.sample.common.SysConstants;
import com.fykj.sample.sysrole.vo.SysUserRoleOutVO;
import com.fykj.sample.sysuser.model.SysUser;
import com.fykj.sample.sysuser.service.SysUserService;
import com.fykj.sample.sysuser.vo.RetrievePassowrd;
import com.fykj.sample.sysuser.vo.SysUserAddInVO;
import com.fykj.sample.sysuser.vo.SysUserEditInVO;
import com.fykj.sample.sysuser.vo.SysUserPageInVO;
import com.fykj.sample.sysuser.vo.SysUserPageOutVO;
import com.fykj.sample.sysuser.vo.SysUserRoleInVO;
import com.fykj.sample.sysuser.vo.UpdatePasswordInVo;

/**
 * @author wg525
 *
 */
@Controller
@RequestMapping("/sysuser")
@ParamValidation4Controller
public class SysUserController {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysUserService sysUserService;
	
	/**
	 * 忘记密码页面
	 */
	@RequestMapping("/toRetrievePassword")
	public String toRetrievePassword() {
		return "retrievePassword";
	}
	
	/**
	 * 邮箱修改密码跳转页面
	 */
	@RequestMapping("/toforgetPassword")
	public String toforgetPassword(Model model,String account) {
		model.addAttribute("account",account);
		return "forgetPassword";
	}


	/**
	 * 用户列表页面
	 */
	@RequestMapping("/toUserList")
	public String toUserList(Model model) {
		model.addAttribute("disableds", DictionaryCacheHelper.getDictData(CodesTable.UserState.code));
		return "sys/user/sysuser-list";
	}

	/**
	 * 添加用户页面
	 */
	@RequestMapping("/toUserAdd")
	public String toUserAdd(Model model) {
		model.addAttribute("disableds", DictionaryCacheHelper.getDictData(CodesTable.UserState.code));
		return "sys/user/sysuser-add";
	}

	/**
	 * 编辑用户页面
	 */
	@RequestMapping("/toUserEdit")
	@ResponseBody
	public InvokeResult toUserEdit(String id, Model model) {
		try {
			SysUser su = sysUserService.getSysUserById(id);
			Map<String, Object> result = new HashMap<String, Object>() ;
			result.put("user", su) ;
			result.put("disableds", DictionaryCacheHelper.getDictData(CodesTable.UserState.code)) ;
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
	 * 用户详情页面
	 */
	@RequestMapping("/toUserView")
	public String toUserView(String id, Model model) {
		SysUser su = sysUserService.getSysUserById(id);
		model.addAttribute("user", su);
		return "sys/user/sysuser-view";
	}

	/**
	 * 用户分配角色页面
	 */
	@RequestMapping("/toUserRole")
	public String toUserRole() {
		return "sys/user/sysuser-role";
	}
	
	/**
	 * 修改密码页面
	 */
	@RequestMapping("/toUpdatePassword")
	public String toUpdatePassword() {
		return "updatePassword";
	}
	
	

	/**
	 * 保存用户
	 * 
	 * @param addInVO
	 * @return
	 */
	@RequestMapping("/saveSysUser")
	@ResponseBody
	public InvokeResult saveSysUser(SysUserAddInVO addInVO) {
		try {
			SysUser sysUser = Copy.simpleCopy(addInVO, SysUser.class);
			sysUserService.saveSysUser(sysUser);
			return InvokeResult.success(sysUser.getId());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 禁用用户
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/disableSysUser")
	@ResponseBody
	public InvokeResult disableSysUser(String userId) {
		try {
			if (StringUtils.isBlank(userId)) {
				return InvokeResult.failure("未获取用户信息");
			}
			String[] userIds = userId.split(",");
			sysUserService.disableSysUser(userIds);
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
	 * 启用用户
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/enableSysUser")
	@ResponseBody
	public InvokeResult enableSysUser(String userId) {
		try {
			if (StringUtils.isBlank(userId)) {
				return InvokeResult.failure("未获取用户信息");
			}
			String[] userIds = userId.split(",");
			sysUserService.enableSysUser(userIds);
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
	 * 用户列表
	 * 
	 * @param vo
	 * @param pageVo
	 * @return
	 */
	@RequestMapping("/getSysUserPage")
	@ResponseBody
	public InvokeResult getSysUserPage(SysUserPageInVO vo, SimplePageRequestVO pageVo) {
		try {
			JPage<SysUserPageOutVO> page = sysUserService.getSysUserPage(vo,
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
	 * 逻辑删除用户
	 * 
	 * @param userId
	 * @return
	 */
	@RequestMapping("/removeSysUser")
	@ResponseBody
	public InvokeResult removeSysUser(String userId) {
		try {
			if (StringUtils.isBlank(userId)) {
				return InvokeResult.failure("未获取用户信息");
			}
			String[] userIds = userId.split(",");
			sysUserService.removeSysUser(userIds);
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
	 * 重置用户密码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/resetPassword")
	@ResponseBody
	public InvokeResult resetPassword(String ids) {
		try {
			if (StringUtils.isBlank(ids)) {
				return InvokeResult.failure("未获取用户信息");
			}
			String[] arr = ids.split(",");
			sysUserService.resetPasswrodByIds(arr);
			return InvokeResult.success(SysConstants.DEFUALT_PASSWORD);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}
	
	/**
	 * 修改用户密码
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping("/updatePassword")
	@ResponseBody
	public InvokeResult updatePassword(UpdatePasswordInVo inVO) {
		try {
			sysUserService.updatePassword(inVO);
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
	 * 获取用户信息
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSysUserById")
	@ResponseBody
	public InvokeResult getSysUserById(String id) {
		try {
			SysUser su = sysUserService.getSysUserById(id);
			su.getEmail();
			return InvokeResult.success(su);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 编辑用户
	 * 
	 * @param InVO
	 * @return
	 */
	@RequestMapping("/editSysUser")
	@ResponseBody
	public InvokeResult editSysUser(SysUserEditInVO InVO) {
		try {
			SysUser sysUser = Copy.simpleCopy(InVO, SysUser.class);
			sysUserService.editSysUser(sysUser);
			return InvokeResult.success(sysUser.getId());
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 查询已有权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSysUserRoles_grant")
	@ResponseBody
	public InvokeResult getSysUserRoles_grant(String id) {
		try {
			List<SysUserRoleOutVO> page = sysUserService.getSysUserGrantRoles(id);
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
	 * 查询未有权限
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/getSysUserRoles_notGrant")
	@ResponseBody
	public InvokeResult getSysUserRoles_notGrant(String id) {
		try {
			List<SysUserRoleOutVO> page = sysUserService.getSysUserNotGrantRoles(id);
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
	 * 删除用户角色
	 * 
	 * @param userRoleIds
	 * @return
	 */
	@RequestMapping("/deleteUserRole")
	@ResponseBody
	public InvokeResult deleteUserRole(String userRoleIds) {
		try {
			if (StringUtils.isBlank(userRoleIds)) {
				return InvokeResult.failure("未获取用户角色信息");
			}
			String[] arr = userRoleIds.split(",");
			sysUserService.deleteUserRoleByIds(arr);
			return InvokeResult.success(userRoleIds);
		} catch (BusinessException e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure(e.getMessage());
		} catch (Exception e) {
			LOGGER.error(e.getMessage(), e);
			return InvokeResult.failure("系统错误");
		}
	}

	/**
	 * 添加用户角色
	 * 
	 * @param inVO
	 * @return
	 */
	@RequestMapping("/addUserRole")
	@ResponseBody
	public InvokeResult addUserRole(SysUserRoleInVO inVO) {
		try {
			String[] arr = inVO.getRoleId().split(",");
			sysUserService.addUserRoles(inVO.getUserId(), arr);
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
	 * 忘记密码发送邮件
	 */
	@RequestMapping("/retrievePassword")
	@ResponseBody
	public InvokeResult retrievePassword(String accountName) {
		try {
			sysUserService.retrievePassword(accountName);
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
	 * 忘记密码发送邮件
	 */
	@RequestMapping("/updatePasswordByAccount")
	@ResponseBody
	public InvokeResult updatePasswordByAccount(RetrievePassowrd vo) {
		try {
			sysUserService.updatePasswordByAccount(vo);
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
