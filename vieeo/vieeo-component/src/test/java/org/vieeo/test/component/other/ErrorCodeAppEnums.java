package org.vieeo.test.component.other;


/**
 * @title 盛大卡支付应用服务错误编码枚举常量
 * @description
 * @copyright Copyright 2011 SDP Corporation. All rights reserved.
 * @author MengHongtao <menghongtao@snda.com>
 * @version Id: ErrorCodeAppEnums.java,2011-5-3 下午03:57:47
 */
public enum ErrorCodeAppEnums {

	/*---------------------------payment service-CARD 公共枚举常量----------------------------*/

	/**
	 * 未知异常信息
	 */
	ER_PAYMENTSERVICE_CARD_APP_UNKNOW( "A0513000", "", "未知异常!"),
	/**
	 * 渠道未返回充值结果!
	 */
	ER_PAYMENTSERVICE_CARD_APP_NORETURN( "A0513001", "", "渠道未返回充值结果!"),

	/**
	 * 系统不支持该组卡型
	 */
	ER_PAYMENTSERVICE_CARD_CARDTYPE_NOSUPPORT( "A0513002", "", "系统不支持该组卡型!"),

	/**
     * 系统不支持该组卡型
     */
    ER_PAYMENTSERVICE_CARD_BLANCE_NO_ENOUTH( "A0513003", "", "余额不足!"),

    /**
     * 系统不支持该组卡型
     */
    ER_PAYMENTSERVICE_CARD_DUPCARD( "A0513004", "", "重复卡号!"),

    /**
     * 系统不支持该组卡型
     */
    ER_PAYMENTSERVICE_CARD_TOOMANY( "A0513005", "", "组合不能超过三张卡!"),

    ER_PAYMENTSERVICE_CARD_NOMONEY( "A0513006", "", "卡账户金额为零!"),

    ER_PAYMENTSERVICE_CARD_LOCKED( "A0513007", "", "卡账户已被锁定!"),

    ER_PAYMENTSERVICE_CARD_INACTIVE( "A0513008", "", "卡账户异常!"),

    /**
     * 界面显示支付失败<br>
     * 由于四舍五入出现分的误差
     */
    ER_PAYMENTSERVICE_CARD_BLANCE_INACCURACY( "A0513009", "", "超过误差精度!"),


    NOTFOUND_ACQUIREBATCH( "A0513010", "", "继续支付批次不存在!"),

    NOTMATCH_ACQUIREBATCH( "A0513011", "", "继续支付批次与支付订单号不匹配!"),

    ACQUIREBATCH_LIMIT_ERROR( "A0513012", "", "继续支付失败,请稍候重试!"),

    ACQUIREBATCH_OVERTIME( "A0513013", "", "继续支付订单已过期,请重新发起支付!"),

	;
	/**
	 * 常量值
	 */
	private final String value;

	/**
	 * APP错误代码
	 */
	private final String appCode;

	/**
	 * 常量描述
	 */
	private final String desc;

	/**
	 * get "value" (type : String)
	 */
	public String getValue() {
		return value;
	}

	/**
	 * get "appCode" (type : String)
	 */
	public String getAppCode() {
		return appCode;
	}

	/**
	 * get "desc" (type : String)
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * 根据常量值获取常量描述
	 *
	 * @param value : 常量值
	 * @return String
	 */
	public static String getDescByValue(String value) {
		// 获取所有的错误代码
		ErrorCodeAppEnums[] enums = ErrorCodeAppEnums.values();

		// 如果没有直接返回 null
		if (enums == null || enums.length == 0) {
			return null;
		}

		// 根据参数 'value' 遍历,查找符合条件的 'desc'
		for (ErrorCodeAppEnums errorEnums : enums) {
			if (errorEnums.getValue().equals(value)) {
				return errorEnums.getDesc();
			}
		}

		return null;
	}

	/**
	 * 根据appCode获取value
	 *
	 * @param appCode : APP有错误编码
	 * @return String
	 */
	public static String getValueByAppCode(String appCode) {
		// 获取所有的错误代码
		ErrorCodeAppEnums[] enums = ErrorCodeAppEnums.values();

		// 如果没有直接返回 null
		if (enums == null || enums.length == 0) {
			return null;
		}

		// 根据参数 'appCode' 遍历,查找符合条件的 'value'
		for (ErrorCodeAppEnums errorEnums : enums) {
			if (errorEnums.getAppCode().equals(appCode)) {
				return errorEnums.getValue();
			}
		}

		return ER_PAYMENTSERVICE_CARD_APP_UNKNOW.getValue();
	}

	/**
	 * 基本构造方法
	 *
	 * @param value : 错误代码
	 * @param appCode : APP错误代码
	 * @param desc : 错误信息
	 */
	private ErrorCodeAppEnums(String value, String appCode, String msg) {
		this.value = value;
		this.appCode = appCode;
		this.desc = msg;
	}
}
