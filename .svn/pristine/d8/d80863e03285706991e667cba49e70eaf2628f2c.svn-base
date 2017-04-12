/**
 * 
 */
package com.fykj.sample.element.service;

import java.util.List;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.sample.element.model.PageElement;
import com.fykj.sample.element.model.SysRoleElement;
import com.fykj.sample.element.vo.ElementPageInVO;
import com.fykj.sample.element.vo.ElementPageOutVO;
import com.fykj.sample.element.vo.SysRoleElementOutVo;
import com.fykj.sample.element.vo.SysUserElementOutVO;

/**
 * @author zhengzw
 *
 */
public interface PageElementService {
	public PageElement saveElement(PageElement pageElement);

	public JPage<ElementPageOutVO> getElementPage(ElementPageInVO vo, SimplePageRequest page);

	public ElementPageOutVO getElementById(String id);

	public void editElement(PageElement pageElement);

	public void deleteElement(String id);

	public void deleteElement(String[] ids);

	public List<SysRoleElementOutVo> getRoleGrantElements(String roleId);

	public List<SysRoleElementOutVo> getRoleNotGrantElements(String roleId);

	public void deleteRoleElement(String id);

	public SysRoleElement saveSysRoleElement(String roleId, String elementId);

	public List<SysUserElementOutVO> getElementByUser(String userId);
}
