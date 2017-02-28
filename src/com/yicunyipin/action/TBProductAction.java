package com.yicunyipin.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.*;
import com.yicunyipin.service.ProductBigTypeService;
import com.yicunyipin.service.ProductSmallTypeService;
import com.yicunyipin.service.TBProductService;
import com.yicunyipin.service.TBUserService;
import com.yicunyipin.util.NavUtil;
import com.yicunyipin.util.PageUtil;
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
public class TBProductAction extends ActionSupport implements
		ServletRequestAware {

	private static final long serialVersionUID = 1L;
	@Resource
	private TBProductService tbProductService;  //商品Service

	@Resource
	private TBUserService tbUserService;		//用户Service
	public TBUserService getTbUserService() {
		return tbUserService;
	}

	public void setTbUserService(TBUserService tbUserService) {
		this.tbUserService = tbUserService;
	}

	private HttpServletRequest request;

	@Resource
	private ProductSmallTypeService ProductSmallTypeService;  //商品小类Service

	public ProductBigTypeService getProductBigTypeService() {
		return productBigTypeService;
	}

	public void setProductBigTypeService(ProductBigTypeService productBigTypeService) {
		this.productBigTypeService = productBigTypeService;
	}

	private ProductBigTypeService productBigTypeService;  //鍟嗗搧灏忕被Service
	private String page;
	private String rows;

	public TBProduct getProduct() {
		return product;
	}

	public void setProduct(TBProduct product) {
		this.product = product;
	}

	private TBProduct product;
	private String verified;//是否审核通过 1-通过 2-未通过
	private String ids;
	private String new1;//查询全部新产品的标志
	private String type;//产品类型 2-新品 1-精品
	private String pageCode;
	private String mainPage;
	private String navCode;

	public String getConditionCode() {
		return conditionCode;
	}

	public void setConditionCode(String conditionCode) {
		this.conditionCode = conditionCode;
	}

	private String conditionCode;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	private int productId;

	public List<TBProduct> getTbProductList() {
		return tbProductList;
	}

	public void setTbProductList(List<TBProduct> tbProductList) {
		this.tbProductList = tbProductList;
	}

	public String getPageCode() {
		return pageCode;
	}

	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}

	public String getMainPage() {
		return mainPage;
	}

	public void setMainPage(String mainPage) {
		this.mainPage = mainPage;
	}

	public String getNavCode() {
		return navCode;
	}

	public void setNavCode(String navCode) {
		this.navCode = navCode;
	}

	public TBProductService getTbProductService() {
		return tbProductService;
	}

	public void setTbProductService(TBProductService tbProductService) {
		this.tbProductService = tbProductService;
	}

	private List<TBProduct> tbProductList;

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

	public String getVerified() {
		return verified;
	}
	public void setVerified(String verified) {
		this.verified = verified;
	}

	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}

	public String getNew1() {
		return new1;
	}
	public void setNew1(String new1) {
		this.new1 = new1;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * 查询商品集合
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		//System.out.println("11");
		//System.out.println(verified);
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<TBProduct> productList;
		TBProduct s_product = new TBProduct();

		if(verified!=null){
			s_product.setVerified(Integer.parseInt(verified));
			//System.out.println(type);
			if(type!=null)
			{
				//System.out.println("444");
				s_product.setType(Integer.parseInt(type));
			}
			productList = tbProductService.findProductList(s_product, pageBean);
			// userList=tbUserService.findUserListByStatus(s_user, pageBean);
		}else if(new1!=null&&Integer.parseInt(new1)==1){
			s_product.setVerified(-1);
			productList = tbProductService.findNewProductList(s_product, pageBean)
;		}else{
			productList = tbProductService.findProductList(s_product, pageBean);
		}

		long total=tbProductService.getProductCountWithVerified(s_product);


		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setJsonPropertyFilter(new PropertyFilter() {
		   public boolean apply(Object source, String name, Object value) {
		   //配置你可能出现递归的属性  
		   if (name.equals("orderList") ||name.equals("productList") ||name.equals("orderProductList") || name.equals("pictures")) {
		   return true;
		   } else {
		   return false;
		   }
		   }
		   }
		   );

//		jsonConfig.registerJsonValueProcessor(Town.class, new ObjectJsonValueProcessor(new String[]{"tid","tname"}, Town.class));
//		jsonConfig.registerJsonValueProcessor(City.class, new ObjectJsonValueProcessor(new String[]{"cid","cname"}, City.class));
//		jsonConfig.registerJsonValueProcessor(Province.class, new ObjectJsonValueProcessor(new String[]{"pid","pname"}, Province.class));
		jsonConfig.registerJsonValueProcessor(ProductSmallType.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductSmallType.class));
		jsonConfig.registerJsonValueProcessor(ProductBigType.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));
		jsonConfig.registerJsonValueProcessor(Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rows=JSONArray.fromObject(productList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		System.out.println("result:"+result);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * 审核商品不通过
	 * @return
	 * @throws Exception
	 */
	public String disagreeProduct()throws Exception{
		//System.out.println("未通过原因:"+product.getVerifiedReason());
		//tbProductService.setProductWithStautsNo(product.getId());
		TBProduct s_product = tbProductService.getProductById(product.getId());
		s_product.setVerified(2);
		s_product.setVerifiedReason(product.getVerifiedReason());
		tbProductService.saveProduct(s_product);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}




	/**
	 * 审核商品通过
	 * @return
	 * @throws Exception
	 */
	public String agreeProduct()throws Exception{
		TBProduct s_product = tbProductService.getProductById(product.getId());
		s_product.setVerified(1);
		s_product.setAddTime(new Date());
		//tbProductService.setProductWithStauts(product.getId());
		tbProductService.saveProduct(s_product);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * 删除商品
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		 String idd=  (String) request.getAttribute("ids");
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
//			
			TBProduct s_product = tbProductService.getProductById(Integer.parseInt(idsStr[i]));
			tbProductService.deleteProduct(s_product);
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
	/**
	 * start kongjun
	 */
//	@Resource
	//private TBProductService tbProductService;
	
	private TBProduct tbProduct;
	
	//private HttpServletRequest request;
	
	private int type1;
	
	private int  type2;
	
	public int getType1() {
		return type1;
	}

	public void setType1(int type1) {
		this.type1 = type1;
	}

	public int getType2() {
		return type2;
	}

	public void setType2(int type2) {
		this.type2 = type2;
	}

	private File[] image; // 上传的文件
	private String[] imageContentType; // 文件类型
	private String[] imageFileName; // 文件名称

	
	
	public TBProduct getTbProduct() {
		return tbProduct;
	}

	public void setTbProduct(TBProduct tbProduct) {
		this.tbProduct = tbProduct;
	}

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

//	@Override
//	public void setServletRequest(HttpServletRequest arg0) {
//		// TODO Auto-generated method stub
//		this.request = arg0;
//	}

	public String init() throws Exception {
		TBUser user = (TBUser) request.getSession().getAttribute("currentUser");
		boolean flag;
		JSONObject result = new JSONObject();
		if ((user.getType() == 2 || user.getType() == 1) && user.getVerified() == 1) {
			result.put("success", true);
		} else {
			result.put("success", false);
		}
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 权限：会员
	 * 增加商品（id!=0时为修改）
	 * @author libing
	 * @return
	 */
   public String productAdd()throws Exception{
	   System.out.println("productAdd");
	   System.out.println(tbProduct.toString());
	   System.out.println(type1 + "ss" + type2 );
	   TBUser user=(TBUser)request.getSession().getAttribute("currentUser");
	   System.out.println("yonghu"+user.toString());
	   String pass = user.getPassword();
	   //user.setPassword(MD5.getMD5(pass.getBytes()));
	   //String newimageName=null;
	   if(user != null){
		   tbProduct.setUser(user);
		   //user.setPassword(pass);
	   }
	   else {
		   return "login";//跳转到登陆界面
	   } 
	   
	   /*保存图片组*/
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
					tbProduct.setProductPic(imageFileName[i]);
				} else if (i == 1) {
					tbProduct.setPackagePic(imageFileName[i]);
				} else {
					tbProduct.setProductAddressPic(imageFileName[i]);
				}
			}
//			Date currentTime = new Date();// 生成日期
//			SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");// 转化成到毫秒格式
//			String date = formatter.format(currentTime);
//			tbUser.setMemberTime(currentTime);
			if(user.getType() == 1){
				tbProduct.setType(2);
			}else if(user.getType() == 2){
				tbProduct.setType(1);
			}
			tbProduct.setVerified(0);
			ProductBigType big = new ProductBigType();
			big.setId(type1);
			ProductSmallType small = new ProductSmallType();
			small.setId(type2);
			tbProduct.setBigType(big);
			tbProduct.setSmallType(small);
			System.out.println(tbProduct.toString());
			ActionContext.getContext().put("message", "文件上传成功");
			tbProductService.saveProduct(tbProduct);
			navCode=NavUtil.genNavCode("产品发布结果");
			request.setAttribute("mainPage", "/background/success.jsp");
			return "productSave";
		}else{
			navCode=NavUtil.genNavCode("产品发布结果");
			request.setAttribute("mainPage", "/background/fail.jsp");
			return "productSave";
		}
	     /*保存图片组*/
			
					     
		}
   
   /**
	 * 权限：会员
	 * 增加商品（id!=0时为修改）
	 * @author libing
	 * @return
	 */
  public void androidProductAdd()throws Exception{
	  Map m = new HashMap<>();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
	  
	   System.out.println("productAdd");
	   System.out.println(tbProduct.toString());
	   System.out.println(type1 + "ss" + type2 );
	   TBUser user=(TBUser)request.getSession().getAttribute("currentUser");
	   System.out.println("yonghu"+user.toString());
	   String pass = user.getPassword();
	   //user.setPassword(MD5.getMD5(pass.getBytes()));
	   //String newimageName=null;
	   if(user != null){
		   tbProduct.setUser(user);
		   //user.setPassword(pass);
		   /*保存图片组*/
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
						tbProduct.setProductPic(imageFileName[i]);
					} else if (i == 1) {
						tbProduct.setPackagePic(imageFileName[i]);
					} else {
						tbProduct.setProductAddressPic(imageFileName[i]);
					}
				}
//				Date currentTime = new Date();// 生成日期
//				SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");// 转化成到毫秒格式
//				String date = formatter.format(currentTime);
//				tbUser.setMemberTime(currentTime);
				if(user.getType() == 1){
					tbProduct.setType(2);
				}else if(user.getType() == 2){
					tbProduct.setType(1);
				}
				tbProduct.setVerified(0);
				ProductBigType big = new ProductBigType();
				big.setId(type1);
				ProductSmallType small = new ProductSmallType();
				small.setId(type2);
				tbProduct.setBigType(big);
				tbProduct.setSmallType(small);
				System.out.println(tbProduct.toString());
				ActionContext.getContext().put("message", "文件上传成功");
				tbProductService.saveProduct(tbProduct);
				
				m.put("code", "A0000");
				m.put("data", 1);
				
				
			}else{
				m.put("code", "A0000");
				m.put("data", 0);
				m.put("msg", "图片错误");
			}
		     /*保存图片组*/
	   }
	   else {
		   //return "login";//跳转到登陆界面
		   m.put("code", "A0000");
		   m.put("data", 0);
		   m.put("msg", "请登录");
	   } 
	   
	   
	   JSONObject jsonObject = JSONObject.fromObject(m);
		System.out.println(jsonObject.toString());
		out.println(jsonObject.toString());	
					     
	}
  
  /**
	 * 权限：会员
	 * 进入增加（修改）商品界面
	 * @author libing
	 * @return
	 */
	public String ProductPreSave() throws Exception {
//	       String gid=String.valueOf(proId);
//	       if (StringUtil.isNotEmpty(gid)) {
//	    	   product=productService.getProductById(proId);//根据ID获取商品;
//	          request.setAttribute("product", product);
//	       }
	       request.setAttribute("mainPage", "/background/goods/goodsSave.jsp");
	       navCode=NavUtil.genNavCode("商品添加");
	       return "productPreSave";
	}

	/**
	 * end kongjun
	 */

	public String showProducts() throws Exception {

		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),24);
		if(product==null){
			product=new TBProduct();
		}
		product.setVerified(1); //展示的必须是审核通过的
		tbProductList=tbProductService.findProductList(product, pageBean);
		long total=tbProductService.getProductCount(product);
		//拼接分页是的参数例如product_showProduct.action?productId=39&&product.bigType.id=
		String param=this.getUrlParam(product);
		pageCode= PageUtil.genPagination(request.getContextPath()+"/product_showProducts.action", total, Integer.parseInt(page), 24, param);
		if(product.getType()==0){
			navCode= NavUtil.genNavCode1("购物服务","订单服务");
			conditionCode = "大地鲜订单服务";
		}
		else if(product.getType()==1){
			navCode=NavUtil.genNavCode1("精品展示","商品列表");
			conditionCode = "大地鲜精品列表";
		}
		else if(product.getType()==2){
			navCode=NavUtil.genNavCode1("新品展示","商品列表");
			conditionCode = "大地鲜新品列表";
		}

		if(product.getSmallType()!=null){
			ProductSmallType pST=ProductSmallTypeService.getProductSmallTypeById(product.getSmallType().getId());
			String pSTName=pST.getName();
			System.out.println("当前选中的产品种类是："+pSTName);
			navCode=NavUtil.genNavCode1(pSTName,"商品列表");
			ProductBigType pBT= productBigTypeService.getProductBigTypeById(product.getBigType().getId());
			conditionCode = pBT.getName()+":"+pSTName;
		}
		mainPage="productList2.jsp";
		return super.execute();
	}
	/**
	 * 根据ID获得单个商品，传递到订购页面
	 * @author libing
	 * @return
	 */
	public String findProduct()throws Exception{
		tbProduct=tbProductService.getProductById(productId);
		this.saveCurrentBrowse(tbProduct);     //保存到最近浏览上
		HttpSession session=request.getSession();
		session.setAttribute("product", tbProduct);
		navCode=NavUtil.genNavCode("订单填写");
		mainPage="orderFill.jsp";
		return "orderFill";
	}
	public String showProduct()throws Exception{
		tbProduct=tbProductService.getProductById(productId);
		this.saveCurrentBrowse(tbProduct);     //保存到最近浏览上
		navCode=NavUtil.genNavCode("商品详情");
		mainPage="productDetails.jsp";
		return "productDetil";
	}



	private String getUrlParam(TBProduct s_product){

		StringBuffer param=new StringBuffer();
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				param.append("s_product.bigType.id="+s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				param.append("s_product.smallType.id="+s_product.getSmallType().getId());
			}

			if(s_product.getType()>=0&&s_product.getType()<3){
				param.append("s_product.type="+s_product.getType());
			}

			if(s_product.getVerified()>=0&&s_product.getVerified()<3){
				param.append("s_product.verified="+s_product.getVerified());
			}

			if(s_product.getUser() != null && StringUtil.isNotEmpty(s_product.getUser().getMemberName())){
				param.append("s_product.user.memberName="+s_product.getUser().getMemberName());
			}
		}
		return param.toString();
	}

	     /**
		 * 根据ID获得单个商品，并将商品增加到“最近浏览”模块
		 * @author libing
		 * @return
		 */
		private void saveCurrentBrowse(TBProduct product)throws Exception{
			HttpSession session=request.getSession();
			List<TBProduct> currentBrowseProduct=(List<TBProduct>) session.getAttribute("currentBrowse");
			if(currentBrowseProduct==null){
				currentBrowseProduct=new LinkedList<TBProduct>();
			}
			Iterator<TBProduct> it = currentBrowseProduct.iterator();
			while (it.hasNext()){
				TBProduct p = it.next();
				if(p.getId()==product.getId()){
					it.remove();
					//flag=false;
					break;
				}

			}
			currentBrowseProduct.add(0,product);
			if(currentBrowseProduct.size()==6){
				currentBrowseProduct.remove(5);
			}
			session.setAttribute("currentBrowse", currentBrowseProduct);
		}
	/*
	end wxy
	 */
}
