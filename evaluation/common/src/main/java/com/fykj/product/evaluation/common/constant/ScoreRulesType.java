package com.fykj.product.evaluation.common.constant;

public enum ScoreRulesType {
	
	ADD_UP("addUpp", "累加") ,
	SECTION("section", "区间") ;
	
	private String key ;
	private String value ;
	
	private ScoreRulesType(){
		
	}
	
	private ScoreRulesType(String key, String value) {
		this.setKey(key) ;
		this.setValue(value) ;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
