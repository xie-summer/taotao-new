package com.taotao.utils;

import java.io.Serializable;
import java.math.BigDecimal;

public class DoubleUtil implements Serializable {

	private static final long serialVersionUID = 5869754693429128435L;
	
	/**
	 * 提供精确的小数位四舍五入处理。
	 * @param value 需要四舍五入的数字
	 * @param scale 小数点后保留几位
	 * @return 四舍五入后的结果
	 */
    public static Double round(Double value, Integer scale) {
        if (scale < 0) {  
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");  
        }  
        BigDecimal b = new BigDecimal(Double.toString(value));
        BigDecimal one = BigDecimal.ONE;
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    } 
    
    public static String getString(Double var){
    	return StringUtil.toString(var);
    }
	/********************************************/
	private Double totalDouble = Double.valueOf(0.0d);
	public static DoubleUtil getDoubleUtil(){
		return new DoubleUtil();
	}
	
	public DoubleUtil add(Double var){
		if(var != null){
			totalDouble += var;
		}
		return this;
	}
	
	public DoubleUtil add(String var){
		return add(StringUtil.isNotBlank(var)? Double.valueOf(var):null);
	}
	
	public DoubleUtil add(BigDecimal var){
		return add(var == null?null:var.doubleValue());
	}
	
	   /** 
     * double 相减 
     * @param d1 
     * @param d2 
     * @return 
     */ 
    public double sub(double d1,double d2){ 
        BigDecimal bd1 = new BigDecimal(Double.toString(d1));
        BigDecimal bd2 = new BigDecimal(Double.toString(d2));
        return bd1.subtract(bd2).doubleValue(); 
    } 
	
	public Double getTotalDouble(){
		return totalDouble;
	}
	
	public String toString(){
		return StringUtil.toString(totalDouble);
	}

}
