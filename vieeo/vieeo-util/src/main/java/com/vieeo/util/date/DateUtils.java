package com.vieeo.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * 日期转换助手类
 * @author roy.he
 *
 */
public class DateUtils {
	
	public static final String PATTERN_YYYYMMDD="yyyy-MM-dd";
	public static final String PATTERN_YYYYMM = "yyyy-MM";
	public static final String PATTERN_YYYY="yyyy";
	public static final String PATTERN_F = "F";
	public static final String PATTERN_YYYYMMDD_HHMMSS="yyyy-MM-dd HH:mm:ss";
	
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_YYYYMMDD);
	
	private static SimpleDateFormat datetimeFormat = new SimpleDateFormat(PATTERN_YYYYMMDD_HHMMSS);
	
	public static String formatDate(Date date) {
		if(date==null) return "";
		try {
			return dateFormat.format(date);
		}catch(Exception e) {
			return "";
		}
	}
	
	public static String formatDateTime(Date date) {
		if(date==null) return "";
		try {
			return datetimeFormat.format(date);
		}catch(Exception e) {
			return "";
		}
	}
	
	public static Date parseDate(String value){
		if(StringUtils.isBlank(value)) return null;
		try {
			return dateFormat.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static Date parseDateTime(String value){
		if(StringUtils.isBlank(value)) return null;
		try {
			return datetimeFormat.parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static String format(Date date,String pattern){
		if(date == null || StringUtils.isBlank(pattern)) return "";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.format(date);
		}catch(Exception e) {
			return "";
		}
	}
	
	public static Calendar formatCalendar(Date date){
		if(date == null) return null;
		Calendar can = Calendar.getInstance();
		can.setTime(date);
		return can;
	}
	
	public static Date parse(String value,String pattern){
		if(StringUtils.isBlank(value) || StringUtils.isBlank(pattern)) return null;
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		try {
			return format.parse(value);
		}catch(ParseException e) {
			return null;
		}
	}
	
	/**
	 * 得到开始结束时间相差月份中每个月份实际最大天数与月份最大天数之间的相差天数
	 * 31 - 28（2月） = 3
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getExtraDays(Calendar startDate,Calendar endDate){
		//得到相差月份
		int months = (endDate.get(Calendar.YEAR) - startDate.get(Calendar.YEAR))* 12+ endDate.get(Calendar.MONTH)- startDate.get(Calendar.MONTH);  
		Calendar cdate = Calendar.getInstance();
		cdate.setTime(startDate.getTime());
		int extraDays = 0;
		for (int i = 0; i < months; i++) {
			if(i != 0) cdate.add(Calendar.MONTH, 1);
			extraDays = extraDays + (31-cdate.getActualMaximum(Calendar.DAY_OF_MONTH));
		}
		return extraDays;
	}
	
	/**
	 * 计算2个日期相差的分钟数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getExtraMinutes(Calendar startDate,Calendar endDate){
		if(startDate == null || endDate == null) return -1;
		long diff = endDate.getTime().getTime()-startDate.getTime().getTime(); 
		return diff/(1000*60);
	}
	
	/**
	 * 在指定时间参数上相加或想减值
	 * @param date
	 * @param value
	 * @param field
	 * @return
	 */
	public static Date add(Date date,int value,int field){
		if(date == null) return null;
		Calendar can = Calendar.getInstance();
		can.setTime(date);
		can.add(field, value);
		return can.getTime();
	}
	
	/**
	 * 在指定时间参数上重新设置一个值
	 * @param date
	 * @param value
	 * @param field
	 * @return
	 */
	public static Date set(Date date,int value,int field){
		if(date == null) return null;
		Calendar can = Calendar.getInstance();
		can.setTime(date);
		can.set(field, value);
		return can.getTime();
	}
	
	/**
	 * 获取指定字段的值
	 * @param date
	 * @param field
	 * @return
	 */
	public static int get(Date date,int field){
		if(date == null) return -1;
		Calendar can = Calendar.getInstance();
		can.setTime(date);
		return can.get(field);
	}
	
	public static void main(String[] args) {
		long minutes = getExtraMinutes(DateUtils.formatCalendar(DateUtils.parseDateTime("2011-10-01 08:30:00")), 
				DateUtils.formatCalendar(DateUtils.parseDateTime("2011-10-01 08:35:00")));
		System.out.println(minutes);
	}
}
