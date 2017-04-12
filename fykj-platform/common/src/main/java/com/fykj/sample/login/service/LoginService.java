/**
 * 
 */
package com.fykj.sample.login.service;

import com.fykj.sample.sysuser.model.SysUser;

/**
 * @author zhengzw
 *
 */
public interface LoginService {

	/**
	 * 用户登录
	 * 
	 * @param context
	 * @param sysUser
	 * @return
	 */
	public void userLogin(SysUser sysUser);
}
