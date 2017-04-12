package com.fykj.sample.attachment.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fykj.platform.kernel._c.model.AbstractEntity;


/**
 * Auto Generated Entity
 * 
 * @author zhangj
 * 
 */
@Entity
@Table(name = "t_attachment")
public class Attachment extends AbstractEntity {

	private static final long serialVersionUID = 7427535043528604634L;

	@Column(name = "name")
	private String name;

	@Column(name = "real_name")
	private String realName;

	@Column(name = "description")
	private String description;

	@Column(name = "path")
	private String path;

	@Column(name = "suffixation")
	private String suffixation;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getDescription() {
		return description;
	}
	
	public String getNote() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}

	public String getSuffixation() {
		return suffixation;
	}

	public void setSuffixation(String suffixation) {
		this.suffixation = suffixation;
	}
	
	public String getfullRelationPath(){
		return getPath()+getRealName();
	}


}