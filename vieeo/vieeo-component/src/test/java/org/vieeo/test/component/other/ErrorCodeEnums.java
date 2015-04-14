package org.vieeo.test.component.other;

public enum ErrorCodeEnums {

	SYSERR_UNKNOWN_ERROR("S0533001","系统未知错误","网络繁忙!"),
	SYSERR_CAST_ERROR("S0533002","对象转换错误","网络繁忙!"),
	SYSERR_UNSUPPORTED_PAY_MODE("S0533003","不支持的支付模式","不支持的支付模式"),

	//手机SDK系统级异常
	SYSERR_INVALIDATE_REQUEST("S0533008","tokenId不合法","tokenId不合法"),
	SYSERR_SESSION_EXPIRED("S0533009","session已失效","支付超时"),
	SYSERR_SESSION_EMPTY("S0533010","sessionID不能为空！","sessionID不能为空！"),
	SYSERR_INVALIDATE_EMPTY("S0533011","tokenId不能为空！","tokenId不能为空！"),

	//SDK业务级对外异常
	//公共信息异常
	BISNESS_COSTOMER_NULL("B053001","商户号不正确","商户号不正确"),
	BISNESS_ORDER_TYPE_NULL("B053002","定单类型不正确","定单类型不正确"),
	BISNESS_ORDER_AMOUNT_NULL("B053003","定单金额不正确","定单金额不正确"),
	BISNESS_NOT_LOGIN("B053004","用户未登录","用户未登录"),
	BISNESS_PAYMENT_TYPE_NULL("B053005","支付类型不正确","支付类型不正确"),
	BISNESS_INSTCODE_NULL("B053006","支付机构编码为空","支付机构编码为空"),
	BISNESS_PAYMENT_TOOLS_NULL("B053007","支付工具不存在","支付工具不存在"),
	BISNESS_PAY_CHANNEL_NULL("B053008","支付渠道不存在","支付渠道不存在"),
	BISNESS_GET_AGENCY_FAILED("B053009","支付机构不存在","支付机构不存在"),
	BISNESS_COMB_STRATEGY_ERROR("B053010","组合支付策略配置信息有误！","组合支付策略配置信息有误！"),
	BISNESS_ORDER_AMOUNT_NOT_MATCH("B053011","组合支付订单金额与应付金额不匹配！","组合支付订单金额与应付金额不匹配！"),
	BISNESS_COSTOMERORDERNO_NULL("B0530012","商户订单号不正确","商户订单号"),
	BUSNESS_ORDER_EXISTS("B0532534","订单不允许重复","订单不允许重复"),


	//check&rander
	BISNESS_NOT_ACTIVATE_BALANCE("B053010","您的通行证尚未激活钱包","您的通行证尚未激活钱包"),
	BISNESS_NOT_ACTIVATE_BALANCE_MOBILE("B053011","尚未绑定手机","尚未绑定手机"),
	BISNESS_NOT_SET_PAYMENT_PWD("B053012","尚未设置支付密码","尚未设置支付密码"),
	BISNESS_PAYMENT_PWD_LOCKED("B053013","支付密码已被锁定","支付密码已被锁定"),
	BISNESS_PAYMENT_PWD_ERROR("B0532433", "支付密码输入错误","支付密码输入错误"),



	//会员账户
	BISNESS_MA_ACCOUNT_INACTIVE("B053014","会员账户尚未激活","会员账户尚未激活"),
	BISNESS_MA_ACCOUNT_LOCKED("B053015","会员账户已被锁定","会员账户已被锁定"),
	BISNESS_MA_ACCOUNT_REFUSE_FUND_IN("B053016","会员账户不可用","会员账户不可用"),

	BISNESS_MA_ACCOUNT_NOT_EXITS("B053091","账户不存在","账户不存在"),

	BISNESS_CHECK_RENDER_NULL("B053017","CHECK_RENDER 错误","CHECK_RENDER 失败"),

	//登录信息
	BISNESS_LOGIN_TYPE_NULL("B053018","登录类型为空","登录类型为空"),
	BISNESS_LOGIN_NAME_NULL("B053019","用户名为空","用户名为空"),
	BISNESS_SIGN_MAG_NULL("B053020","签名信息不存在","签名信息不存在"),
	BISNESS_SIGN_ERROR("B053021","签名信息错误","签名信息错误"),
	BISNESS_LOGIN_PASSWORD_NULL("B053022","登录密码为空","登录密码为空"),
	BISNESS_LOGIN_FAIL("B053023","用户名或者密码错误","用户名或者密码错误"),
	BISNESS_LOGIN_USER_IP_NULL("B053024","用户IP地址为空","用户IP地址为空"),
	BISNESS_LOGIN_SECURITY_CODE_ERROR("B053025","密保不正确","密保不正确"),
	BISNESS_LOGIN_SECURITY_CARD_ERROR("B053026","密保卡不正确","密保卡不正确"),
	BISNESS_PT_NOT_FIND("B053027","用户PT账号未找到","用户PT账号未找到"),

	BISNESS_PT_DISAFFINITY("B053028","登录用户与订单信息用户不匹配","登录用户与订单信息用户不匹配"),
	BISNESS_HUAYOU_SYS_ERROR("B053029","华友登录返回错误","华友登录返回错误"),
	BISNESS_NO_BIND_BANK("B053030","未绑定银行","您还没有开通一点充，请通过绑定银行卡、手机轻松开通。"),


