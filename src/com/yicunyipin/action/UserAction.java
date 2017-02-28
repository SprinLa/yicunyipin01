package com.yicunyipin.action;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import javax.annotation.Resource;
import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Picture;
import com.yicunyipin.entity.Product;
import com.yicunyipin.entity.User;
import com.yicunyipin.service.ProductService;
import com.yicunyipin.service.UserService;
import com.yicunyipin.util.MD5;
import com.yicunyipin.util.NavUtil;
import com.yicunyipin.util.ResponseUtil;
import com.yicunyipin.util.StringUtil;

import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
@Controller
public class UserAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private UserService userService;
	
	
	private HttpServletRequest request;
	private String userName;
	private String email; // 
	private User user;
	private String error;
	private String page;
	private String rows;
	private User s_user;
	private String ids;
	private int uId;//vip浼氬憳缁存姢鍩烘湰淇℃伅鏃秛id
	private String navCode;
	private String password1;//淇敼瀵嗙爜鏃�
	private String password2;
	private String status;//鐢ㄦ埛鐘舵�
	private Long goodtol;//鍟嗗搧鎬绘暟
	@Resource
	private ProductService productService;
	
	
	private File image; //涓婁紶鐨勬枃浠�
	private String imageContentType; //鏂囦欢绫诲瀷
	private String imageFileName; //鏂囦欢鍚嶇О
	
	
	
	public Long getGoodtol() {
		return goodtol;
	}
	public void setGoodtol(Long goodtol) {
		this.goodtol = goodtol;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public File getImage() {
		return image;
	}
	public void setImage(File image) {
		this.image = image;
	}
	public String getImageContentType() {
		return imageContentType;
	}
	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}
	public String getImageFileName() {
		return imageFileName;
	}
	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
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
	public String getNavCode() {
		return navCode;
	}
	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}
	public int getuId() {
		return uId;
	}
	public void setuId(int uId) {
		this.uId = uId;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public User getS_user() {
		return s_user;
	}
	public void setS_user(User s_user) {
		this.s_user = s_user;
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


	private String imageCode;
	
	public String getUserName() {
		return userName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	
	
	
	public String getImageCode() {
		return imageCode;
	}
	public void setImageCode(String imageCode) {
		this.imageCode = imageCode;
	}
	
	/**
	 * 娉ㄥ唽鏃舵牴鎹悕瀛楀垽鏂敤鎴锋槸鍚﹀瓨鍦�
	 * @return boolean
	 * @throws Exception
	 */
	public String existUserWithUserName()throws Exception{
		
		boolean flage = false;
		boolean exist=userService.existUserWithUserName(userName);
		
		if (exist == false) {
			ResponseUtil.write1(flage);
		} else {
			flage = true;
			ResponseUtil.write1(flage);
		}
		
		return null;
	}
	
	/**
	 * 娉ㄥ唽鏃舵牴鎹垽鏂偖绠辨槸鍚﹀瓨鍦�
	 * @return boolean
	 * @throws Exception
	 */
	public String CheckEmail()throws Exception{
		boolean flage = false;
		boolean exist=userService.existUserWithEmail(email);
		if (exist == false) {
			ResponseUtil.write1(flage);
		} else {
			flage = true;
			ResponseUtil.write1(flage);
		}
			return null;
	}
	
	
	
	
	/**
	 * 鏍规嵁浼氬憳鍚嶇О鍒ゆ柇鏄惁瀛樺湪
	 * @return boolean
	 * @throws Exception
	 */
	public String existUserWithTrueName()throws Exception{
		
		boolean flage = false;
		boolean exist=userService.existUserWithTrueName(userName);
		if (exist == false) {
			ResponseUtil.write1(flage);
		} else {
			flage = true;
			ResponseUtil.write1(flage);
		}
		return null;
	}
	
	
	/**
	 * 浼氬憳鐧婚檰
	 * @return boolean
	 * @throws Exception
	 */
	public String login()throws Exception{
		//System.out.println(user.getUserName()+ "  00  " + user.getPassword());
		String passwordMD5=MD5.getMD5(user.getPassword().getBytes());//
		if(!StringUtil.isEmpty(user.getPassword())){
			user.setPassword(passwordMD5);
		}
		User currentUser=userService.login(user);
		HttpSession session=request.getSession();
	
		if(currentUser==null){
			error="鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒锛�";
			return "error";
		}else if(!currentUser.getPassword().equals(passwordMD5)){
			error="鐢ㄦ埛鍚嶆垨瀵嗙爜閿欒锛�";
			return "error";
		}
		else{
			currentUser.setPassword(user.getPassword());
			session.setAttribute("currentUser", currentUser);
			
			Product p=new Product();
			p.setUser(currentUser);
			 goodtol=productService.getProductCount(p);

			 if(currentUser.getStatus()==2){
				return "viplogin";
			}else{
				return "viplogin";			
			}
		}
	}
	
	/**
	 * 浼氬憳娉ㄥ唽
	 * @return boolean
	 * @throws Exception
	 */
	public String register()throws Exception{
		HttpSession session=request.getSession();
		@SuppressWarnings("unused")
		String sRand=(String)session.getAttribute("sRand");
		if(user.getUserName()==null||"".equals(user.getUserName())){
			request.setAttribute("error", "鐢ㄦ埛鍚嶄笉鑳戒负绌猴紒");
			return "register";
		}else if(user.getPassword()==null||"".equals(user.getPassword())){
			request.setAttribute("error", "涓ゆ杈撳叆瀵嗙爜涓嶄竴鑷�");
			return "register";
		}
		else if(userService.existUserWithUserName(user.getUserName())){
			request.setAttribute("error", "鐢ㄦ埛鍚嶅凡瀛樺湪!!");
			return "register";
		}else if(userService.existUserWithEmail(user.getEmail())){
			request.setAttribute("error", "璇ラ偖绠卞凡琚敞鍐�");
			return "register";
		}else {
			user.setPassword(MD5.getMD5(user.getPassword().getBytes()));
			userService.saveUser(user);
			session.setAttribute("currentUser", user);
			int result=1;
			if(result!=0){
				try{
			    	   
			    	   //寰�敞鍐岄偖绠卞彂閭欢
					/*Properties props = new Properties();
					props.load(this.getClass().getClassLoader().getResourceAsStream("email_template.properties"));
					String host = props.getProperty("host");//锟街�
					String username = props.getProperty("username");//锟斤拷取锟矫伙拷锟斤拷
					String password = props.getProperty("password");//锟斤拷取锟斤拷锟斤拷
					String from = props.getProperty("from");//锟斤拷取锟斤拷锟斤拷锟剿碉拷址
					String to = user.getEmail();//锟斤拷取锟秸硷拷锟剿碉拷址
					String subject = props.getProperty("subject");//锟斤拷取锟斤拷锟斤拷
					//String content = MessageFormat.format(props.getProperty("content"), user.getActivecode());
					String content = props.getProperty("content");

					
					Session session1 = MailUtils.createSession(host, username, password);
					Mail mail = new Mail(from, to, subject, content);
					MailUtils.send(session1, mail);*/
					request.setAttribute("error", "娉ㄥ唽鎴愬姛锛岃鎮ㄥ厛婵�椿锛�");
					return "register_success";
		          }catch(Exception e){
		            	 throw new RuntimeException(e);
		            }
			       }else{
				       request.setAttribute("error", "娉ㄥ唽澶辫触");
				       return "register";
		            }
	             }
	}
	/**
	 * 浼氬憳閫�嚭
	 * @return boolean
	 * @throws Exception
	 */
	public String logout()throws Exception{
		request.getSession().invalidate();
		return "logout";
	}
	
	
	
	/**
	 * 杩涘叆浼氬憳淇℃伅鏌ョ湅椤甸潰
	 * @return boolean
	 * @throws Exception
	 */
	public String userPreSave() throws Exception {
	       request.setAttribute("mainPage", "/background/user/userSave.jsp");
	       navCode=NavUtil.genNavCode("鍩烘湰淇℃伅缁存姢");
	       return "userPreSave";
	    
	  
	}
	
	
	/**
	 * 杩涘叆浼氬憳淇℃伅缂栬緫鏌ョ湅椤甸潰
	 * @return boolean
	 * @throws Exception
	 */
	public String userPreSave2() throws Exception {
	       request.setAttribute("mainPage", "/background/user/userSave2.jsp");
	       navCode=NavUtil.genNavCode("鍩烘湰淇℃伅缁存姢");
	       return "userPreSave2";
	}
	
		/**
		 * 淇濆瓨浼氬憳鍩烘湰淇℃伅
		 * @return
		 * @throws Exception
		 */
		public String saveUser2()throws Exception{
	
			 /*淇濆瓨鍥剧墖缁*/
			 ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		     String realpath = ServletActionContext.getServletContext().getRealPath("/images");
		     if (image != null) {
		         File savedir=new File(realpath);
		         if(!savedir.getParentFile().exists())
		             savedir.getParentFile().mkdirs();
		         
		             File savefile = new File(savedir, imageFileName);
		             FileUtils.copyFile(image, savefile);
		         ActionContext.getContext().put("message", "鏂囦欢涓婁紶鎴愬姛");
		     }
		     /*淇濆瓨鍥剧墖缁*/
			
		     String passwordMD5=MD5.getMD5(user.getPassword().getBytes());//
		     
			    user.setDentityPic(imageFileName);
				userService.updateUser(user);
				HttpSession session=request.getSession();
				session.setAttribute("currentUser", user);
				return "modify";
		}
	
		/**
		 * 杩涘叆浼氬憳瀵嗙爜缁存姢椤甸潰	
		 * @return
		 * @throws Exception
		 */
		public String userPassword() throws Exception {
		       request.setAttribute("mainPage", "/background/user/userPass.jsp");
		       navCode=NavUtil.genNavCode("淇敼瀵嗙爜");
		       return "userPassword";
		    
		  
		}
		
		
		/**
		 * 浼氬憳淇敼瀵嗙爜	
		 * @return
		 * @throws Exception
		 */
		public String modifyPassWord()throws Exception{
				
			String passwordMD5=MD5.getMD5(user.getPassword().getBytes());//
			if(!StringUtil.isEmpty(user.getPassword())){
				user.setPassword(passwordMD5);
			}
			User currentUser=userService.login(user);
		
			if(currentUser==null){
				error="鍘熷瘑鐮侀敊璇紒";
				return "error1";
			}else if(password1!=password2){
				error="涓ゆ瀵嗙爜涓嶄竴鑷达紒";
				return "error1";
			}
			else{
				String mpasswordMD5=MD5.getMD5(password1.getBytes());//
				user.setPassword(mpasswordMD5);
				userService.saveUser(user);
			}
			
				
				return "modify";
			
			
		}

		
		
	/*------------------nb鍒嗗壊绾�-----------------绠＄悊鍛樻搷浣滅敤鎴蜂俊鎭ā鍧�------------------nb鍒嗗壊绾�----------------*/
		
		
	/**
	 * admin鍒嗛〉鏌ヨ鐢ㄦ埛淇℃伅
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<User> userList;
		User s_user=new User();
		if(status!=null){
			 s_user.setStatus(Integer.parseInt(status));
			 userList=userService.findUserListByStatus(s_user, pageBean);
		}else{
			userList=userService.findUserList(s_user, pageBean);
		}
		long total=userService.getUserCount(s_user);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderList","productList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
		JSONArray rows=JSONArray.fromObject(userList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	/**
	 * admin鍚庡彴-淇濆瓨鐢ㄦ埛淇℃伅
	 * @return
	 * @throws Exception
	 */
	public String saveUser()throws Exception{
		userService.saveUser(user);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	public String agreeUser()throws Exception{
		User user1=userService.getUserById(user.getId());
		user1.setStatus(2);
		userService.saveUser(user1);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	public String disagreeUser()throws Exception{
		User user1=userService.getUserById(user.getId());
		user1.setStatus(1);
		userService.saveUser(user1);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 鍒犻櫎鐢ㄦ埛
	 * @return
	 * @throws Exception
	 */
	public String deleteUsers()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			
			
			if(productService.existProductWithUserId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "璇ョ敤鎴峰寘鍚晢鍝侊紝涓嶈兘鍒犻櫎");
			}else{
				User u=userService.getUserById(Integer.parseInt(idsStr[i]));
				userService.delete(u);
			}
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	

	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

}
