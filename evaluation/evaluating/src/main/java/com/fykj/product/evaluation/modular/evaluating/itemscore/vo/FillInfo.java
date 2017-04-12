package com.fykj.product.evaluation.modular.evaluating.itemscore.vo;

import com.fykj.platform.kernel._c.model.JModel;

public class FillInfo implements JModel {

	private String askKey;
	
	private String ask;
	
	private Object answer;

	private String type;
	
	public String getAsk() {
		return ask;
	}

	public void setAsk(String ask) {
		this.ask = ask;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Object getAnswer() {
		return answer;
	}

	public void setAnswer(Object answer) {
		this.answer = answer;
	}

	public String getAskKey() {
		return askKey;
	}

	public void setAskKey(String askKey) {
		this.askKey = askKey;
	}
	
	
	
}
