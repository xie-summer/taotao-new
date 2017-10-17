package com.taotao.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * 字符串和Map互相转换
 */
public class StrUtil {
	
	public static final String SPLITSTR = "|";
	public static final String EQUALSTR = "=";//
	
	/**
	 * 字符串转换Map
	 * @param value
	 * @return
	 */
	public static Map<String, String> toMap(String value){
		return toMap(value, SPLITSTR);
	}
	
	/**
	 * Map转换字符串
	 * @param value
	 * @param splitCharacter
	 * @return
	 */
	public static Map<String, String> toMap(String value, String splitCharacter){
		return toMapBySubString(value, splitCharacter);
	}
	
	/**
	 * Map转换字符串
	 * @param value
	 * @param splitCharacter
	 * @return
	 */
	public static Map<String, String> toMapBySubString(String value, String splitCharacter){
		Map<String, String> result = Maps.newHashMap();
	    if (StringUtils.isNotBlank(value)) {
	    	int k=0;
	    	for (int i = 0; i < value.length(); i++) {
	    		if(value.substring(i, i + 1).equals(splitCharacter)){
	    			if(i == 0) {
	    				k = i + 1;
	    				continue;
	    			}
	    			putMap(value.substring(k, i), EQUALSTR, result);
	    			
	    			String str = value.substring(i + 1, value.length());
	    			if(StringUtils.isNotBlank(str) && str.indexOf(splitCharacter) == -1){
	    				putMap(str, EQUALSTR, result);
	    			}
	    			k = i + 1;
	    		}
	    	}
	    }
	    return result;
	}
	
	private static void putMap(String value, String splitCharacter, Map<String, String> result) {
		if(StringUtils.isNotBlank(value)){
			int index = value.indexOf(splitCharacter);
			if (index > 0 && index < value.length() - 1) {
				result.put(value.substring(0, index), value.substring(index + 1, value.length()));
			}
		}
	}
    
	/**
	 * 拼接字符串
	 * @param map
	 * @return
	 */
    public static String getMapToString(Map<String, String> map){
    	if(map==null || map.isEmpty()){
    		return null;
    	}
    	Set<Entry<String, String>> set = map.entrySet();
    	StringBuffer br = new StringBuffer();
    	for(Entry<String, String> var : set){
    		if(StringUtils.isNotBlank(var.getValue())){
    			br.append(var.getKey()).append(EQUALSTR).append(var.getValue()).append(SPLITSTR);
    		}
    	}
    	return br.toString();
    }
    
	/**
	 * 拼接字符串
	 * @param map
	 * @return
	 */
    public static String getObjMapToString(Map<String, Object> map){
    	if(map==null || map.isEmpty()){
    		return null;
    	}
    	Set<Entry<String, Object>> set = map.entrySet();
    	StringBuffer br = new StringBuffer();
    	for(Entry<String, Object> var : set){
    		if(var.getValue()!=null && StringUtils.isNotBlank(var.getValue().toString())){
    			br.append(var.getKey()).append(EQUALSTR).append(var.getValue().toString()).append(SPLITSTR);
    		}
    	}
    	return br.toString();
    }
    
	/**
	 * 拼接字符串
	 * @param key
	 * @param value
	 * @param memo
	 * @return
	 */
    public static String getMapToString(String key, String value, String memo){
    	if(StringUtils.isBlank(key)){
    		return memo;
    	}
    	Map<String, String> map = StringUtils.isBlank(memo)?new HashMap<String, String>():toMap(memo);
    	//设置值
    	map.put(key, value);
    	Set<Entry<String, String>> set = map.entrySet();
    	StringBuffer br = new StringBuffer();
    	for(Entry<String, String> var : set){
    		if(StringUtils.isNotBlank(var.getValue())){
    			br.append(var.getKey()).append(EQUALSTR).append(var.getValue()).append(SPLITSTR);
    		}
    	}
    	return br.toString();
    }
    
	public static String unicodeEncoding(final String str) {
		if(StringUtils.isBlank(str)){
			return null;
		}
		char[] utfBytes = str.toCharArray();
		StringBuffer br = new StringBuffer();
		for (char b:utfBytes) {
			String hexB = Integer.toHexString(b);
			if (hexB.length() <= 2) {
				br.append("\\u00").append(hexB);
			}else{
				br.append("\\u").append(hexB);
			}
		}
		return br.toString();
	}

}
