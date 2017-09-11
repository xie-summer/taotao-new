package com.taotao.untrans;

public interface CacheTools {
	Object get(String regionName, String key);
	/**
	 * 
	 * @param regionName
	 * @param key
	 * @param value
	 */
	void set(String regionName, String key, Object value);
	void remove(String regionName, String key);
	boolean isLocal();
}
