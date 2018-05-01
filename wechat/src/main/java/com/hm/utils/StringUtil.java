package com.hm.utils;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	
	public static boolean isEmpty(String arg) {
		return (arg == null) || (arg.trim().equals(""));
	}

	public static boolean isEmail(final String searchPhrase) {
		if (!isEmpty(searchPhrase)) {
			final String check = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
			// final String check =
			// "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$"
			// ;
			final Pattern regex = Pattern.compile(check);
			final Matcher matcher = regex.matcher(searchPhrase);
			return matcher.matches();
		} else {
			return false;
		}

	}

	public static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern
				.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}
	
	
	public static boolean hasNullStr(String arg) {
		return arg == null || arg.trim().equals("")
				|| arg.trim().equalsIgnoreCase("null");
	}

	/**
	 * 检验密码的正确性
	 * 
	 * @param length
	 *            密码至少多少位
	 * @param pwd
	 *            密码
	 * @param confirmpwd
	 *            确认密码
	 * @return
	 */
	public static String validatePWD(int length, String pwd, String confirmpwd) {
		if (!isEmpty(pwd) && !isEmpty(confirmpwd)) {
			if (!pwd.equals(confirmpwd)) {
				return "两次密码不一致!";
			}
			char[] element = pwd.toCharArray();
			for (int i = 0; i < element.length; i++) {
				if (element[i] > 127) {
					return "密码不能包含中文字符!";
				}
			}
			if (pwd.length() < length) {
				return "密码长度至少为6位!";
			}
		} else {
			return "密码不能为空!";
		}
		return null;
	}
	
	//对手机端传过来的重要字段进行过滤，中文乱码处理
	public static String toUtf8(String str ){
		
		if(null!=str&&!("").equals(str)){
			try {
				str=new String( str.getBytes("iso8859-1"),"utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	  
		return str;
		}

	/**
	 * 正浮点数
	 * 
	 * @param num
	 * @return
	 */
	public static boolean isDecimal(String str) {
		if (str == null || "".equals(str))
			return false;
		Pattern pattern = Pattern.compile("[0-9]*(\\.?)[0-9]*");
		return pattern.matcher(str).matches();
	}

	public static boolean isNumber(String arg) {
		// return (!isEmpty(arg)) && arg.matches("^[0-9]+$"); //正数 0-9
		return (!isEmpty(arg)) && arg.matches("-?[0-9]*"); // 正负数
	}

	public static String formatDate(Date date, String format) {
		return (new SimpleDateFormat(format)).format(date);
	}

	public static int GetRandomNumber6() {
		Random r = new Random();
		return r.nextInt(900000) + 100000;
	}

	public static int GetRandomNumber4() {
		Random r = new Random();
		return r.nextInt(9000) + 1000;
	}
	
	public static String getString(String str) {
		if (StringUtil.isEmpty(str)) {
			return str;
		}
		return str.replace("{", "").replace("}", "");
	}

}
