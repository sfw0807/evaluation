package com.fykj.sample.cache;

import java.util.List;
import java.util.Map;

import com.fykj.sample.common.context.SpringContext;

public class DictionaryCacheHelper {

	private static DictionaryCache dictionaryCache = SpringContext.getBean(DictionaryCache.class);

	public static String getDictDataName(String dictCode, String dictDataValue) {
		return dictionaryCache.getDictDataName(dictCode, dictDataValue);
	}
	
	public static List<Map<String, String>> getDictData(String dictCode){
		return dictionaryCache.getDictData(dictCode);
	}
	
	public static void load(){
		dictionaryCache.load();	
	}

}
