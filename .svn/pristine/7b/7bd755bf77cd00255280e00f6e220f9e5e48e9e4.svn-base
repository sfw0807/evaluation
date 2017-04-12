/**
 * 
 */
package com.fykj.sample.urlresources.service;

import java.util.List;

import com.fykj.platform.kernel._c.model.JPage;
import com.fykj.platform.kernel._c.model.SimplePageRequest;
import com.fykj.sample.urlresources.model.SysRoleResource;
import com.fykj.sample.urlresources.model.URLResources;
import com.fykj.sample.urlresources.vo.SysRoleResourcesOutVo;
import com.fykj.sample.urlresources.vo.URLResourcesPageInVO;

/**
 * @author zhengzw
 *
 */
public interface URLResourcesService {
	
	public JPage<URLResources> getUrlResourcesPage( URLResourcesPageInVO vo, SimplePageRequest page);
	
	public URLResources getUrlResourcesById( String id);
	
	public URLResources saveUrlResources( URLResources res);
	
	public void editUrlResources( URLResources res);
	
	public void deleteUrlResources( String id);
	
	public void deleteUrlResources( String[] ids);
	
	public List<SysRoleResourcesOutVo> getRoleGrantResources( String roleId);
	
	public List<SysRoleResourcesOutVo> getRoleNotGrantResources( String roleId);
	
	public void deleteRoleResources( String id);
	
	public SysRoleResource saveSysRoleResource( String roleId, String resourcesId);
}
