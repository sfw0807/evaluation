package com.fykj.sample.cache;

import com.fykj.sample.common.context.SpringContext;

public class SysParamCacheHelper {

	private static SysParamCache sysParamCache = SpringContext.getBean(SysParamCache.class);

	public static String getValue(String code) {
		return sysParamCache.getValue(code);
	}

	public static void load() {
		sysParamCache.load();
	}

}
