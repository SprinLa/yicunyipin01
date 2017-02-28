package com.yicunyipin.util;

/**
 * ����������
 * @author Administrator
 *
 */
public class NavUtil {

	/**
	 * ��ɵ�������
	 * @param subName
	 * @return
	 */
	public static String genNavCode(String subName){
		StringBuffer navCode=new StringBuffer();
		navCode.append("您现在的位置：");
		navCode.append("<a href='index.jsp'>首页</a>&nbsp;");
		navCode.append("&gt; ");
		navCode.append(subName);
		return navCode.toString();
	}
	
	public static String genNavCode1(String subName1,String subName2){
		StringBuffer navCode=new StringBuffer();
		navCode.append("您现在的位置：");
		navCode.append("<a href='index.jsp'>首页</a>&nbsp;");
		navCode.append("&gt; ");
		navCode.append(subName1);
		navCode.append("&gt; ");
		navCode.append(subName2);
		return navCode.toString();
	}
	
	
	
	
}
