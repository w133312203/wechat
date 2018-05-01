package com.hm.utils;

import java.math.BigDecimal;
import java.util.Arrays;


public class BaseUtil {
	
	/**
	 * 判断数组中是否含有该字符串
	 * @param arr
	 * @param str
	 * @return
	 */
	public static boolean useList(String[] arr, String str) {
	    return Arrays.asList(arr).contains(str);
	}
	
	/**
	 * 判断数组arrA中是否包含数组arrB中的所有元素(如果包含返回非公共数据)
	 * @param arr
	 * @param arrB
	 * @return
	 */
	public static boolean contains(String[] arrA, String[] arrB) {
		boolean obj = true;
		for (String str : arrB) {
			if(!Arrays.asList(arrA).contains(str)){
				obj = false;
			}
		}
	    return obj;
	}
	
	/**
	 * 判断数组arrA中是否包含数组arrB中的所有元素(如果包含返回非公共数据)
	 * @param arr
	 * @param arrB
	 * @return
	 */
	public static String[] difference(String[] arrA, String[] arrB) {
		if(contains(arrA, arrB)){
			String data = "";
			for (String str : arrA) {
				if(!Arrays.asList(arrB).contains(str)){
					data += str+",";
				}
			}
			if(!StringUtil.isEmpty(data))
			return data.substring(0, data.length()-1).split(",");
		}
		return null;
	}
	
	public static String[] defferenceArray(String[] arrA, String[] arrB) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arrA.length;i++) {
			boolean flag = false;
			for(int n=0;n<arrB.length;n++) {
				if(arrA[i].equals(arrB[n])) {
					flag = true;
					break;
				}
			}
			if(!flag) {
				sb.append(arrA[i]+",");
			}
		}
		String str = sb.toString().substring(0, sb.length()-1);
		return str.split(",");
	}
	
	public static String ArryToString(String[] arrA) {
		StringBuilder sb = new StringBuilder();
		for(int i=0;i<arrA.length;i++) {
			if(StringUtil.isEmpty(arrA[i])) {
				sb.append(",");
			}else {
				sb.append(arrA[i]+",");
			}
			
		}
		return sb.toString().substring(0, sb.length()-1);
	}
	
	public static Double round(double d) {
		BigDecimal b = new BigDecimal(new Double(d).toString());
		double f = b.setScale(0, BigDecimal.ROUND_HALF_UP).doubleValue();
		return f;
	}
	
}
