package com.yicunyipin.util;

/**
 * 字符串工具类
 * @author 
 *
 */
public class StringUtil {
/*
	*//**
	 * 判断是否是空
	 * @param str
	 * @return
	 *//*
	public static boolean isEmpty(String str){
		if(str==null||"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}
	
	*//**
	 * 判断是否不是空
	 * @param str
	 * @return
	 *//*
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!"".equals(str.trim())){
			return true;
		}else{
			return false;
		}
	}*/
	
	//高效率写法str.isEmpty() 或者str.length() == 0

	/**
	 * 判断是否是空
	 * @param str
	 * @return
	 */
	public static boolean isEmpty(String str){
		if(str==null||str.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * 判断是否不是空
	 * @param str
	 * @return
	 */
	public static boolean isNotEmpty(String str){
		if((str!=null)&&!str.isEmpty()){
			return true;
		}else{
			return false;
		}
	}
	
	
	
	
}
