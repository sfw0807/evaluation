package com.fykj.sample.cache;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fykj.platform.kernel._c.cache.EhCacheService;
import com.fykj.sample.sysparam.model.SysParam;
import com.fykj.sample.sysparam.service.SysParamService;

@Component
public class SysParamCache {

	private static final String SYSPARAM_CACHE_NAME = "sysparam_cache";

	@Autowired(required=false)
	private EhCacheService ehCacheService;

	@Autowired
	private SysParamService sysParamService;

	public void load() {
		Map<String, String> sysparam = new HashMap<String, String>();
		List<SysParam> list = sysParamService.loadSysParam();
		if (CollectionUtils.isNotEmpty(list)) {
			for (SysParam param : list) {
				sysparam.put(param.getCode(), param.getValue());
			}
		}
		ehCacheService.putNeverExpired(SYSPARAM_CACHE_NAME, sysparam);
	}

	@SuppressWarnings("unchecked")
	public Map<String, String> get() {
		Object cache = ehCacheService.get(SYSPARAM_CACHE_NAME);
		if (cache != null) {
			Map<String, String> sysparam = (Map<String, String>) cache;
			return sysparam;
		}
		return null;
	}

	public String getValue(String code) {
		Map<String, String> sysparam = get();
		if (sysparam == null) {
			return null;
		} else {
			return sysparam.get(code);
		}
	}

}
