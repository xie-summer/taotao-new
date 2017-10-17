package com.taotao.utils;


//import com.cpic.caf.core.utils.StringUtil;

public class IntegerUtil {
	
	public static String getString(Integer var){
		if(var == null){
			return null;
		}
		return StringUtil.toString(var);
	}
	
	private Integer totalInteger =  Integer.valueOf("0");
	
	public static IntegerUtil getIntegerUtil(){
		return new IntegerUtil();
	}
	/**
	 * Integer 
	 * @param var
	 * @return
	 */
	public IntegerUtil add(Integer var){
		if(var != null){
			totalInteger += var;
		}
		return this;
	}
	
	/**
	 * Integer 
	 * @param var
	 * @return
	 */
	public IntegerUtil add(String var){
		return add(StringUtil.isNotBlank(var)? Integer.valueOf(var):null);
	}
	
	/**
	 * return total
	 * @return
	 */
	public Integer getTotalInteger(){
		return totalInteger;
	}
	
	@Override
    public String toString(){
		return StringUtil.toString(totalInteger);
	}

//	public static void main(String[] args) {
//
//	}

}
