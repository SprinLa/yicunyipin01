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
	private Admin admin;//��̨��¼ʱ������Admin��������
	private String oldpassword; //����Ա��������ʱ�ľ�����
	private String newpassword;//����Ա��������ʱ��������
	private String repassword;//����Ա��������ʱ��ȷ��������
	private HttpServletRequest request;
	private String imageCode;//��֤��
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
	 * ����Ա��¼
	 */
	public String execute() throws Exception {
		
			return "success";
	}
	
	public String map() throws Exception {
		mainPage="map.jsp";
		return "map";
}
	
	
	
	
	/**
	 * ���Ĺ���Ա������
	 * @return
	 * @throws Exception
	 */
	public String updatePassword()throws Exception{
		HttpSession session=request.getSession();
		JSONObject result=new JSONObject();
		Admin admin=(Admin)session.getAttribute("Curentadmin");
		if(!repassword.equals(newpassword)){
			result.put("errorMsg", "�����������벻һ�£�");	
		    ResponseUtil.write(ServletActionContext.getResponse(), result);//������Ϣ
		    return null;
		}else if(!admin.getPassword().equals(oldpassword)){
			result.put("errorMsg", "ԭ���벻��ȷ��");	
		    ResponseUtil.write(ServletActionContext.getResponse(), result);//������Ϣ
		    return null;
		}else {
			admin.setPassword(newpassword);
			//int a=admindao.updateAdminPassWord(admin);
			//if(a!=0){
			  //  ResponseUtil.write(ServletActionContext.getResponse(), result);//������Ϣ
			//}
		}
		return null;
	}
	/**
	 * ����Ա�˳�
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