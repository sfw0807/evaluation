package com.fykj.product.evaluation.common.constant;
/**
 * ClassName: FillType  
 * (填报类型)
 * @author zhangtian  
 * @version
 */
public enum FillType {
	FILL_BLANK("0", "无"),
	FILL_SINGLE("1", "单选"),
	FILL_MULTI("2", "多选"),
	FILL_ANSWER("3", "问答") ,
	FILL_ATTACH("4", "附件") ;
	
	private String key ;
	private String value ;
	
	private FillType() {
		
	}
	
	public static String getValue(String key) {
		String result = "" ;
		for(FillType fillType : FillType.values()){
			if(fillType.getKey().equals(key)) {
				result = fillType.getValue() ;
			}
		}
		return result ;
	}
	
	private FillType(String key, String value) {
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
	
	public enum FillAnswerGetScore {
		GET_SCORE("1", "记分"),
		GET_NO_SCORE("2", "不计分");
		
		FillAnswerGetScore(String key, String value) {
			this.key = key ;
			this.value = value ;
		}
		
		private String key; 
		private String value ;
		
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
			for(FillAnswerGetScore answerGetScore : FillAnswerGetScore.values()){
				if(answerGetScore.getKey().equals(key)) {
					result = answerGetScore.getValue() ;
				}
			}
			return result ;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(FillType.FillAnswerGetScore.GET_SCORE.getKey());
	}
}
