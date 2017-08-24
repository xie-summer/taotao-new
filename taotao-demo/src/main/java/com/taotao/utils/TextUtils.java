/**
 * 
 */
package com.taotao.utils;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

/**
 * 长文本的公共处理方法
 * @author songtukang-001
 *
 */
public class TextUtils {
	/**符号“_”**/
	public static final String SYMBOL_DASH = "_";
	/**符号“:”**/
	public static final String SYMBOL_COLON = ":";
	/**符号“,”**/
	public static final String SYMBOL_COMMA = ",";
	/**符号“%”**/
	public static final String SYMBOL_PERCENT = "%";
	/**符号“.”**/
	public static final String SYMBOL_DOT = ".";
	/**符号“|”**/
	public static final String SYMBOL_VERTICAL = "|";
	/**随机字符串**/
	public static final String RANDOM_STRING = "abcdefghijklmnopqrstuvwxyz0123456789";
	/**16位**/
	public static final int RANDOM_LENGTH_16 = 16;
	
	/**
	 * 生成一个16位的随机字符串
	 * @return
	 */
	public static String get16RandomString() {
		return getRandomString(RANDOM_LENGTH_16);
	}

	/**
	 * 生成一个length位的随机字符串
	 * @param length
	 * @return
	 */
	public static String getRandomString(int length) {
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			int number = random.nextInt(RANDOM_STRING.length());
			sb.append(RANDOM_STRING.charAt(number));
		}
		return sb.toString();
	}
	
	/**
	 * 长文本拆分
	 * @param xml
	 * @param length
	 * @return
	 */
	public static List<String> split(String text, int length){
		List<String> result = new ArrayList<String>();
		try {
			if (text != null){
				char[] charArray = text.toCharArray();
				StringBuilder sb = new StringBuilder();
				int count = 0;
				for (int i=0; i<charArray.length; i++) {
					char ch = charArray[i];
					int chLength = Character.toString(ch).getBytes("UTF-8").length;
					if (count+chLength > length) {
						result.add(sb.toString());
						sb = new StringBuilder();
						count = 0;
					}
					count += chLength;
					sb.append(ch);
				}
				result.add(sb.toString());
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("UnsupportedEncoding!", e);
		} 
		return result;		
	}
	
	/**
	 * 将entity集合中的属性拼接成指定分隔符的串
	 * 
	 * @param splitString
	 * @param propertyName
	 * @param entities
	 * @return
	 */
	public static <T> String joinEntityPropertyString(String splitString, String propertyName, Collection<T> entities){
		StringBuilder result = new StringBuilder();
		if(entities != null && entities.size()>0) {
			for(T t : entities){
				PropertyDescriptor pd = BeanUtils.getPropertyDescriptor(t.getClass(), propertyName);
				Object value;
				try {
					value = pd.getReadMethod().invoke(t);
					if(value != null && StringUtil.isNotBlank(value.toString())){
						if (result.length() != 0) {
							result.append(SYMBOL_COMMA);
						}
						result.append(value);
					}
				} catch (IllegalArgumentException e) {
					throw new RuntimeException("IllegalArgumentException!", e);
				} catch (IllegalAccessException e) {
					throw new RuntimeException("IllegalAccessException!", e);
				} catch (InvocationTargetException e) {
					throw new RuntimeException("InvocationTargetException!", e);
				}
				
			}
		}
		
		return result.toString();
	}
	
	/**
	 * 拼接字符串
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinString(String... strs ){
		StringBuilder result = new StringBuilder();
		if(strs != null && strs.length>0){
			for(String str : strs){
				if(StringUtil.isNotBlank(str)){
					result.append(str);
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * 拼接Object
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinObject(Object... objs ){
		StringBuilder result = new StringBuilder();
		if(objs != null && objs.length>0){
			for(Object obj : objs){
				if(obj!=null && StringUtil.isNotBlank(obj.toString())){
					result.append(obj.toString());
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * “_”拼接字符串
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinByDash(String... strs){
		return joinBySymbol(strs,SYMBOL_DASH);
	}
	
	/**
	 * “,”拼接字符串
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinByComma(String... strs){
		return joinBySymbol(strs,SYMBOL_COMMA);
	}
	
	/**
	 * “,”拼接字符串list
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinListByComma(Collection<String> list){
		return joinListBySymbol(list,SYMBOL_COMMA);
	}
	
	/**
	 * “:”拼接字符串
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinByColon(String... strs){
		return joinBySymbol(strs,SYMBOL_COLON);
	}
	
	/**
	 * “|”拼接字符串
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinByVertical(String... strs){
		return joinBySymbol(strs,SYMBOL_VERTICAL);
	}
	
	/**
	 * “|”拼接字符串
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinByVertical(Object... objs){
		return joinObject(objs,SYMBOL_VERTICAL);
	}
	
	/**
	 * “:”拼接字符串
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinByDot(String... strs){
		return joinBySymbol(strs,SYMBOL_DOT);
	}
	
	/**
	 * 拼接字符串
	 * @param strs  需要连接的字符数组
	 * @param regex 连接字符
	 * @return 拼接后代字符串
	 */
	public static String joinBySymbol(String[] strs, String regex){
		StringBuilder result = new StringBuilder();
		if(strs != null && strs.length>0){
			int count = 0;
			for(String str:strs){
				if(StringUtil.isNotBlank(str)){
					//采用第一个不添加符号
					result.append(count==0?"":regex).append(str);
					count++;
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * 拼接Object
	 * @param strs
	 * @return 拼接后代字符串
	 */
	public static String joinObject(Object[] objs, String regex){
		StringBuilder result = new StringBuilder();
		if(objs != null && objs.length>0){
			int count = 0;
			for(Object obj:objs){
				if(obj!=null&& StringUtils.isNotBlank(obj.toString())){
					//采用第一个不添加符号
					result.append(count==0?"":regex).append(obj.toString());
					count++;
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * 拼接字符串
	 * @param list 需要连接的字符list
	 * @param regex
	 * @return
	 */
	public static String joinListBySymbol(Collection<String> list , String regex){
		StringBuilder result = new StringBuilder();
		if(list != null && list.size()>0){
			int count = 0;
			for(String str:list){
				if(StringUtil.isNotBlank(str)){
					//采用第一个不添加符号
					result.append(count==0?"":regex).append(str);
					count++;
				}
			}
		}
		return result.toString();
	}
	
	/**
	 * 按"_"拆分splitStr为String数组
	 * @param splitStr
	 * @return
	 */
	public static String[] splitByDash(String splitStr){
		return split( splitStr,SYMBOL_DASH);
	}
	
	/**
	 * 按","拆分splitStr为String数组
	 * @param splitStr
	 * @return
	 */
	public static String[] splitByComma(String splitStr){
		return split( splitStr,SYMBOL_COMMA);
	}
	
	/**
	 * 按":"拆分splitStr为String数组
	 * @param splitStr
	 * @return
	 */
	public static String[] splitByColon(String splitStr){
		return split( splitStr,SYMBOL_COLON);
	}
	
	/**
	 * 按regex拆分splitStr为String数组
	 * @param splitStr
	 * @param regex
	 * @return
	 */
	public static String[] split(String splitStr, String regex){
		if(StringUtil.isBlank(splitStr)){
			return null;
		}
		return splitStr.split(regex);
	}
	
	/**
	 * 按','拆分splitStr为String数组
	 * @param splitStr
	 * @return
	 */
	public static String[] split(String splitStr){
		return split(splitStr,SYMBOL_COMMA);
	}
	
	/**
	 * 按'|'拆分splitStr为String数组
	 * @param splitStr
	 * @return
	 */
	public static String[] splitByVerticalLine(String splitStr){
		return split(splitStr,"\\|");
	}
	
	
	/**
	 * 按','拆分splitStr为List
	 * @param splitStr
	 * @return
	 */
	public static List<String> splitToList(String splitStr){
		List<String> list ;
		if(StringUtil.isNotBlank(splitStr)){
			list = Arrays.asList(split(splitStr))  ;
		}else{
			list = new ArrayList<String>();
		}
		return list;
	}
	
	/**
	 * 获取异常堆栈信息字符串
	 * @param ex
	 * @return
	 */
	public static String getExceptionStackTrace(Throwable ex){
		if(ex==null){
			return null;
		}
		StringWriter sw = null;
		PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw =  new PrintWriter(sw);
            ex.printStackTrace(pw);
            pw.flush();
            sw.flush();
            return sw.toString();
        } finally {
        	IOUtils.closeQuietly(sw);
        	IOUtils.closeQuietly(pw);
        }
	}
	/**
	 * 将对象中为null的String字段替换为空字符串
	 * @param object
	 * @throws Exception
	 */
	public static void fillNullField(Object obj){
		if(obj==null){
			return;
		}
		Class clazz=obj.getClass();
		Field[] fields=clazz.getDeclaredFields();
		try {
			for(Field field:fields){
				Class fieldClass=field.getType();
				field.setAccessible(true);
				if(fieldClass==String.class){
					String fieldVal=(String) field.get(obj);
					if(fieldVal==null){
						field.set(obj, StringUtils.EMPTY);
					}
				}else if(fieldClass==List.class){
					List children=(List) field.get(obj);
					if(children!=null&&children.size()>0){
						for(Object child:children){
							fillNullField(child);
						}
					}
				}
			}
		} catch (SecurityException e) {
			throw new RuntimeException("SecurityException!", e);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException("IllegalArgumentException!", e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException("IllegalAccessException!", e);
		}
	}
}
