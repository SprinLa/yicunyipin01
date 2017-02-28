package com.yicunyipin.util;

import org.logicalcobwebs.proxool.ProxoolDataSource;
import org.apache.log4j.Logger;


public class DesPasswordBasicDataSource extends ProxoolDataSource {
	private Logger logger = Logger.getLogger(this.getClass());

	public void setPassword(String password) {

		byte[] key = "781296-5e32-89122".getBytes();
		String pwd = new DES3().decipher2(key,password,password.length());
		super.setPassword(pwd);
	}
	
	public static void main(String[] args){
    	byte[] key = "781296-5e32-89122".getBytes();
    	//加密
    	String str = new DES3().cipher2(key, "asia1nf0", "asia1nf0".length());
    	System.out.println("加密结果："+str);
    	//解密
    	String result = new DES3().decipher2(key, "2006c72a13394559", "2006c72a13394559".length());
    	System.out.println("解密结果："+result);
    }
}