package com.hm.utils;


public class CalculatorUtil {
	
	/**
	 * a/b向上取整
	 * @param a
	 * @param b
	 * @return
	 */
	public static int division(int a, int b) {
		int c;
        if(a%b!=0){
            c=a/b+1;
        }else{
            c=a/b;
        }
		return c;
	}

}
