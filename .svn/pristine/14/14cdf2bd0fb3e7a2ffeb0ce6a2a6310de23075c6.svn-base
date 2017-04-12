package com.fykj.plugins;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.fykj.platform.kernel._c.cache.EhCacheService;

@Service
public class _EhCacheService implements EhCacheService {

	private Map<String, Object> map=new HashMap<>();
	
	@Override
	public Object putNeverExpired(String key, Object object) {
		return map.put(key, object);
	}

	@Override
	public Object put(String key, Object object) {
		return map.put(key, object);
	}

	@Override
	public Object get(String key) {
		return map.get(key);
	}

	@Override
	public Object remove(String key) {
		return map.remove(key);
	}

	@Override
	public boolean contains(String key) {
		return map.containsKey(key);
	}

}
