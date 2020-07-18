package com.bjlemon.auto;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 基于内存的缓存工具类
 * @author apple
 *
 */
public class CacheUtils {
	// 缓存配置信息
	private static Map<String, Integer> cache = new ConcurrentHashMap<>();
	
	// 设置缓存
	public static void setCache(String key,Integer value) {
		cache.put(key, value);
	}
	
	// 获取缓存
	public static Integer getCache(String key) {
		return cache.get(key);
	}
	
	// 清空缓存
	public static void clearCache() {
		cache.clear();
	}
	
	/**
	 * 是否包含key值
	 * @param key
	 * @return
	 */
	public static boolean isContainKey(String key) {
		return cache.containsKey(key);
	}
}