	//支付请求check
	BISNESS_REQUEST_ILLEGAL("B053040","请求参数不正确","请求参数不正确"),
	BISNESS_PAYMENTITEM_ILLEGAL("B053041","付款机构[PAYMENT_ITEM]信息不正确","付款机构信息不正确"),
	BISNESS_TRANSNO_ILLEGAL("B053042","网关交易号不正确","网关交易号不正确"),
	BISNESS_PAYABLE_AMOUNT_ILLEGAL("B053043","应付金额不正确","应付金额不正确"),
	BISNESS_PAYABLE_FEE_ILLEGAL("B053044","应付手续费不正确","应付手续费不正确"),
	BISNESS_RECEIVABLE_AMOUNT_ILLEGAL("B053045","应收金额不正确","应收金额不正确"),
	BISNESS_RECEIVABLE_FEE_ILLEGAL("B053046","应收手续费不正确","应收手续费不正确"),
	BISNESS_PAYER_IP_ILLEGAL("B053047","付款用户IP不正确","付款用户IP不正确"),
	BISNESS_PAY_PASSWORD_ILLEGAL("B053048","支付密码不正确","支付密码不正确"),
	BISNESS_SDCARD_CARD_INFO_ILLEGAL("B053049","卡信息不正确","卡信息不正确"),
	BISNESS_MBCARD_CARD_AMOUNT_ILLEGAL("B053050","卡金额不正确","卡金额不正确"),
	BISNESS_MBCARD_CARD_NO_ILLEGAL("B053051","充值卡卡号不正确","充值卡卡号不正确"),
	BISNESS_MBCARD_CARD_PASSWORD_ILLEGAL("B053052","充值卡卡密不正确","充值卡卡密不正确"),
	BISNESS_CER_KEY_ILLEGAL("B053053","签名密钥不正确","签名密钥不正确"),
	BISNESS_BANK_CARD_NO_ILLEGAL("B053054","银行卡信息不正确","银行卡信息不正确"),
	BISNESS_ID_CARD_NO_ILLEGAL("B053055","身份证号不正确","身份证号不正确"),
	BISNESS_PAYER_NAME_ILLEGAL("B053056","用户姓名不正确","用户姓名不正确"),
	BISNESS_ID_TYPE_ILLEGAL("B053057","证件类型不正确","证件类型不正确"),
	BISNESS_ID_NO_ILLEGAL("B053058","证件号码不正确","证件号码不正确"),
	BISNESS_VALID_DATE_ILLEGAL("B053059","有效期不正确","有效期不正确"),
	BISNESS_CVV2_ILLEGAL("B053060","CVV2不正确","CVV2不正确"),
	BISNESS_MOBILE_NULL("B053061","手机号不正确","手机号不正确"),
	BISNESS_SMS_CODE_ILLEGAL("B053062","短信验证码不正确","短信验证码不正确"),
	BISNESS_NOTIFY_PAGE_URL_ILLEGAL("B053063","网银支付回调地址为空","网银支付回调地址为空"),
	BISNESS_PT_ID_NULL("B053064","用户PT账号为空","用户PT账号为空"),
	BISNESS_PAYMENT_BIS_ERROR("B053065","支付异常","支付异常"),
	BISNESS_ENCRYPTED_KEY_ILLEGAL("B053066","RSA加密随机KEY为空","RSA加密随机KEY为空"),
	BISNESS_ENCRYPT_TYPE_ILLEGAL("B053067","加密方式为空","加密方式为空"),
	BISNESS_CER_SERIAL_NO_ILLEGAL("B053068","加密所使用的证书的序列号为空","加密所使用的证书的序列号为空"),
	BISNESS_AGENCY_IS_EMPTY("B053069","无可用的支付机构","无可用的支付机构"),
	BISNESS_MBCARD_CARD_NO_LENGTH_ERROR("B053070","充值卡卡号长度不正确[10-19]位","充值卡卡号长度不正确[10-19]位"),
	BISNESS_MBCARD_CARD_PASSWORD_LENGTH_ERROR("B053071","充值卡卡密长度不正确[8-21]位","充值卡卡密长度不正确[8-21]位"),
	BISNESS_DIRECT_RECEIVEANDPAY_NOTSUPPORTED_PAYMENTTYPE("B053073","收单充值API统一接口暂不支持该种支付方式！","收单充值API统一接口暂不支持该种支付方式！"),
	BISNESS_ORDER_PAYING("B053074","订单已在支付处理中","订单已在支付处理中"),


	//二维码查询
	BISNESS_QRCODE_TRANS_NO_ILLEGAL("B053072","盛付通交易号不能为空","盛付通交易号不能为空"),

	//银行卡代扣校验异常
	ORDER_NO_NULL("B053080","商户订单号不能为空","商户订单号不能为空"),
	ORDER_TIME_ILLEGAL("B053081","订单时间格式错误,正确格式：yyyyMMddHHmmss","订单时间格式错误,正确格式：yyyyMMddHHmmss"),
	CURRENCY_ILLEGAL("B053082","货币类型错误","货币类型错误"),

	ACQUIRE_ORDER_NO_NULL("B053083","订单号不存在","订单号不存在"),

	ACQUIRE_ORDER_AMOUNT_INVALIDATE("S0505078", "订单金额必须为整数", "订单金额必须为整数"),
	;

	private String code;
	private String name;
	private String displayName;

	ErrorCodeEnums(String code, String name,String displayName) {
		this.code = code;
		this.name = name;
		this.displayName = displayName;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}


	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static ErrorCodeEnums getByCode(String code) {
		for (ErrorCodeEnums errorType : ErrorCodeEnums.values()) {
			if (errorType.getCode().equals(code)) {
				return errorType;
			}
		}
		return null;
	}
}
