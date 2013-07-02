package com.vieeo.util.number;

import java.math.BigDecimal;
import java.math.MathContext;

import org.apache.commons.lang.StringUtils;

public class NumberUtils {

	
	/**
	 * 返回一个指定精度和舍入模式的BigDecimal
	 * @param value      BigDecimal的值
	 * @param precision  小数位
	 * @param round		 舍入模式
	 * @return
	 */
	public static BigDecimal getDecimalByPrecision(double value,int precision,int round) {
		BigDecimal result = new BigDecimal(value);
		result = result.setScale(precision,round);
		return result;
	}

	public static BigDecimal getDecimalByAbs(double value,int abs) {
		return new BigDecimal(value).abs(new MathContext(abs));
	}

	public static Integer parseInteger(String value) {
		return StringUtils.isBlank(value) ? null :Integer.parseInt(value);
	}
	
	/**
	 * 返回指定BigDecimal的小数部分
	 * @param value  	要返回小数部分的value
	 * @param scale		截取小数的精度
	 * @param divideNum 截取小数后需要除的100的平方数
	 * @return
	 */
	public static double getScaleValue(BigDecimal value,int scale,int divideNum){
		if(value == null) return 0;
		value = value.setScale(scale, BigDecimal.ROUND_HALF_DOWN);
		String valueStr = value.toString();
		int index = StringUtils.indexOf(valueStr, ".");
		if(index <=0 ) return 0;
		index = index+1;
		valueStr = StringUtils.substring(valueStr, index, index+scale);
		double precent = Double.valueOf(valueStr);
		return precent/divideNum;
	}
	
	public static void main(String[] args) {
		double result = getScaleValue(NumberUtils.getDecimalByPrecision(20.9, 3, BigDecimal.ROUND_HALF_DOWN),2,100);
		BigDecimal minute = NumberUtils.getDecimalByPrecision(60, 2, BigDecimal.ROUND_HALF_DOWN);
		int minutes = minute.multiply(new BigDecimal(result)).intValue();
		System.out.println(minutes);
	}
}
