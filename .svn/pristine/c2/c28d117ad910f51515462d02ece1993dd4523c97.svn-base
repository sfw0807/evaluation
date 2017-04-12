package com.fykj.product.evaluation.common.constant;

public enum RoleType {

	ADMIN("0","系统管理员角色"),
	REPORT_TYPE("1", "填报角色"),
	SELF_EVA_TYPE("2", "自评角色"),
	EXPERT_EVA("3", "专家角色"),
	FINAL_EVA("4", "终评角色");
	
	private String key ;
	private String value ;
	
	private RoleType(){
		
	}
	
	private RoleType(String key, String value) {
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
		for(RoleType roleType : RoleType.values()){
			if(roleType.getKey().equals(key)) {
				result = roleType.getValue() ;
			}
		}
		return result ;
	}
	
}
