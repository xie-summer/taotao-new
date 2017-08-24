package com.taotao.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 日期工具类
 * 
 * @author zhangxiang-030
 * 
 */
public class DateFormatUtils {
	private static Logger logger = LoggerFactory.getLogger(DateFormatUtils.class);
	private static final String defaultDatePattern = "yyyy-MM-dd HH:mm:ss";
	private static final String shortDatePattern = "yyyy-MM-dd";
	private static final String simpleDatePattern="yyyyMMdd";
//	private static final String hourDatePattern = "yyyy-MM-dd HH";
	private static final String simpleHourDatePattern = "yyyyMMddHH";
	private static final String simpleMinuteDatePattern = "yyyyMMddHHmm";
	private static final String simpleSecDatePattern = "yyyyMMddHHmmss";

	/**
	 * 从Date中只取日期，时间清零
	 * 
	 * @param date
	 * @return
	 */
	public static Date getDate(Date date) {
		if (date == null) {
			return date;
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date returnDate = null;
		try {
			returnDate = dateFormat.parse(dateFormat.format(date));
		} catch (ParseException e) {
			logger.error("getDate error ", e);
		}
		return returnDate;
	}

	/**
	 * 通过日期差获取响应的日期
	 * 
	 * @param date
	 * @param Dvalue
	 *            日期差
	 * @return
	 */
	public static Date addDateByDvalue(Date date, int dValue) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, dValue);
		return calendar.getTime();
	}

	/**
	 * 通过日期差获取响应的日期
	 * 
	 * @param date
	 * @param Dvalue
	 *            日期差
	 * @return
	 */
	public static Date addHourByDvalue(Date date, int dValue) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, dValue);
		return calendar.getTime();
	}

	/**
	 * 通过日期差获取响应的日期
	 * 
	 * @param date
	 * @param Dvalue
	 *            日期差（例如明天为1，昨天为-1）
	 * @return
	 */
	public static Date addMinuteByDvalue(Date date, int dValue) {
		if (date == null) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MINUTE, dValue);
		return calendar.getTime();
	}
	
	/**
	 * 将日期转化成指定格式 yyyy-MM-dd
	 * @param date
	 * @param pattern yyyy-MM-dd
	 * @return
	 */
	public static String parseDateToShortDateString(Date date) {
		return parseDateToString(date,shortDatePattern);
	}
	
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseShortDateStringToDate(String dateString){
		return parseStringToDate(dateString,shortDatePattern);
	}
	
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseSimpleMinuteStringToDate(String dateString){
		return parseStringToDate(dateString,simpleMinuteDatePattern);
	}
	
	/**
	 * 将日期转化成指定格式 yyyyMMdd
	 * @param date
	 * @param pattern yyyyMMdd
	 * @return
	 */
	public static String parseDateToSimpleDateString(Date date) {
		return parseDateToString(date,simpleDatePattern);
	}
	
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseSimpleDateStringToDate(String dateString){
		return parseStringToDate(dateString,simpleDatePattern);
	}
	
	/**
	 * 
	 * @param dateString
	 * @return
	 */
	public static Date parseDefaultDateStringToDate(String dateString){
		return parseStringToDate(dateString,defaultDatePattern);
	}
	
	/**
	 * 将日期转化成指定格式 yyyyMMddHH
	 * 
	 * @param date
	 * @param pattern
	 *            yyyyMMddHH
	 * @return
	 */
	public static String parseDateToSimpleHourString(Date date) {
		return parseDateToString(date, simpleHourDatePattern);
	}

	/**
	 * 将日期转化成指定格式 yyyyMMddHHmm
	 * 
	 * @param date
	 * @param pattern
	 *            yyyyMMddHHmm
	 * @return
	 */
	public static String parseDateToSimpleMinuteString(Date date) {
		return parseDateToString(date, simpleMinuteDatePattern);
	}

	/**
	 * 将日期转化成指定格式 yyyyMMddHHmmss
	 * 
	 * @param date
	 * @param pattern
	 *            yyyyMMddHHmmss
	 * @return
	 */
	public static String parseDateToSimpleSecString(Date date) {
		return parseDateToString(date, simpleSecDatePattern);
	}
	
	/**
	 * 将yyyyMMddHHmmss的字符串转化成日期
	 * 
	 * @param str yyyyMMddHHmmss
	 * @return
	 */
	public static Date parseSimpleSecStringToDate(String str) {
		return parseStringToDate(str, simpleSecDatePattern);
	}

	/**
	 * 将日期转化成指定格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param date
	 * @param pattern
	 *            yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static String parseDateToDefaultDateString(Date date) {
		return parseDateToString(date, defaultDatePattern);
	}

	/**
	 * 将日期转化成指定格式
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String parseDateToString(Date date, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		if (date != null) {
			return format.format(date);
		}
		return null;
	}

	/**
	 * date与XMLGregorianCalendar 的格式转换
	 * 
	 * @param date
	 * @return XMLGregorianCalendar
	 * */
	public static XMLGregorianCalendar convertToXMLGregorianCalendar(Date date) {
		return convertToXMLCalendar(date, null);
	}

	/**
	 * date与XMLGregorianCalendar 的格式转换 精确到小时
	 * 
	 * @param date
	 * @return XMLGregorianCalendar
	 * */
	public static XMLGregorianCalendar convertToHourXMLCalendar(Date date) {
		return convertToXMLCalendar(date, simpleHourDatePattern);
	}

	/**
	 * date与XMLGregorianCalendar 的格式转换 精确到秒
	 * 
	 * @param date
	 * @return XMLGregorianCalendar
	 * */
	public static XMLGregorianCalendar convertToSecondXMLCalendar(Date date) {
		return convertToXMLCalendar(date, simpleSecDatePattern);
	}

	/**
	 * date与XMLGregorianCalendar 的格式转换
	 * 
	 * @param date
	 * @return XMLGregorianCalendar
	 * */
	public static XMLGregorianCalendar convertToXMLCalendar(Date date, String dateFormat) {
		if (date == null) {
			return null;
		}
		GregorianCalendar cal = new GregorianCalendar();
		if (StringUtil.isNotBlank(dateFormat)) {
			DateFormat format = new SimpleDateFormat(dateFormat);
			try {
				cal.setTime(format.parse(format.format(date)));
			} catch (ParseException e) {
				logger.error("convertToXMLCalendar error ", e);
			}
		} else {
			cal.setTime(date);
		}
		XMLGregorianCalendar gc = null;
		try {
			gc = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
		} catch (Exception e) {
			logger.error("convertToXMLCalendar error ", e);
		}
		return gc;
	}

	/***
	 * XMLGregorianCalendar 改date
	 * 
	 * @param cal
	 * @return
	 * @throws Exception
	 */
	public static Date convertToDate(XMLGregorianCalendar cal) {
		if(cal!=null){
			GregorianCalendar ca = cal.toGregorianCalendar();
			return ca.getTime();
		}else{
			return null;
		}
	
		
	}

	public static Date parseStringToDate(String dateString, String pattern) {
		DateFormat format = new SimpleDateFormat(pattern);
		if (StringUtil.isNotBlank(dateString)) {
			try {
				return format.parse(dateString);
			} catch (ParseException e) {
				logger.error("parseStringToDate error ", e);
			}
		}
		return null;
	}

	/**
	 * 计算两日期月差多余天数不算一个月（采用a-b方式）
	 * 
	 * @param a
	 *            大日期 如：2014年7月16
	 * @param b
	 *            小日期 如：2013年7月16
	 * @return 计算出月差为12个月即返回12
	 */
	public static int getMonthsBetween(Date a, Date b) {
		if (a == null || b == null) {
			return 0;
		}
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(a);
		calendar2.setTime(b);
		int year = calendar1.get(Calendar.YEAR) - calendar2.get(Calendar.YEAR);
		int month = calendar1.get(Calendar.MONTH) - calendar2.get(Calendar.MONTH);
		return year * 12 + month;
	}

	/**
	 * 计算两日期差天数（采用a-b方式）
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getDaysBetween(Date a, Date b) {
		if (a == null || b == null) {
			return 0;
		}
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(a);
		calendar2.setTime(b);
		long mSeconds = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
		int days = (int) (mSeconds / (1000L * 60L * 60L * 24L));
		return days;
	}

	/**
	 * 计算两日期差天数,超一天,多算一天（采用a-b方式）
	 * 
	 * @param a
	 * @param b
	 * @return
	 */
	public static int getDaysBetweenPreciseHour(Date a, Date b) {
		if (a == null || b == null) {
			return 0;
		}
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(a);
		calendar2.setTime(b);
		long mSeconds = calendar1.getTimeInMillis() - calendar2.getTimeInMillis();
		int days = (int) (mSeconds / (1000L * 60L * 60L * 24L));
		// 超一天,多算一天
		if ((mSeconds % (1000L * 60L * 60L * 24L)) != 0) {
			return days + 1;
		}
		return days;
	}
	
	/**
	 * 判断两个时间是否为同一天
	 * @param a
	 * @param b
	 * @return
	 */
	public static boolean isSameTime(Date a, Date b, int field){
		if(a == null || b == null){
		  return false;
		}
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(a);
		calendar2.setTime(b);
		if(calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR) &&
		   calendar1.get(field) == calendar2.get(field)){
			 return true;
		}
		return false;
	}
	
	/**
	 * 比较两个时间的大小，如果date1不小于date2则返回true，否则返回false
	 * 
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static boolean compareDate(Date date1, Date date2){
		if(date2==null){
			return true;
		}
		if(date1==null){
			return false;
		}
		if(date1.after(date2)){
			return true;
		}
		return false;
	}
	/**
	 * 判断两个时间是否不超过一个时间阀值
	 * 例如：不超过一个小时 field为、amount为1
	 * @param a
	 * @param b
	 * @param field
	 * @param number
	 * @return 不超过返回true，超过返回false
	 */
	public static boolean isNotPass(Date a, Date b, int field, int amount){
		if(a == null || b == null){
		  return false;
		}
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		calendar1.setTime(a);
		calendar2.setTime(b);
		if(calendar1.getTimeInMillis() <= calendar2.getTimeInMillis()){
			calendar1.add(field, amount);
			if(calendar1.getTimeInMillis() >= calendar2.getTimeInMillis()){
				return true;
			}
		}
		if(calendar1.getTimeInMillis() >= calendar2.getTimeInMillis()){
			calendar2.add(field, amount);
			if(calendar1.getTimeInMillis() <= calendar2.getTimeInMillis()){
				return true;
			}
		}
		return false;
	}
	
	public static String toLongStr(Calendar c) {
		if (c==null) 
			return "";
		
		String s = toShortStr(c);
		
		int hour = c.get(Calendar.HOUR_OF_DAY);
		int minute = c.get(Calendar.MINUTE);
		int second = c.get(Calendar.SECOND);
		
		s += " ";
		s += paddingZero(hour);
		s += ":";
		s += paddingZero(minute);
		s += ":";
		s += paddingZero(second);
		
		return s;
	}
	
	public static String toShortStr(Calendar c) {
		if (c==null)
			return "";
		
		StringBuffer sb = new StringBuffer();
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DAY_OF_MONTH);
		
		sb.append(year)
		.append("-")
		.append(paddingZero(month))
		.append("-")
		.append(paddingZero(date));
		
		return sb.toString();
	}
	
	private static String paddingZero(int num) {
		if (num<10)
			return "0" + num;
		
		return "" + num;
	}
	
	/**
	 * 把精确到天的日期设置成该天的23:59:59
	 * @param date
	 * @return
	 */
	public static Date setDayTime(Date date){
		if(date == null){
			return null;
		}
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		calendar.set(year, month, day, 23, 59, 59);
		date = calendar.getTime();
		return date;
	}
	
	/***
	 * 日期加减操作
	 * @param date
	 * @param pattern 格式
	 * amount为正则往后,为负则往前 
	   field取1加1年,取2加半年,取3加一季度,取4加一周 
	      取5加一天
	 */
	public static Date HandlerDate(Date date, int field, int amount){
		GregorianCalendar gc=new GregorianCalendar();
		gc.setTime(date);
		gc.add(field, amount);
		return gc.getTime();
	}
	/**
	 * 计算车龄 = 出险时间-登记日期(不满1个月不计算)
	 * @param accidentDate
	 * @param vehicleRegisterDate
	 * @return
	 */
	public static Integer calculateVehicleMonth(Date accidentDate, Date vehicleRegisterDate) {
		Calendar c1 = Calendar.getInstance();
		Calendar c2 = Calendar.getInstance();
		c1.setTime(accidentDate);
		c2.setTime(vehicleRegisterDate);
		Integer yearOfGap = c1.get(Calendar.YEAR) - c2.get(Calendar.YEAR);
		Integer monthOfGap = c1.get(Calendar.MONTH) - c2.get(Calendar.MONTH);
		Integer months = yearOfGap * 12 + monthOfGap; //相差的总共的月份
		if((c1.get(Calendar.DATE) - c2.get(Calendar.DATE)) < 0) {
			months = months - 1; //如果天数比登记日期小, 相差的月份-1
		}
		return months;
	}
}
