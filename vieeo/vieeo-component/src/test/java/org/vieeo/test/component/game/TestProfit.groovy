package org.vieeo.test.component.game;

import static org.junit.Assert.*

import org.junit.Test

class TestProfit {

	Map<String,String> ids = [:];
	Map<String,String> names = [:];

	@Test
	public void test() {
		BufferedReader reader = new File("D:\\y.txt").newReader('gb2312');

		reader.eachLine {line ->
			String account = line.substring(line.indexOf("[")+1,line.indexOf("]"));
			ids.put(account, line.substring(15,33));
			names.put(account, line.substring(line.indexOf("]")+1,line.indexOf("</")));
		};
		reader.close();


		def cardYC ="SFT103",cardYD="SFT103";
		def kind ="201202241000000144";
		def role = "0012";
		def profitType="0001";
		def profitValueYCTO ="6.7";
		def profitValueYCFROM ="5.0";
		def profitValueYDTO ="6.7";
		def profitValueYDFROM ="4.7";
		def realRole = "0005";

		def merchant1 = "107697";
		def merchant2 = "495887";
		def merchant1Name = "上海益充电子商务有限公司";
		def merchant2Name = "天津盛景贸易有限公司";
		def validDate = "TO_DATE ('20131223', 'yyyymmdd')";

		def saleFrom = "TO_DATE ('20120601', 'yyyymmdd')";
		def saleTo = "TO_DATE ('20120531', 'yyyymmdd')";



		BufferedReader reader2 = new File("D:\\yc.txt").newReader('utf-8');
		reader2.eachLine {line ->
			String pre495887YCTO = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YCTO = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYC',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValueYCTO,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,null,$saleTo,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YCTO+values495887YCTO+";";

			/*String pre495887YCFROM = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YCFROM = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYC',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValueYCFROM,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,$saleFrom,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YCFROM+values495887YCFROM+";";*/

			String pre107697YC = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values107697YC = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYC',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',0,'$realRole','$merchant1','$merchant1$merchant1Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre107697YC+values107697YC+";";


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

		BufferedReader reader3= new File("D:\\yd.txt").newReader('utf-8');
		reader3.eachLine {line ->
			/*String pre495887YCTO = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YCTO = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYC',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValueYCTO,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,null,$saleTo,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YCTO+values495887YCTO+";";

			String pre495887YCFROM = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YCFROM = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYC',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValueYCFROM,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,$saleFrom,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YCFROM+values495887YCFROM+";";

			String pre107697YC = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values107697YC = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYC',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',0,'$realRole','$merchant1','$merchant1$merchant1Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre107697YC+values107697YC+";";*/


			String pre495887YDTO = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YDTO = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYD',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValueYDTO,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,null,$saleTo,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YDTO+values495887YDTO+";";

			String pre495887YDFROM = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values495887YDFROM = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYD',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValueYDFROM,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,$saleFrom,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre495887YDFROM+values495887YDFROM+";";

			String pre107697YD = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values107697YD = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$cardYD',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',0,'$realRole','$merchant1','$merchant1$merchant2Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre107697YD+values107697YD+";";

			/*String pre362 = "INSERT INTO PPCPROFIT.PROFIT_RULE_FEE_SPEC (ID,CARD_TYPE,INDUSTRY_ID,PROFIT_ROLE, PROFIT_USER_ID,PROFIT_USER_NAME,PROFIT_TYPE,PROFIT_VALUE,REL_PROFIT_ROLE,REL_PROFIT_USER_ID,REL_PROFIT_USER_NAME,REL_PROFIT_TYPE,REL_PROFIT_VALUE,VALIDATE_DATE,INVALIDATE_DATE,SALE_DATE_FROM,SALE_DATE_TO,CREATE_TIME,LAST_UPDATE_TIME,VERSION,RESV_FLD1)";
			String values362 = " VALUES(TO_CHAR (SYSDATE, 'yyyymmdd') || PPCPROFIT.SEQ_PROFIT_RULE_FEE_SPEC.NEXTVAL,'$card36',$kind,'$role',${ids.get(line)},'[$line]${names.get(line)}','$profitType',$profitValue36,'$realRole','$merchant2','$merchant2$merchant2Name','$profitType',0,$validDate,null,null,null,SYSDATE,SYSDATE,1,'YD卡特殊分润政策-新增加外部中凯类商户-20131223')";
			println pre362+values362+";";*/

			//println ids.get(line)+",";

		}

		reader3.close();

	}

}
