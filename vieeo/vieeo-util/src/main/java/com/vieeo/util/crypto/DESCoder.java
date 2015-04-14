package com.vieeo.util.crypto;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;


public class DESCoder {

	public static final String KEY_ALGORITHM = "DES";

	public static final String CIPHER_ALGORITHM = "DES/ECB/PKCS5Padding";

	public static byte[] initkey() throws Exception {
		KeyGenerator kg = KeyGenerator.getInstance(KEY_ALGORITHM);
		kg.init(56);
		SecretKey secretKey = kg.generateKey();
		return secretKey.getEncoded();
	}

	public static Key toKey(byte[] key) throws Exception {
		DESKeySpec dks = new DESKeySpec(key);
		SecretKeyFactory keyFactory = SecretKeyFactory
				.getInstance(KEY_ALGORITHM);
		SecretKey secretKey = keyFactory.generateSecret(dks);
		return secretKey;
	}

	public static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	public static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		Key k = toKey(key);
		Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, k);
		return cipher.doFinal(data);
	}

	public static void main(String[] args) throws Exception {
		String str = "DES";
		System.out.println("原文：" + str);
		// 初始化密钥
		byte[] key = DESCoder.initkey();
		System.out.println("密钥：" + Base64.encode(key));
		// 加密数据
		byte[] data = DESCoder.encrypt(str.getBytes(), key);
		System.out.println("加密后：" + Base64.encode(data));
		// 解密数据
		data = DESCoder.decrypt(data, key);
		System.out.println("解密后：" + new String(data));
	}

	public static String encrypt(String string, String key) {
		String result = null;
		try {
			return Base64.encode(DESCoder.encrypt(string.getBytes(), key.getBytes()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}