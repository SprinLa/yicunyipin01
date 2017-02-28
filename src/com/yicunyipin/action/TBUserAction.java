package com.yicunyipin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Picture;
import com.yicunyipin.entity.TBUser;
import com.yicunyipin.service.TBProductService;
import com.yicunyipin.service.TBUserService;
import com.yicunyipin.util.MD5;
import com.yicunyipin.util.NavUtil;
import com.yicunyipin.util.ResponseUtil;
import com.yicunyipin.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class TBUserAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	
	//@Resource
	//private TBUserService tbuserService;
	/*shaoshan start*/
	private HttpServletRequest request;
	
	@Resource
	private TBProductService tbProductService;
	@Resource
	private TBUserService tbUserService;
	
	private String verified;////是否审核通过 1-通过 2-未通过
	private String page;
	private String rows;
	private String ids;
	private String type1;////会员类型 2-VIP 1-普通
	private TBUser user;
	private String new1;//查询全部新会员的标志
	
	
	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
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
	

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	
	
	public String getType1() {
		return type1;
	}
	public void setType1(String type1) {
		this.type1 = type1;
	}
	public TBUser getUser() {
		return user;
	}
	public void setUser(TBUser user) {
		this.user = user;
	}
	
	public String getNew1() {
		return new1;
	}
	public void setNew1(String new1) {
		this.new1 = new1;
	}
	/**
	 * admin分页查询用户信息
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		//System.out.println(2221);
		//System.out.println("page"+page+"rows"+rows);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<TBUser> userList;
		TBUser s_user=new TBUser();
		//System.out.println("type:"+type);
		
		if(verified!=null){
			s_user.setVerified(Integer.parseInt(verified));
			if(type1!=null)
			{
				s_user.setType(Integer.parseInt(type1));
			}
			 userList=tbUserService.findUserListByStatus(s_user, pageBean);
		}else if(new1!=null&&Integer.parseInt(new1)==1){
			s_user.setVerified(-1);
			userList = tbUserService.findNewUserList(s_user, pageBean);
		}else{
			userList=tbUserService.findUserList(s_user, pageBean);
		}
		
		
		long total=tbUserService.getUserCountWithVerified(s_user);
		//System.out.println("total:"+total);
		//System.out.println("userlist:"+userList.toString());
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderList","productList"});
		
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd")); 
		
		JSONArray rows=JSONArray.fromObject(userList,jsonConfig);
		//System.out.println("result:"+rows);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		System.out.println("result:"+result);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 *  删除用户
	 * @return
	 * @throws Exception
	 */
	public String deleteUsers()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		System.out.println("ids"+ids);
		
		for(int i=0;i<idsStr.length;i++){
			
		//	System.out.println(tbProductService.existProductWithUserId(Integer.parseInt(idsStr[i])));
			if(tbProductService.existProductWithUserId(Integer.parseInt(idsStr[i]))){
				System.out.println(111);
				result.put("exist", "该用户包含商品，不能删除");
			}else{
				System.out.println(222);
				TBUser u=tbUserService.getUserById(Integer.parseInt(idsStr[i]));
				tbUserService.delete(u);
			}
		}
		System.out.println(333);
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String disagreeUser()throws Exception{
		//System.out.println(11133);
		//System.out.println("id"+user.getId());
		TBUser user1=tbUserService.getUserById(user.getId());
		//System.out.println("reason"+user.getVerifiedReason());
		user1.setVerified(2);
		user1.setVerifiedReason(user.getVerifiedReason());
		tbUserService.saveUser(user1);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	public String agreeUser()throws Exception{
		TBUser user1=tbUserService.getUserById(user.getId());
		user1.setVerified(1);
		user1.setMemberTime(new Date());
		tbUserService.saveUser(user1);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	/*shaoshan end*/
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	
	/**
	 * start kongjun 
	 */
//	private HttpServletRequest request;
//
//	@Resource
//	private TBProductService tbProductService;
//	@Resource
//	private TBUserService tbUserService;

	private String userName;
//	private String verified;;// 是否审核通过 1-通过 2-未通过
//	private String page;
//	private String rows;
//	private String ids;
	private String error;
	private TBUser tbUser;

	private int type;
	
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	private String navCode;
	private String password1;// 修改密码时
	private String password2;

	private File[] image; // 上传的文件
	private String[] imageContentType; // 文件类型
	private String[] imageFileName; // 文件名称

	public File[] getImage() {
		return image;
	}

	public void setImage(File[] image) {
		this.image = image;
	}

	public String[] getImageContentType() {
		return imageContentType;
	}

	public void setImageContentType(String[] imageContentType) {
		this.imageContentType = imageContentType;
	}

	public String[] getImageFileName() {
		return imageFileName;
	}

	public void setImageFileName(String[] imageFileName) {
		this.imageFileName = imageFileName;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public String getPassword1() {
		return password1;
	}

	public void setPassword1(String password1) {
		this.password1 = password1;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

//	public String getVerified() {
//		return verified;
//	}
//
//	public void setVerified(String verified) {
//		this.verified = verified;
//	}
//
//	public String getPage() {
//		return page;
//	}
//
//	public void setPage(String page) {
//		this.page = page;
//	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

//	public String getRows() {
//		return rows;
//	}
//
//	public void setRows(String rows) {
//		this.rows = rows;
//	}

	public TBUser getTbUser() {
		return tbUser;
	}

	public void setTbUser(TBUser tbUser) {
		this.tbUser = tbUser;
	}

//	public String getIds() {
//		return ids;
//	}
//
//	public void setIds(String ids) {
//		this.ids = ids;
//	}

	/**
	 * admin分页查询用户信息
	 * 
	 * @return
	 * @throws Exception
	 */
//	public String list() throws Exception {
//		// System.out.println(2221);
//		// System.out.println("page"+page+"rows"+rows);
//		PageBean pageBean = new PageBean(Integer.parseInt(page), Integer.parseInt(rows));
//		List<TBUser> userList;
//		TBUser s_user = new TBUser();
//		// System.out.println("veri:"+verified);
//		if (verified != null) {
//			s_user.setVerified(Integer.parseInt(verified));
//			userList = tbUserService.findUserListByStatus(s_user, pageBean);
//		} else {
//			userList = tbUserService.findUserList(s_user, pageBean);
//		}
//
//		// System.out.println(111);
//		long total = tbUserService.getUserCountWithVerified(s_user);
//		// System.out.println("total:"+total);
//		// System.out.println("userlist:"+userList.toString());
//		JsonConfig jsonConfig = new JsonConfig();
//		jsonConfig.setExcludes(new String[] { "orderList", "productList" });
//
//		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
//
//		JSONArray rows = JSONArray.fromObject(userList, jsonConfig);
//		System.out.println("result:" + rows);
//		JSONObject result = new JSONObject();
//		result.put("rows", rows);
//		result.put("total", total);
//		System.out.println("result:" + result);
//		ResponseUtil.write(ServletActionContext.getResponse(), result);
//		return null;
//	}
//
//	/**
//	 * 删除用户
//	 * 
//	 * @return
//	 * @throws Exception
//	 */
//	public String deleteUsers() throws Exception {
//		JSONObject result = new JSONObject();
//		String[] idsStr = ids.split(",");
//		System.out.println("ids" + ids);
//
//		for (int i = 0; i < idsStr.length; i++) {
//
//			// System.out.println(tbProductService.existProductWithUserId(Integer.parseInt(idsStr[i])));
//			if (tbProductService.existProductWithUserId(Integer.parseInt(idsStr[i]))) {
//				System.out.println(111);
//				result.put("exist", "该用户包含商品，不能删除");
//			} else {
//				System.out.println(222);
//				TBUser u = tbUserService.getUserById(Integer.parseInt(idsStr[i]));
//				tbUserService.delete(u);
//			}
//		}
//		System.out.println(333);
//		result.put("success", true);
//		ResponseUtil.write(ServletActionContext.getResponse(), result);
//		return null;
//	}

	/**
	 * 登陆
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public String login() throws Exception {
		String password = tbUser.getPassword();
		// System.out.println(user.getUserName()+ " 00 " + user.getPassword());
		String passwordMD5 = MD5.getMD5(tbUser.getPassword().getBytes());//
		if (!StringUtil.isEmpty(tbUser.getPassword())) {
			tbUser.setPassword(passwordMD5);
		}
		TBUser currentUser = tbUserService.login(tbUser);
		HttpSession session = request.getSession();

		if (currentUser == null) {
			error = "用户名或密码错误！";
			return "error";
		} else if (!currentUser.getPassword().equals(passwordMD5)) {
			error = "用户名或密码错误！";
			return "error";
		} else {
			//currentUser.setPassword(password);
			session.setAttribute("currentUser", currentUser);
			
			// Product p=new Product();
			// p.setTBUser(currentUser);
			// goodtol=productService.getProductCount(p);

			// if(currentUser.getStatus()==2){
			return "login";
			// }else{
			// return "viplogin";
			// }
		}
	}
	

	public void androidLogin() throws Exception {
		System.out.println("00" + tbUser.getUserName() + "00" + tbUser.getPassword() +"00");
		String pass = tbUser.getPassword();
		String passwordMD5 = MD5.getMD5(tbUser.getPassword().getBytes());//
		if (!StringUtil.isEmpty(tbUser.getPassword())) {
			tbUser.setPassword(passwordMD5);
		}
		Map m = new HashMap<>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println(" 登录前"+tbUser.toString());
		TBUser currentUser = tbUserService.login(tbUser);
		if(currentUser != null) {
			System.out.println("登陆后"+currentUser.toString());
		}
		HttpSession session = request.getSession();

		if (currentUser == null) {
			m.put("code", "A0000");
			m.put("msg", "用户名或密码错误");
			m.put("data", 0);
		} else if (!currentUser.getPassword().equals(passwordMD5)) {
			m.put("code", "A0000");
			m.put("msg", "用户名或密码错误");
			m.put("data", 0);
		} else {
			//currentUser.setPassword(pass);
			session.setAttribute("currentUser", currentUser);
			m.put("code", "A0000");
//			System.out.println("denglu"+currentUser.toString());
			m.put("data", 1);
			// Product p=new Product();
			// p.setTBUser(currentUser);
			// goodtol=productService.getProductCount(p);

			// if(currentUser.getStatus()==2){
			// }else{
			// return "viplogin";
			// }
		}

		JSONObject jsonObject = JSONObject.fromObject(m);
		System.out.println(jsonObject.toString());
		out.println(jsonObject.toString());
	}

	/**
	 * 注册时根据名字判断用户是否存在
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public String existUserWithUserName() throws Exception {
		System.out.println("laile");
		boolean flage = false;
		boolean exist = tbUserService.existUserWithUserName(userName);

		if (exist == false) {
			ResponseUtil.write1(flage);
		} else {
			flage = true;
			ResponseUtil.write1(flage);
		}

		return null;
	}

	/**
	 * 会员注册
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public void androidRegister() throws Exception {
		Map m = new HashMap<>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		System.out.println(tbUser.getUserName());
		@SuppressWarnings("unused")
		String sRand = (String) session.getAttribute("sRand");
		if (tbUser.getUserName() == null || "".equals(tbUser.getUserName())) {
			// request.setAttribute("error", "用户名不能为空！");
			m.put("code", "A0000");
			m.put("msg", "用户名不能为空");
			m.put("data", 0);
		} else if (tbUser.getPassword() == null || "".equals(tbUser.getPassword())) {
			// request.setAttribute("error", "两次输入密码不一致!");
			m.put("code", "A0000");
			m.put("msg", "两次输入密码不一致");
			m.put("data", 0);
		} else if (tbUserService.existUserWithUserName(tbUser.getUserName())) {
			// request.setAttribute("error", "用户名已存在!!");
			m.put("code", "A0000");
			m.put("msg", "用户名已存在");
			m.put("data", 0);
		} else {
			tbUser.setPassword(MD5.getMD5(tbUser.getPassword().getBytes()));
			tbUserService.saveUser(tbUser);
			//session.setAttribute("currentUser", tbUser);
			try {
				// request.setAttribute("error", "注册成功，请您先激活！");
				m.put("code", "A0000");
				m.put("data", 1);
				// return "register_success";
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
		
		JSONObject jsonObject = JSONObject.fromObject(m);
		out.println(jsonObject.toString());
	}
	
	/**
	 * 会员注册
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public void androidGetUser() throws Exception {
		System.out.println("androidGetUser");
		Map m = new HashMap<>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		TBUser u = (TBUser) request.getSession().getAttribute("currentUser");
		System.out.println(u.getUserName());
		System.out.println(u.getPassword());
		//String pass = u.getPassword();
		if(u == null){
			m.put("code", "A0000");
			m.put("msg", "请登录");
			m.put("data", null);
			System.out.println("ceshi1");
		}else{
			System.out.println("ceshi2");
			//System.out.println(u.toString());
			String pass = u.getPassword();
			System.out.println("ceshi3");
			//u.setPassword(MD5.getMD5(u.getPassword().getBytes()));
			System.out.println("ceshi4");
			TBUser user = tbUserService.login(u);
			System.out.println("地方"+user.toString());
			//u.setPassword(pass);
			System.out.println("ceshi5");
			m.put("code", "A0000");
//			Map uu = new HashMap();
//			uu.put("address", user.getAddress());
//			uu.put("contactName", user.getContactName());
//			uu.put("factoryInfo", user.getFactoryInfo());
//			uu.put("legal", user.getLegal());
//			uu.put("memberName", user.getMemberName());
//			uu.put("phoneNum", user.getPhoneNum());
//			uu.put("productName", user.getProductName());
//			uu.put("provinceCity", user.getProvinceCity());
//			uu.put("telNum", user.getTelNum());
//			uu.put("type", user.getType());
//			uu.put("verified", user.getVerified());
//			uu.put("verifiedReason", user.getVerifiedReason());
			
			System.out.println("ceshi6");
			m.put("data", user);
			System.out.println("ceshi7");
			//user.setPassword(pass);
			System.out.println("ceshi8");
			//session.setAttribute("currentUser", user);
			
		}
		JsonConfig config = new JsonConfig();
		config.setJsonPropertyFilter(new PropertyFilter() {
		@Override
		 public boolean apply(Object source, String name, Object value) {
	           if(name.equals("productList")||name.equals("orderList")) {
	             return true;
	           } else {
	             return false;
	          }
	        }
		                });
		JSONObject jsonObject = JSONObject.fromObject(m,config);
		System.out.println("对象"+jsonObject.toString());
		out.println(jsonObject.toString());
	}

	/**
	 * 会员注册
	 * 
	 * @return boolean
	 * @throws Exception
	 */
	public String register() throws Exception {
		HttpSession session = request.getSession();
		System.out.println(tbUser.getUserName());
		@SuppressWarnings("unused")
		String sRand = (String) session.getAttribute("sRand");
		if (tbUser.getUserName() == null || "".equals(tbUser.getUserName())) {
			request.setAttribute("error", "用户名不能为空！");
			return "register";
		} else if (tbUser.getPassword() == null || "".equals(tbUser.getPassword())) {
			request.setAttribute("error", "两次输入密码不一致!");
			return "register";
		} else if (tbUserService.existUserWithUserName(tbUser.getUserName())) {
			request.setAttribute("error", "用户名已存在!!");
			return "register";
		} else {
			tbUser.setPassword(MD5.getMD5(tbUser.getPassword().getBytes()));
			tbUserService.saveUser(tbUser);
			session.setAttribute("currentUser", tbUser);
			int result = 1;
			if (result != 0) {
				try {
					request.setAttribute("error", "注册成功，请您先激活！");
					return "register_success";
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			} else {
				request.setAttribute("error", "注册失败");
				return "register";
			}
		}
	}

	/**
	 * 会员修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public String modifyPassWord() throws Exception {
		TBUser u = (TBUser) request.getSession().getAttribute("currentUser");
		String md5Pass = MD5.getMD5(tbUser.getPassword().getBytes());
		System.out.println(md5Pass);
		System.out.println(u.getPassword());
		if (!md5Pass.equals(u.getPassword())) {
			error = "原密码错误1！";
			// System.out.println("原密码错误1");
			return "error1";
		}
		//String passwordMD5 = MD5.getMD5(tbUser.getPassword().getBytes());//
		//if (!StringUtil.isEmpty(tbUser.getPassword())) {
		tbUser.setPassword(md5Pass);
		tbUser.setUserName(u.getUserName());
		//}
		TBUser currentUser = tbUserService.login(tbUser);

		if (currentUser == null) {
			error = "原密码错误！";
			System.out.println("原密码错误2");
			return "error1";
		} else if (!password1.equals(password2)) {
			error = "两次密码不一致！";
			System.out.println("两次密码不一致");
			return "error1";
		} else {
			String mpasswordMD5 = MD5.getMD5(password1.getBytes());//
			currentUser.setPassword(mpasswordMD5);
			tbUserService.saveUser(currentUser);
			u.setPassword(mpasswordMD5);
			System.out.println("修改密码成功");
		}
		return "modifySuccess";
	}
	
	/**
	 * 会员修改密码
	 * 
	 * @return
	 * @throws Exception
	 */
	public void androidModifyPassword() throws Exception {
		System.out.println("androidModify");
		System.out.println(password1);
		Map m = new HashMap<>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		
		//System.out.println(tbUser.getPassword());
		TBUser u = (TBUser) request.getSession().getAttribute("currentUser");
		System.out.println("修改密码"+u.toString());
		//u.setPassword(MD5.getMD5(u.getPassword().getBytes()));
		TBUser user = tbUserService.login(u);
		
		System.out.println(user.toString());
//		System.out.println(u.getPassword());
//		System.out.println(tbUser.getPassword());
//		if (!tbUser.getPassword().equals(u.getPassword())) {
//			m.put("code", "A0000");
//			m.put("data", 0);
//			m.put("msg", "原密码错误");
//			//error = "原密码错误！";
//			// System.out.println("原密码错误1");
//			//return "error1";
//		}
//		String passwordMD5 = MD5.getMD5(tbUser.getPassword().getBytes());//
//		if (!StringUtil.isEmpty(tbUser.getPassword())) {
//			tbUser.setPassword(passwordMD5);
//			tbUser.setUserName(u.getUserName());
//		}
//		TBUser currentUser = tbUserService.login(tbUser);
//
//		if (currentUser == null) {
//			m.put("code", "A0000");
//			m.put("data", 0);
//			m.put("msg", "原密码错误");
//			//error = "原密码错误！";
//			System.out.println("原密码错误");
//			//return "error1";
//		} else if (!password1.equals(password2)) {
//			m.put("code", "A0000");
//			m.put("data", 0);
//			m.put("msg", "两次密码不一致");
//			//error = "两次密码不一致！";
//			System.out.println("两次密码不一致");
//			//return "error1";
//		} else {
			String mpasswordMD5 = MD5.getMD5(password1.getBytes());//
			user.setPassword(mpasswordMD5);
			tbUserService.saveUser(user);
			//System.out.println("修改密码成功");
			m.put("code", "A0000");
			m.put("data", 1);
			//m.put("msg", "两次密码不一致");
			//u.setPassword(password1);
		//}
		JSONObject jsonObject = JSONObject.fromObject(m);
		System.out.println(jsonObject.toString());
		out.println(jsonObject.toString());
	}

	public String userPassword() throws Exception {
		request.setAttribute("mainPage", "/background/user/userPass.jsp");
		navCode = NavUtil.genNavCode("修改密码");
		return "userPassword";
	}

	public String verifiedStatus() throws Exception {
		request.setAttribute("mainPage", "/background/user/verifiedStatus.jsp");
		return "verifiedStatus";
	}

	public String userPreSave2() throws Exception {
		request.setAttribute("mainPage", "/background/user/userSave2.jsp");
		navCode = NavUtil.genNavCode("基本信息维护");
		return "userPreSave2";
	}
	
	public String userPreSave3() throws Exception {
		request.setAttribute("mainPage", "/background/user/userSave3.jsp");
		navCode = NavUtil.genNavCode("基本信息维护");
		return "userPreSave2";
	}

	/**
	 * 保存会员基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String applyMember() throws Exception {
		/* 保存图片组 */
		//System.out.println(type);
		 //System.out.println(tbUser.toString());
		 System.out.println(image.length);
		
		if(type == 1){
			tbUser.setType(1);
		}else{
			tbUser.setType(2);
		}
		tbUser.setVerified(0);
		ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		String realpath = ServletActionContext.getServletContext().getRealPath("/images");
		if (image != null) {
			File savedir = new File(realpath);
			if (!savedir.getParentFile().exists())
				savedir.getParentFile().mkdirs();

			for (int i = 0; i < image.length; i++) {

				Date currentTime = new Date();// 生成日期
				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");// 转化成到毫秒格式
				String dateString = formatter.format(currentTime);
				Random ran = new Random();
				int rannum = ran.nextInt(9999);// 生成随机数
				imageFileName[i] = dateString + rannum + imageFileName[i];// 拼凑新的图片名称
				System.out.println("wenjian"+imageFileName[i]);
				File savefile = new File(savedir, imageFileName[i]);
				FileUtils.copyFile(image[i], savefile);

				Picture pic = new Picture();

				pic.setPicName(imageFileName[i]);

				if (i == 0) {
					tbUser.setAddressPic(imageFileName[i]);
				} else if (i == 1) {
					tbUser.setAwardPic(imageFileName[i]);
				} else {
					tbUser.setLicencePic(imageFileName[i]);
				}
			}
//			Date currentTime = new Date();// 生成日期
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");// 转化成到毫秒格式
//			String date = formatter.format(currentTime);
//			tbUser.setMemberTime(currentTime);
			ActionContext.getContext().put("message", "文件上传成功");
			//String passwordMD5 = MD5.getMD5(tbUser.getPassword().getBytes());//
			//String password = tbUser.getPassword();
			//tbUser.setPassword(passwordMD5);
			tbUserService.updateUser(tbUser);
			HttpSession session = request.getSession();
			//tbUser.setPassword(password);
			session.setAttribute("currentUser", tbUser);
			navCode=NavUtil.genNavCode("会员申请结果");
			request.setAttribute("mainPage", "/background/success.jsp");
			return "modifySuccess";
		}
		navCode=NavUtil.genNavCode("会员申请结果");
		request.setAttribute("mainPage", "/background/fail.jsp");
		return "modifySuccess";
	}
	
	/**
	 * 保存会员基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void androidApplyMember() throws Exception {
		/* 保存图片组 */
		//System.out.println(type);
		 //System.out.println(tbUser.toString());
		
		Map m = new HashMap<>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		 System.out.println(image.length);
		TBUser u = (TBUser)request.getSession().getAttribute("currentUser");
		if(u== null){
			m.put("code", "A0000");
			m.put("msg", "请登录");
			m.put("data", 0);
		}else{
			tbUser.setVerified(0);
			ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
			String realpath = ServletActionContext.getServletContext().getRealPath("/images");
			if (image != null) {
				File savedir = new File(realpath);
				if (!savedir.getParentFile().exists())
					savedir.getParentFile().mkdirs();

				for (int i = 0; i < image.length; i++) {

					Date currentTime = new Date();// 生成日期
					SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");// 转化成到毫秒格式
					String dateString = formatter.format(currentTime);
					Random ran = new Random();
					int rannum = ran.nextInt(9999);// 生成随机数
					imageFileName[i] = dateString + rannum + imageFileName[i];// 拼凑新的图片名称
					System.out.println("wenjian"+imageFileName[i]);
					File savefile = new File(savedir, imageFileName[i]);
					FileUtils.copyFile(image[i], savefile);

					Picture pic = new Picture();

					pic.setPicName(imageFileName[i]);

					if (i == 0) {
						tbUser.setAddressPic(imageFileName[i]);
					} else if (i == 1) {
						tbUser.setAwardPic(imageFileName[i]);
					} else {
						tbUser.setLicencePic(imageFileName[i]);
					}
				}
//				Date currentTime = new Date();// 生成日期
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");// 转化成到毫秒格式
//				String date = formatter.format(currentTime);
//				tbUser.setMemberTime(currentTime);
				ActionContext.getContext().put("message", "文件上传成功");
				//String passwordMD5 = u.getPassword();//
				//String password = tbUser.getPassword();
				tbUser.setPassword(u.getPassword());
				tbUser.setUserName(u.getUserName());
				tbUser.setId(u.getId());
				tbUserService.updateUser(tbUser);
				HttpSession session = request.getSession();
				//tbUser.setPassword(u.getPassword());
				session.setAttribute("currentUser", tbUser);
				m.put("code", "A0000");
				m.put("data", 1);
			}else{
				m.put("code", "A0000");
				m.put("data", 0);
				m.put("msg", "图片不能为空");
			}
		}
		JSONObject jsonObject = JSONObject.fromObject(m);
		out.println(jsonObject.toString());
	}
	
	/**
	 * 保存会员基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public String confirmMember() throws Exception {
		/* 保存图片组 */
		System.out.println(type);
		System.out.println(tbUser.toString());
		//System.out.println(image.length);
		
		if(type == 1){
			tbUser.setType(1);
		}else{
			tbUser.setType(2);
		}
		//tbUser.setVerified(0);
		
		String passwordMD5 = MD5.getMD5(tbUser.getPassword().getBytes());//
		String password = tbUser.getPassword();
		//tbUser.setPassword(passwordMD5);
		tbUserService.updateUser(tbUser);
		HttpSession session = request.getSession();
		//tbUser.setPassword(password);
		session.setAttribute("currentUser", tbUser);
		return "modifySuccess";
	}
	
	/**
	 * 保存会员基本信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public void androidConfirmMember() throws Exception {
		Map m = new HashMap<>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		TBUser user = (TBUser)request.getSession().getAttribute("currentUser");
		System.out.println(user.getPassword());
		System.out.println(user.toString());
		user.setAddress(tbUser.getAddress());
		System.out.println(tbUser.getAddress());
		user.setPhoneNum(tbUser.getPhoneNum());
		System.out.println(tbUser.getPhoneNum());
		user.setTelNum(tbUser.getTelNum());
		System.out.println(tbUser.getTelNum());
		user.setContactName(tbUser.getContactName());
		System.out.println(tbUser.getContactName());
		user.setFactoryInfo(tbUser.getFactoryInfo());
		System.out.println(tbUser.getFactoryInfo());
		
		tbUserService.updateUser(user);
		
		System.out.println(user.toString());
		m.put("code", "A0000");
		m.put("data", 1);
		
		JSONObject jsonObject = JSONObject.fromObject(m);
		out.println(jsonObject.toString());
	}
	
	
	/**
	 * end kongjun 
	 */

}
