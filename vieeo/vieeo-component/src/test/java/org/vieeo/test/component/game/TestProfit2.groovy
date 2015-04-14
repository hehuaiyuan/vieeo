package org.vieeo.test.component.game;

import static org.junit.Assert.*

import org.junit.Test

class TestProfit2 {

	Map<String,String> ids = [:];
	Map<String,String> names = [:];

	@Test
	public void test() {
		BufferedReader reader = new File("D:\\新建文件夹\\profit_rule\\new.txt").newReader('gb2312');

		reader.eachLine {line ->
			String account = line.substring(line.indexOf("[")+1,line.indexOf("]"));
			ids.put(account, line.substring(15,33));
			names.put(account, line.substring(line.indexOf("]")+1,line.indexOf("</")));
		};
		reader.close();


		def card35 ="SFT002";
		def card36 ="SFT003";
		def kind ="201202241000000144";
		def role = "0020";
		def profitType="0001";
		def profitValue35 ="3.2";
		def profitValue36 ="2.9";
		def realRole = "0005";

		def merchant1 = "495887";
		def merchant1Name = "天津盛景贸易有限公司495887";
		def merchant2 = "107697";
		def merchant2Name = "上海益充电子商务有限公司";
		def validDate = "TO_DATE ('20131223', 'yyyymmdd')";


		BufferedReader reader2 = new File("D:\\新建文件夹\\profit_rule\\2.txt").newReader('utf-8');
		reader2.eachLine {line ->
			String pre495887YCTO = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YCTO = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$card35',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValue35,'$realRole','$merchant1','$merchant1$merchant1Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YCTO+values495887YCTO+";";

			String pre495887YCFROM = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YCFROM = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$card36',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValue36,'$realRole','$merchant1','$merchant1$merchant1Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YCFROM+values495887YCFROM+";";


			String pre107697YCTO = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values107697YCTO = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$card35',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValue35,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre107697YCTO+values107697YCTO+";";

			String pre107697YCFROM = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values107697YCFROM = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$card36',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValue36,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre107697YCFROM+values107697YCFROM+";";


			/*String pre495887YDTO = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YDTO = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYD',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValueYDTO,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,null,$saleTo,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YDTO+values495887YDTO+";";

			String pre495887YDFROM = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YDFROM = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYD',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValueYDFROM,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,$saleFrom,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YDFROM+values495887YDFROM+";";

			String pre107697YD = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values107697YD = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYD',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',0,'$realRole','$merchant1','$merchant1$merchant2Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre107697YD+values107697YD+";";*/

			/*String pre362 = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values362 = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$card36',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValue36,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre362+values362+";";*/

			//println ids.get(line)+",";

		}

		reader2.close();

		/*def profitValue ="0";

		BufferedReader reader3= new File("D:\\新建文件夹\\profit_rule\\yc2.1.txt").newReader('utf-8');
		reader3.eachLine {line ->
			String pre495887YCTO = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YCTO = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$card36',$kind,'0020',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValue,'$realRole','$merchant1','$merchant1$merchant1Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YCTO+values495887YCTO+";";

			String pre495887MONTH = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887MONTH = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$card36',$kind,'0012',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValue,'$realRole','$merchant1','$merchant1$merchant1Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887MONTH+values495887MONTH+";";

		}

		reader3.close();*/

	}

}
