package com.adms.batch.sales.support;

import java.util.HashMap;

public class CacheObject {

	private HashMap<String, Object> cache = new HashMap<String, Object>();

	public Object putCache(String key, Object value)
	{
		this.cache.put(key, value);
		return value;
	}

	public Object getCache(String key)
	{
		return this.getCache(key);
	}

}
