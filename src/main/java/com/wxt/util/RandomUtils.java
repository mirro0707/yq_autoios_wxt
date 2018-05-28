package com.wxt.util;

import java.util.Random;

/**
 * @description 这个工具类用来随机生成各种类型的字符或字符串
 */
public class RandomUtils {
	
	public static final String allChar = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	public static final String numberChar = "0123456789";
	public static String specialChar = "";
	
	public RandomUtils(){
	}
	
	public RandomUtils(String specialChar){
		
		RandomUtils.specialChar = specialChar;
	}
	
	public static void setSpecialChar(String specialChar){
		RandomUtils.specialChar = specialChar;
	}
	
	/**
	 * @param length 随机生成字符串的长度
	 * @return 返回生成的字符串
	 */
	public static String generateStringFromSpecialChar(int length){
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(specialChar.charAt(random.nextInt(specialChar.length())));
		}
		return sb.toString();
	}
	
	/**
	 * @param specialChars 从指定字符串中提取一定长度的字符串
	 * @param length 提取的字符串的长度
	 * @return 返回提取的字符串
	 */
	public static String generateStringFromSpecialChar(String specialChars, int length){
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(specialChars.charAt(random.nextInt(specialChars.length())));
		}
		return sb.toString();
	}
	
	/**
	 * 从该类所定义的allChar中取得一定长度的字符串 (仅包括26个大写字母和小写字母，还有数字)
	 * @param length 所取字符串的长度
	 * @return 返回随机字符串
	 */
	public static String generateString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(allChar.charAt(random.nextInt(allChar.length())));
		}
		return sb.toString();
	}
	
	/**
	 * return 从所有的字母中取得指定长度的字符串 (仅包括大写字母和小写字母)
	 * @param length 随机取得字符串的长度
	 * @return random 返回随机取得的字符串
	 */
	public static String generateMixString(int length) {
		StringBuffer sb = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < length; i++) {
			sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
		}
		return sb.toString();
	}

	/**
	 * return 随机取得指定长度的字符串 (仅包含大写字母)
	 * @param length 取得字符串的长度
	 * @return 返回取得的字符串
	 */
	public static String generateLowerString(int length) {
		return generateMixString(length).toLowerCase();
	}

	/**
	 * return 随机取得指定长度的字符串 (仅包含小写字母)
	 * @param length 取得的字符串的长度
	 * @return 返回取得的字符串
	 */
	public static String generateUpperString(int length) {
		return generateMixString(length).toUpperCase();
	}

	/**
	 * return 取得指定个数的0 
	 * @param length 取得的0的个数
	 * @return 返回取得的字符串
	 */
	public static String generateZeroString(int length) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sb.append('0');
		}
		return sb.toString();
	}
}
