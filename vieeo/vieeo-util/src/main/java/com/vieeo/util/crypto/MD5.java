package com.vieeo.util.crypto;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	
	private static MD5 instance = null;

	public String calcMD5(String str) {
		try {
			MessageDigest alga = MessageDigest.getInstance("MD5");
			alga.update(str.getBytes());
			
			byte[] digesta = alga.digest();
			
			return byte2hex(digesta);
		} catch (NoSuchAlgorithmException ex) {
			ex.printStackTrace();
		}
		
		return null;

	}

	public String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0XFF));
			
			if (stmp.length() == 1){
				hs = hs + "0" + stmp;
			} else {
				hs = hs + stmp;
			}
			
			if (n < b.length - 1) {
				hs = hs + "";
			}
		}
		
		return hs;
	}

	public static MD5 getInstance() {
		if (instance == null){
			instance = new MD5();
		}
		return  instance;
	}

}
