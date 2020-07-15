package com.kyny.studyretrofit;//package com.kyny.studyretrofit;
//
//import psdi.util.MXCipher;
//import psdi.util.MXException;
//
//public class DesUtil {
//
//	public static final String algTest = "DESede";
//	public static final String modeTest = "CBC";
//	public static final String paddingTest = "PKCS5Padding";
//	public static final String keyTest = "Sa#qk5usfmMI-@2dbZP9`jL3";
//	public static final String specTest = "beLd7$lB";
//	public static final String modTest = "";
//	public static final String providerTest = "";
//
//	public static void main(String[] args) throws Exception {
//		System.out.println("密文：500C7C9864165AD6E8A35E1EFC5279C2");
//		System.out.println("原文："+decryptMode("500C7C9864165AD6E8A35E1EFC5279C2"));
//
//	}
//
//	/**
//	 * 将密码密文转化成明文
//	 * @param password 密码密文
//	 * @return
//	 * @throws MXException
//	 * 曾俊2015-3-13
//	 */
//	public static String decryptMode(String password) throws MXException {
//		MXCipher mxc = new MXCipher(algTest, modeTest, paddingTest, keyTest,
//				specTest, modTest, providerTest);
//		byte[] bytes = StrToBytes(password);// 用户密码
//		return mxc.decData(bytes);
//	}
//
//	public static byte[] StrToBytes(String str) {
//		int len = str.length();
//		if (len == 0 || len % 2 == 1)
//			return null;
//		byte[] b = new byte[len / 2];
//		for (int i = 0; i < len; i += 2) {
//			b[i / 2] = (byte) Integer.decode("0x" + str.substring(i, i + 2))
//					.intValue();
//		}
//		return b;
//	}
//}
