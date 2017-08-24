package com.taotao.utils;


import java.security.MessageDigest;

/**
 * blog www.micmiu.com
 *
 * @author Michael
 *
 */
public class DigestUtil {

	private static final String KEY = "fql_a3";
	
	private static final String ALGORITHM = "SHA1";

	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5',
			'6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * encode string
	 *
	 * @param algorithm
	 * @param str
	 * @return String
	 */
	public static String encode(String algorithm, String str) {
		if (str == null) {
			return null;
		}
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			messageDigest.update(str.getBytes());
			return getFormattedText(messageDigest.digest());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}
	/**
	 * Takes the raw bytes from the digest and formats them correct.
	 *
	 * @param bytes
	 *            the raw bytes from the digest.
	 * @return the formatted bytes.
	 */
	private static String getFormattedText(byte[] bytes) {
		int len = bytes.length;
		StringBuilder buf = new StringBuilder(len * 2);
		// 把密文转换成十六进制的字符串形式
		for (int j = 0; j < len; j++) { 			buf.append(HEX_DIGITS[(bytes[j] >> 4) & 0x0f]);
			buf.append(HEX_DIGITS[bytes[j] & 0x0f]);
		}
		return buf.toString();
	}
	
	public static String getSign(String userCode){
	    return DigestUtil.encode(ALGORITHM,(DigestUtil.encode(ALGORITHM, userCode) + KEY));
	}

}