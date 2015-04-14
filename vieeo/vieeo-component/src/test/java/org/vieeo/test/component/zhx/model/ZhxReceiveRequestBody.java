package org.vieeo.test.component.zhx.model;

import com.thoughtworks.xstream.annotations.XStreamAlias;

public class ZhxReceiveRequestBody {

	@XStreamAlias("URL")
	private String URL;

	@XStreamAlias("SP")
	private String SP;

	@XStreamAlias("SO")
	private String SID;

	@XStreamAlias("SO")
	private String SO;

	@XStreamAlias("SODT")
	private String SODT;

	@XStreamAlias("SA")
	private String SA;

	@XStreamAlias("SODTE")
	private String SODTE;

	@XStreamAlias("SRID")
	private String SRID;

	@XStreamAlias("ST")
	private String ST;

	@XStreamAlias("VER")
	private String VER;

	@XStreamAlias("OP")
	private String OP;

	@XStreamAlias("CC")
	private String SIGN;

	@XStreamAlias("CC")
	private String CC;

	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	public String getSP() {
		return SP;
	}

	public void setSP(String sP) {
		SP = sP;
	}

	public String getSID() {
		return SID;
	}

	public void setSID(String sID) {
		SID = sID;
	}

	public String getSO() {
		return SO;
	}

	public void setSO(String sO) {
		SO = sO;
	}

	public String getSODT() {
		return SODT;
	}

	public void setSODT(String sODT) {
		SODT = sODT;
	}

	public String getSA() {
		return SA;
	}

	public void setSA(String sA) {
		SA = sA;
	}

	public String getSODTE() {
		return SODTE;
	}

	public void setSODTE(String sODTE) {
		SODTE = sODTE;
	}

	public String getSRID() {
		return SRID;
	}

	public void setSRID(String sRID) {
		SRID = sRID;
	}

	public String getST() {
		return ST;
	}

	public void setST(String sT) {
		ST = sT;
	}

	public String getVER() {
		return VER;
	}

	public void setVER(String vER) {
		VER = vER;
	}

	public String getOP() {
		return OP;
	}

	public void setOP(String oP) {
		OP = oP;
	}

	public String getSIGN() {
		return SIGN;
	}

	public void setSIGN(String sIGN) {
		SIGN = sIGN;
	}

	public String getCC() {
		return CC;
	}

	public void setCC(String cC) {
		CC = cC;
	}

}
