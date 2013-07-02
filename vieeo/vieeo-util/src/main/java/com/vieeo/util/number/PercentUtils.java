package com.vieeo.util.number;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;

/**
 * 百分比计算utils类
 * @author hehy
 */
public class PercentUtils {
	
	private static NumberFormat percentFormat = NumberFormat.getPercentInstance();

	/**
	 * 转换一个百分比%字符串为double类型的值
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static double transformPercentToDouble(String value)throws ParseException{
		return percentFormat.parse(value).doubleValue();
	}

	/**
	 * 转换一个百分比0.00字符串为double类型的值
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static double transformStringToDouble(String value)throws ParseException{
		return NumberFormat.getInstance().parse(value).doubleValue();
	}

	/**
	 * 判断一个字符串是否为百分比
	 * @param value
	 * @return
	 * @throws ParseException
	 */
	public static boolean isPercent(String commision) {
		try {
			percentFormat.parse(commision).doubleValue();
			return true;
		} catch (ParseException e) {
		}
		return false;
	}
	
	public static String transformDoubleToString(double value){
		return percentFormat.format(value);
	}
	
	public static String transformDoubleToString(BigDecimal value){
		return percentFormat.format(value);
	}

}
