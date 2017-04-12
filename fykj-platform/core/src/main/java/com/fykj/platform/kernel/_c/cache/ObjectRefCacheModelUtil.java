package com.fykj.platform.kernel._c.cache;

import com.fykj.platform.util.JStringUtils;

class ObjectRefCacheModelUtil {

	public static String key(ResourceCacheModel resourceCacheModel){
		return key(resourceCacheModel.getUri());
	}
	
	public static String key(String identifier){
		String key="";
		if(JStringUtils.isNotNullOrEmpty(identifier)){
			key=key+"-"+identifier;
		}
		return key;
	}
}