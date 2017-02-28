package com.yicunyipin.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.Admin;
import com.yicunyipin.service.AdminService;
import com.yicunyipin.service.UserService;
import com.yicunyipin.util.ResponseUtil;

public class ContactAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	@Resource
	private AdminService adminService;
	private static final long serialVersionUID = 1L;
	private Admin admin;//后台登录时传过来Admin表单对象
	private String oldpassword; //管理员更改密码时的旧密码
	private String newpassword;//管理员更改密码时的新密码
	private String repassword;//管理员更改密码时的确认新密码
	private HttpServletRequest request;
	private String imageCode;//验证码
	private String mainPage;
	

	public String getMainPage() {
		return mainPage;
	}
	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	public Admin getAdmin() {
		return admin;
	}
	public void setAdmin(Admin admin) {
		this.admin = admin;
	}
	
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getRepassword() {
		return repassword;
	}
	public void setRepassword(String repassword) {
		this.repassword = repassword;
	}
	/**
	 * 管理员登录
	 */
	public String execute() throws Exception {
		
			return "success";
	}
	
	public String map() throws Exception {
		mainPage="map.jsp";
		return "map";
}
	
	
	
	
	/**
	 * 更改管理员的密码
	 * @return
	 * @throws Exception
	 */
	public String updatePassword()throws Exception{
		HttpSession session=request.getSession();
		JSONObject result=new JSONObject();
		Admin admin=(Admin)session.getAttribute("Curentadmin");
		if(!repassword.equals(newpassword)){
			result.put("errorMsg", "两次输入密码不一致！");	
		    ResponseUtil.write(ServletActionContext.getResponse(), result);//返回信息
		    return null;
		}else if(!admin.getPassword().equals(oldpassword)){
			result.put("errorMsg", "原密码不正确！");	
		    ResponseUtil.write(ServletActionContext.getResponse(), result);//返回信息
		    return null;
		}else {
			admin.setPassword(newpassword);
			//int a=admindao.updateAdminPassWord(admin);
			//if(a!=0){
			  //  ResponseUtil.write(ServletActionContext.getResponse(), result);//返回信息
			//}
		}
		return null;
	}
	/**
	 * 管理员退出
	 * @return
	 * @throws Exception
	 */
	public String logout()throws Exception{
		HttpSession session=request.getSession();
		session.removeAttribute("Curentadmin");
		return "logout";
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
}
