package com.taotao.untrans;

public interface CacheTools {
	/**
	 * 获取缓存
	 * @param regionName
	 * @param key
	 * @return
	 */
	Object get(String regionName, String key);
	/**
	 * 设置缓存
	 * @param regionName
	 * @param key
	 * @param value
	 */
	void set(String regionName, String key, Object value);

	/**
	 * 移除缓存
	 * @param regionName
	 * @param key
	 */
	void remove(String regionName, String key);

	/**
	 * 是否为本地缓存
	 * @return
	 */
	boolean isLocal();
}
