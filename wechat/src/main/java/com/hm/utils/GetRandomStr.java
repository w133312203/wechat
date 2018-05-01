package com.hm.utils;
 
import java.util.Random;
 
public class GetRandomStr {
	
	/**
	 * 生成随即字符串
	 * @param length
	 * @return
	 */
	public String getRandomString(int length) {
		String base = "abcdefghijklmnopqrstuvwxyz0123456789"; 
		Random random = new Random(); 
		StringBuffer sb = new StringBuffer(); 
		for (int i = 0; i < length; i++) { 
			int number = random.nextInt(base.length()); 
			sb.append(base.charAt(number)); 
		} 
		return sb.toString(); 
	}
}