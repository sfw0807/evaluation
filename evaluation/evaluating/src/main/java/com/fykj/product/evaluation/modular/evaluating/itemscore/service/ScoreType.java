package com.fykj.product.evaluation.modular.evaluating.itemscore.service;

public enum ScoreType {

	/**
	 * 自评
	 */
	SELF("SELF"),
	
	/**
	 * 专家评
	 */
	EXPERT("EXPERT"),
	
	/**
	 * 终评
	 */
	END("END"),
	
	/**
	 * 填报
	 */
	FILL("FILL"), 
	
	/**
	 * 问卷
	 */
	SURVEY("SURVEY"),
	
	/**
	 * 最终评
	 */
	FINAL("FINAL")
	;
	
	final String name;
	
	private ScoreType(String name) {
		this.name=name;
	}
	
	
}
