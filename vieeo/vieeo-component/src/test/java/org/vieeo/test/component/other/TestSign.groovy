package org.vieeo.test.component.other;

import static org.junit.Assert.*

import org.junit.Test

import com.vieeo.util.crypto.MD5

class TestSign {

	@Test
	public void test() {
		//Header
		String name =  "REP_B2CPAYMENT";
		String charset = "UTF-8";
		String msgSender = "SFT";
		String traceNo = "435ce543-bec2-4693-ab2b-081e3e964093";
		String version =  "V4.1.2.1.1";
		String sendTime = "20140615174345";
		//Body
		//订单金额
		String orderAmount = "50.00";
		//商户订单号
		String orderNo = "20140615174257-000005-5183-1";
		String instCode = "OC";
		String paymentNo = "CP20140615174258208492";
		//实际支付金额
		String transAmount = "0.00";
		//盛付通卡网关订单号
		String transNo = "C20140615174257678299";
		//订单状态
		String transStatus = "02";
		//订单支付时间
		String transTime = "20140615174257";
		String transType = "PT030";
		String payChannel = "75";
		String merchantNo = "336821";
		String payableFee = "0.00";
		String receivableFee = "1.00";
		//Error
		String errorCode = "B0532006";
		//String errorMsg = "[{&quot;cardNo&quot;:&quot;14435110352599970&quot;,&quot;errorCode&quot;:&quot;F1039&quot;,&quot;errorMsg&quot;:&quot;卡号或者卡密不正确，请确认后重新输入&quot;}]";
		String errorMsg = "[{\"cardNo\":\"14435110352599970\",\"errorCode\":\"F1039\",\"errorMsg\":\"卡号或者卡密不正确，请确认后重新输入\"}]";
		//Ext
		String ext1 = "";
		String ext2 = "";
		//Sign
		String signType = "MD5";
		String signMsg = "C1B4A32D275F3AA43ABD1C5B668F570A";
		StringBuffer buf = new StringBuffer();
		//Header
		buf.append(name);
		buf.append(version);
		buf.append(charset);
		buf.append(traceNo);
		buf.append(msgSender);
		buf.append(sendTime);
		//body
		buf.append(instCode);
		buf.append(orderNo);
		buf.append(orderAmount);
//		buf.append(paymentNo);
		buf.append(transNo);
		buf.append(transAmount);
		buf.append(transStatus);
		buf.append(transType);
		buf.append(transTime);
		buf.append(merchantNo);
//		buf.append(payChannel);
//		buf.append(payableFee);
//		buf.append(receivableFee);
		//Error
		buf.append(errorCode);
		buf.append(errorMsg);
		//Ext
		buf.append(ext1);
		buf.append(ext2);
		//sign
		buf.append(signType);

		println "${buf.toString()}";
		//加入商户密钥
		buf.append("yIwUyIqIsAN1517");

		println MD5.getInstance().calcMD5(buf.toString()).toUpperCase()

	}

}
