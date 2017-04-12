package com.fykj.product.evaluation.common.constant;

public enum DataCollectionType {
	OFF_LINE("offLine", "线下"),
	FILL_FORM("fillForm", "填报"),
	QUESTION_NAIRE("questionNaire", "问卷") ;
	
	private String key ;
	private String value ;
	
	private DataCollectionType() {
		
	}
	
	private DataCollectionType(String key ,String value) {
		this.setKey(key) ;
		this.setValue(value) ;
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
		for(DataCollectionType dataCollectionType : DataCollectionType.values()){
			if(dataCollectionType.getKey().equals(key)) {
				result = dataCollectionType.getValue() ;
			}
		}
		return result ;
	}
	
	public static void main(String[] args) {
		System.out.println(DataCollectionType.valueOf("QUESTION_NAIRE").getValue());
		for(DataCollectionType dataCollectionType : DataCollectionType.values()){
			System.out.println(dataCollectionType.getKey());
			System.out.println(dataCollectionType.getValue());
		}
	}
}
