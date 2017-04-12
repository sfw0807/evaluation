/**
 * 
 */
package com.fykj.sample.login.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fykj.CodesTable;
import com.fykj.platform.kernel.BusinessException;
import com.fykj.platform.kernel._c.ServerSessionHolder;
import com.fykj.platform.kernel._c.model.SessionUser;
import com.fykj.platform.kernel._c.security.SecurityService;
import com.fykj.platform.kernel._c.service.ServiceSupport;
import com.fykj.sample.element.service.PageElementService;
import com.fykj.sample.element.vo.SysUserElementOutVO;
import com.fykj.sample.login.service.LoginService;
import com.fykj.sample.menu.service.MenuService;
import com.fykj.sample.sysrole.service.SysRoleService;
import com.fykj.sample.sysrole.vo.SysRoleGetOutVO;
import com.fykj.sample.sysuser.model.SysUser;
import com.fykj.sample.sysuser.service.SysUserService;
import com.fykj.sample.tree.JTreeNode;

/**
 * @author zhengzw
 *
 */
@Service
public class LoginServiceImpl extends ServiceSupport implements LoginService {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private SecurityService securityService;

	@Autowired
	private SysRoleService sysRoleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private PageElementService pageElementService;

	@Override
	public void userLogin(SysUser sysUser) {
		String user_account = sysUser.getUserAccount();
		String password = sysUser.getPassword();

		SysUser dbUser = sysUserService.getSysUserByAccount(user_account);

		if (null == dbUser) {
			throw new BusinessException("用户账号或者密码错误!");
		}

		// 密码正确
		if (securityService.encriptyByMD5(password).equals(dbUser.getPassword())) {
			if (CodesTable.UserState.disable.equals(dbUser.getDisabled())) {
				throw new BusinessException("用户账号已禁用,请联系系统管理员!");
			}
			String userId = dbUser.getId();
			// 用户信息
			SessionUser user = new SessionUser();
			user.setId(userId);
			user.setUserName(dbUser.getUserAccount());
			user.setNatureName(dbUser.getName());

			ServerSessionHolder.setSessionUser(user);
			// 角色信息
			List<SysRoleGetOutVO> list = sysRoleService.getSysRoleByUserId(userId);
			ServerSessionHolder.put("roles", list);

			// 菜单
			List<JTreeNode> menuList = menuService.getMenuTreeByUser(userId);
			ServerSessionHolder.put("menus", menuList);

			// 页面元素
			List<SysUserElementOutVO> elementList = pageElementService.getElementByUser(userId);
			ServerSessionHolder.put("elements", elementList);
		} else {
			throw new BusinessException("用户账号或者密码错误!");
		}
	}

}
