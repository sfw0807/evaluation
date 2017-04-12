package com.fykj.sample.attachment.vo;

import com.fykj.platform.kernel._c.model.JOutputModel;


public class AttachmentOutVO  implements JOutputModel{

	private String id;
	private String name;

	private String realName;

	private String description;

	private String path;

	private String suffixation;
	
	private String fileName;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	
	public void setPath(String path) {
		this.path = path;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
		
		return getServerPath()+this.path+this.realName;
	}


	public String getSuffixation() {
		return suffixation;
	}

	public void setSuffixation(String suffixation) {
		this.suffixation = suffixation;
	}

	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	private String getServerPath(){
	
		return "/*nginx.service.url*/";
	}


}
