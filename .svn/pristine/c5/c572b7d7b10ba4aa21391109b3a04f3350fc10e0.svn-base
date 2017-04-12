/**
 * 
 */
package com.fykj.sample.element.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;

/**
 * @author zhengzw
 *
 */
@Entity
@Table(name = "t_sys_element")
public class PageElement extends AbstractEntity {
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4575504454650753722L;

	@Column(name = "name")
	private String name;
	
	@Column(name = "menu_id", length = 36)
	private String menuId;
	
	@Column(name = "func_id")
	private String funcId;
	
	@Column(name = "description")
	private String description;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
