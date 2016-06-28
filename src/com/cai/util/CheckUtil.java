package com.cai.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckUtil {
	private static final String TOKEN = "cai";
	public static boolean checkSignature(String signature, String timestamp, String nonce){
		String[] arr = new String[]{TOKEN, timestamp, nonce};
		//排序
		Arrays.sort(arr);
		//生成字符串
		StringBuffer content = new StringBuffer();
		for (String tmp : arr) {
			content.append(tmp);
		}
		//sha1加密
		String temp = SHA1(content.toString());
		//与微信传过来的签名比较
		return temp.equals(signature);
	}
	
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {
		System.out.println(CheckUtil.SHA1("abc"));
	}
}
