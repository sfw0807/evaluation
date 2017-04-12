package com.fykj.product.evaluation.common.constant;

public enum PreliminaryType {
	MANUAL("manual", "人工"),
	SYSTEM("system", "系统") ;
	
	private String key ;
	private String value ;
	
	private PreliminaryType(){
		
	}
	
	private PreliminaryType(String key, String value) {
		this.key = key ;
		this.value = value ;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getValue(String key) {
		String result = "" ;
		for(PreliminaryType preliminaryType : PreliminaryType.values()){
			if(preliminaryType.getKey().equals(key)) {
				result = preliminaryType.getValue() ;
			}
		}
		return result ;
	}
}
