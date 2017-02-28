package com.yicunyipin.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.Admin;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Policy;
import com.yicunyipin.service.AdminService;
import com.yicunyipin.util.ResponseUtil;
@Controller
public class AdminAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private AdminService adminService;
	private Admin admin;//后台登录时传过来Admin表单对象
	private String oldpassword; //管理员更改密码时的旧密码
	private String newpassword;//管理员更改密码时的新密码
	private String repassword;//管理员更改密码时的确认新密码
	private HttpServletRequest request;
	private String imageCode;//验证码
	private String page;
	private String rows;
	private String type;//1-高级管理员 2-系统操作员
	private String ids;
	private String userName;
	
	
	
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
	
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 管理员登录
	 */
	public String login() throws Exception {
		//System.out.println("admin login");
		HttpSession session=request.getSession();
		
		Admin Curentadmin=adminService.login(admin);
		String ip=request.getRemoteAddr();
		if(imageCode==null||"".equals(imageCode)){
			request.setAttribute("error", "验证码不能为空");
			return "failed";
		}
		String sRand=(String)session.getAttribute("sRand");
		if(!imageCode.equals(sRand)){
			request.setAttribute("error", "验证码错误！！");
			return "failed";
		}
		if(Curentadmin==null){
			request.setAttribute("error", "用户名或密码错误！！");
			return "failed";
		}else{
			session.setAttribute("Curentadmin", Curentadmin);
			return "adminLogin";
		}
		
	
		
	}
	/**
	 * 更改管理员的密码
	 * @return
	 * @throws Exception
	 */
	public String updatePassword()throws Exception{
		//System.out.println("admin update password");
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
			
			//System.out.println(111);
			admin.setPassword(newpassword);
			adminService.updateAdminPassWord(admin);
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return null;
		}
	}
	/**
	 * 管理员退出
	 * @return
	 * @throws Exception
	 */
	public String logout()throws Exception{
		//System.out.println("logout");
		HttpSession session=request.getSession();
		session.removeAttribute("Curentadmin");
		return "logout";
	}
	
	/**
	 * admin分页查询系统操作员信息
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		//System.out.println(2221);
		//System.out.println("page"+page+"rows"+rows);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Admin> adminList;
		Admin s_admin = new Admin();
		//List<TBUser> userList;
		//TBUser s_user=new TBUser();
		System.out.println("type:"+type);
		if(type!=null)
		{
			s_admin.setType(Integer.parseInt(type));
		}
		adminList = adminService.findAdminListWithType(s_admin, pageBean);
			
		long total=adminService.getAdminCountWithType(s_admin);
		System.out.println("admin total:"+total);
		JsonConfig jsonConfig = new JsonConfig();
		//jsonConfig.setExcludes(new String[]{"orderList","productList"});
		
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); 
		
		JSONArray rows=JSONArray.fromObject(adminList,jsonConfig);
		//System.out.println("result:"+rows);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		System.out.println("admin result:"+result);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 *  admin后台-添加系统操作员信息
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		//System.out.println("admin type:"+admin.getType());
		
		boolean exist=adminService.existAdminWithUserName(admin.getUserName());	
		JSONObject result=new JSONObject();
		if (exist) {
			result.put("success", false);
			result.put("errMsg", "此账号已有人使用!");
			ResponseUtil.write(ServletActionContext.getResponse(), result);
		} else {
			if(admin.getId()==0){
				admin.setType(2);
				admin.setPassword("111111");
			}
			
				adminService.saveAdmin(admin);
				
				result.put("success", true);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
			}
		return null;
	}
	
	/**
	 * 重置系统操作员的密码
	 * @return
	 * @throws Exception
	 */
	public String resetPassword()throws Exception{
		//System.out.println("admin reset password");
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Admin s_admin = adminService.getAdminById(Integer.parseInt(idsStr[i]));
			//Policy policy =policyService.getPolicyById(Integer.parseInt(idsStr[i]));
			s_admin.setPassword("111111");
			adminService.saveAdmin(s_admin);						
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String deleteAdmin()throws Exception{
		//System.out.println("admin reset password");
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			Admin s_admin = adminService.getAdminById(Integer.parseInt(idsStr[i]));
			adminService.delete(s_admin);						
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	public String existAdminWithUserName()throws Exception{
		boolean flage = false;
		boolean exist=adminService.existAdminWithUserName(userName);	
		if (exist == false) {
			ResponseUtil.write1(flage);
		} else {
			flage = true;
			ResponseUtil.write1(flage);
		}
		
		return null;
	}
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
}
