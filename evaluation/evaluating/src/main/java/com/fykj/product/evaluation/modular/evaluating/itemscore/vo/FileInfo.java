package com.fykj.product.evaluation.modular.evaluating.itemscore.vo;

import com.fykj.platform.kernel._c.model.JModel;

public class FileInfo implements JModel {

	private String filePath;
	
	private String fileName;

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
