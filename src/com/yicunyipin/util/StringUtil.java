package com.yicunyipin.util;

/**
 * �ַ���������
 * @author 
 *
 */
public class StringUtil {
/*
	*//**
	 * �ж��Ƿ��ǿ�
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
	 * �ж��Ƿ��ǿ�
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
	
	//��Ч��д��str.isEmpty() ����str.length() == 0

	/**
	 * �ж��Ƿ��ǿ�
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
	 * �ж��Ƿ��ǿ�
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
