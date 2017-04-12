package com.fykj.platform.kernel._c.cache;

import com.fykj.platform.util.JStringUtils;

public class CodeRefCacheModelUtil {

	public static String key(CodeRefCacheModel codeRefCacheModel){
		return key(codeRefCacheModel.type(), codeRefCacheModel.code());
	}
	
	public static String key(String type,String code){
		String key="";
		if(JStringUtils.isNotNullOrEmpty(type)){
			key=key+"-"+type;
		}
		if(JStringUtils.isNotNullOrEmpty(code)){
			key=key+"-"+code;
		}
		return key;
	}
}
