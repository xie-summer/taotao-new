package com.taotao.utils;

import java.math.BigDecimal;

/**
 * BigDecimal 数据操作
 * @author Shiwei.Xiao
 *
 */
public class BigDecimalUtil {
	
	/**
	 * bigDecimal1 subtract bigDecimal2
	 * @param bigDecimal1
	 * @param bigDecimal2
	 * @return
	 */
	public static String subtract(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		
		return ((bigDecimal1==null? BigDecimal.ZERO:bigDecimal1).subtract(bigDecimal2==null? BigDecimal.ZERO:bigDecimal2)).toPlainString();
	}
	
	public static BigDecimal subtractBigDecimal(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		
		return ((bigDecimal1==null? BigDecimal.ZERO:bigDecimal1).subtract(bigDecimal2==null? BigDecimal.ZERO:bigDecimal2));
	}
	
	public static String add(BigDecimal bigDecimal1, BigDecimal bigDecimal2){
		return ((bigDecimal1==null? BigDecimal.ZERO:bigDecimal1).add(bigDecimal2==null? BigDecimal.ZERO:bigDecimal2)).toPlainString();
	}
	
	public static String add(BigDecimal bigDecimal1, String bigDecimal2){
		return ((bigDecimal1==null? BigDecimal.ZERO:bigDecimal1).add(bigDecimal2==null? BigDecimal.ZERO:new BigDecimal(bigDecimal2))).toPlainString();
	}
	
	public static String add(String bigDecimal1, BigDecimal bigDecimal2){
		return ((bigDecimal1==null? BigDecimal.ZERO:new BigDecimal(bigDecimal1)).add(bigDecimal2==null? BigDecimal.ZERO:bigDecimal2)).toPlainString();
	}
	
	public static BigDecimal addToBigDecimal(BigDecimal... bigDecimals ){
		BigDecimal result= BigDecimal.ZERO;
		if(bigDecimals!=null && bigDecimals.length>0){
			for (BigDecimal bigDecimal : bigDecimals) {
				result=result.add((bigDecimal==null? BigDecimal.ZERO:bigDecimal));
			}
		}

		return result;
	}
	
	public static String add(String str1, String str2){
		return (new BigDecimal(str1==null?"0":str1).add(new BigDecimal(str2==null?"0":str2))).toPlainString();
	}
	
	public static String getString(BigDecimal bigDecimal){
		if(bigDecimal == null){
			return null;
		}
		return StringUtil.toString(bigDecimal);
	}
	/****************************************************/
	private BigDecimal totalBigDecimal =  BigDecimal.ZERO;
	public static BigDecimalUtil getBigDecimalUtil(){
		return new BigDecimalUtil();
	}
	
	public BigDecimalUtil add(BigDecimal bigDecimal){
		if(bigDecimal != null){
			totalBigDecimal.add(bigDecimal);
		}
		return this;
	}
	
	public String toString(){
		return StringUtil.toString(totalBigDecimal);
	}
	
	public static BigDecimal addBigDecimal(String str, String str1){
		BigDecimal b1=null;
		BigDecimal b2=null;
		if(str!=null){
			 b1 = new BigDecimal(str);
		}else{
			b1= BigDecimal.ZERO;
		}
		if(str1!=null){
			b2= new BigDecimal(str1);
		}else{
			b2= BigDecimal.ZERO;
		}
		return b1.add(b2);
	}
	

	
	public static BigDecimal addDouble(Double str, Double str1){
		BigDecimal b1=null;
		BigDecimal b2=null;
		if(str!=null){
			 b1 = new BigDecimal(str.toString());
		}else{
			b1= BigDecimal.ZERO;
		}
		if(str1!=null){
			b2= new BigDecimal(str1.toString());
		}else{
			b2= BigDecimal.ZERO;
		}
		
		return b1.add(b2);
	}
	
	public static BigDecimal max(BigDecimal val1, BigDecimal val2){
		if(val1.compareTo(val2)>=0){
			return val1;
		}
		return val2;
	}
	
	public static BigDecimal min(BigDecimal val1, BigDecimal val2){
		if(val1.compareTo(val2)<0){
			return val1;
		}
		return val2;
	}
}
