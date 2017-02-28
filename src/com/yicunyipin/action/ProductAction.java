//package com.yicunyipin.action;
//
//import java.io.File;
//import java.text.SimpleDateFormat;
//import java.util.Date;
//import java.util.HashSet;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Random;
//import java.util.Set;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//import net.sf.json.JSONArray;
//import net.sf.json.JSONObject;
//import net.sf.json.JsonConfig;
//import net.sf.json.util.PropertyFilter;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.struts2.ServletActionContext;
//import org.apache.struts2.interceptor.ServletRequestAware;
//import org.springframework.stereotype.Controller;
//
//import com.opensymphony.xwork2.ActionContext;
//import com.opensymphony.xwork2.ActionSupport;
//import com.yicunyipin.entity.City;
//import com.yicunyipin.entity.PageBean;
//import com.yicunyipin.entity.Picture;
//import com.yicunyipin.entity.Product;
//import com.yicunyipin.entity.ProductBigType;
//import com.yicunyipin.entity.ProductSmallType;
//import com.yicunyipin.entity.Province;
//import com.yicunyipin.entity.Town;
//import com.yicunyipin.entity.User;
//import com.yicunyipin.service.PictureService;
//import com.yicunyipin.service.ProductAddressService;
//import com.yicunyipin.service.ProductService;
//import com.yicunyipin.service.ProductSmallTypeService;
//import com.yicunyipin.service.UserService;
//import com.yicunyipin.util.NavUtil;
//import com.yicunyipin.util.PageUtil;
//import com.yicunyipin.util.ResponseUtil;
//import com.yicunyipin.util.StringUtil;
//
//
///**
// * 鍏充簬鍟嗗搧璇锋眰action
// * @author libing
// *
// */
//
//@Controller
//public class ProductAction extends ActionSupport implements ServletRequestAware{
//
//	
//	private static final long serialVersionUID = 1L;
//
//	
//	
//	@Resource
//	private ProductService productService;  //鍟嗗搧Service
//
//	@Resource
//	private UserService userService;		//鐢ㄦ埛Service
//	
//	@Resource
//	private ProductAddressService ProductAddressService; //鍟嗗搧鍦板潃Service
//	
//	@Resource
//	private PictureService  pictureService;  //鍟嗗搧鍥剧墖Service
//	
//	private HttpServletRequest request;
//	
//	@Resource
//	private ProductSmallTypeService ProductSmallTypeService;  //鍟嗗搧Service
//	
//	private List<Product> productList;
//	private Product s_product;
//	
//	private String page;
//	private String rows;
//	private Long   total;//鍟嗗搧鎬绘暟
//	private Long   okTotal;//鍟嗗搧鎬绘暟
//	private Long   noTotal;//鍟嗗搧鎬绘暟
//	
//	private String pageCode;
//	private String mainPage;
//	private String navCode;
//	
//	private int productId;
//	private Product product;
//	private int proId;//vip浼氬憳缁存姢鍟嗗搧鏃秈d
//	private String ids;
//	private String id;
//	
//	private File[] image; //涓婁紶鐨勬枃浠�
//	private String[] imageContentType; //鏂囦欢绫诲瀷
//	private String[] imageFileName; //鏂囦欢鍚嶇О
//	
//	private File img;//涓婁紶鍟嗗搧鐨勫浘鐗囨枃浠�
//	private String imgContentType;
//	private String imgFileName;
//	
//	
//	public Long getOkTotal() {
//		return okTotal;
//	}
//	public void setOkTotal(Long okTotal) {
//		this.okTotal = okTotal;
//	}
//	public Long getNoTotal() {
//		return noTotal;
//	}
//	public void setNoTotal(Long noTotal) {
//		this.noTotal = noTotal;
//	}
//	public File getImg() {
//		return img;
//	}
//	public void setImg(File img) {
//		this.img = img;
//	}
//	public String getImgContentType() {
//		return imgContentType;
//	}
//	public void setImgContentType(String imgContentType) {
//		this.imgContentType = imgContentType;
//	}
//	public String getImgFileName() {
//		return imgFileName;
//	}
//	public void setImgFileName(String imgFileName) {
//		this.imgFileName = imgFileName;
//	}
//	public File[] getImage() {
//		return image;
//	}
//	public void setImage(File[] image) {
//		this.image = image;
//	}
//	public String[] getImageContentType() {
//		return imageContentType;
//	}
//	public void setImageContentType(String[] imageContentType) {
//		this.imageContentType = imageContentType;
//	}
//	public String[] getImageFileName() {
//		return imageFileName;
//	}
//	public void setImageFileName(String[] imageFileName) {
//		this.imageFileName = imageFileName;
//	}
//	public String getId() {
//		return id;
//	}
//	public void setId(String id) {
//		this.id = id;
//	}
//	public String getIds() {
//		return ids;
//	}
//	public void setIds(String ids) {
//		this.ids = ids;
//	}
//	public List<Product> getProductList() {
//		return productList;
//	}
//	public String getRows() {
//		return rows;
//	}
//	public void setRows(String rows) {
//		this.rows = rows;
//	}
//	public void setProductList(List<Product> productList) {
//		this.productList = productList;
//	}
//	public Product getS_product() {
//		return s_product;
//	}
//	public void setS_product(Product s_product) {
//		this.s_product = s_product;
//	}
//	public String getPage() {
//		return page;
//	}
//	public void setPage(String page) {
//		this.page = page;
//	}
//	public Long getTotal() {
//		return total;
//	}
//	public void setTotal(Long total) {
//		this.total = total;
//	}
//	public String getPageCode() {
//		return pageCode;
//	}
//	public void setPageCode(String pageCode) {
//		this.pageCode = pageCode;
//	}
//	public String getMainPage() {
//		return mainPage;
//	}
//	public void setMainPage(String mainPage) {
//		this.mainPage = mainPage;
//	}
//	public String getNavCode() {
//		return navCode;
//	}
//	public void setNavCode(String navCode) {
//		this.navCode = navCode;
//	}
//	
//	
//	public int getProductId() {
//		return productId;
//	}
//	public void setProductId(int productId) {
//		this.productId = productId;
//	}
//	public Product getProduct() {
//		return product;
//	}
//	public void setProduct(Product product) {
//		this.product = product;
//	}
//	
//	
//	public int getProId() {
//		return proId;
//	}
//	public void setProId(int proId) {
//		this.proId = proId;
//	}
//
//	
//	
//		/**
//		 * 鍓嶅彴鏍规嵁鏉′欢鑾峰緱鍟嗗搧锛堢簿鍝併�鏂板搧鐢ㄤ笂锛�
//		 * @author libing
//		 * @return
//		 */
//		public String showProducts() throws Exception {
//			
//			if(StringUtil.isEmpty(page)){
//				page="1";
//			}
//			PageBean pageBean=new PageBean(Integer.parseInt(page),24);
//			s_product.setStatus(2);         //灞曠ず鐨勫繀椤绘槸瀹℃牳閫氳繃鐨�
//			productList=productService.findProductList(s_product, pageBean);
//			total=productService.getProductCount(s_product);
//			//鎷兼帴鍒嗛〉鏄殑鍙傛暟渚嬪product_showProduct.action?productId=39&&s_product.bigType.id=
//			String param=this.getUrlParam(s_product);
//			pageCode=PageUtil.genPagination(request.getContextPath()+"/product_showProducts.action", total, Integer.parseInt(page), 24, param);
//			if(s_product.getHot()==1){
//				navCode=NavUtil.genNavCode1("鏂板搧灞曠ず","鍟嗗搧鍒楄〃");
//			}else{
//				navCode=NavUtil.genNavCode1("绮惧搧灞曠ず","鍟嗗搧鍒楄〃");
//			}
//			
//			if(s_product.getSmallType()!=null){
//				ProductSmallType pST=ProductSmallTypeService.getProductSmallTypeById(s_product.getSmallType().getId());
//				String pSTName=pST.getName();
//				System.out.println("褰撳墠閫変腑鐨勪骇鍝佺绫绘槸锛�"+pSTName);
//				navCode=NavUtil.genNavCode1(pSTName,"鍟嗗搧鍒楄〃");
//			}
//			
//			if(s_product.getProvince()!=null){
//				Province p=ProductAddressService.getProvinceById(s_product.getProvince().getPid());
//				String proName=p.getPname();
//				navCode=NavUtil.genNavCode1(proName,"鍟嗗搧鍒楄〃");
//			}
//			mainPage="productList.jsp";
//			return super.execute();
//		}
//	
//
//	/**
//	 * 鍓嶅彴鑾峰彇閲戣川浼氬憳鍚嶅崟
//	 * @author libing
//	 * @return
//	 */
//	public String showGProduct() throws Exception {
//		
//		if(StringUtil.isEmpty(page)){
//			page="1";
//		}
//		
//		PageBean pageBean=new PageBean(Integer.parseInt(page),10);
//		
//		Product s_product1=new Product();
//		ProductBigType bt=new ProductBigType();
//		bt.setId(5);
//		s_product1.setBigType(bt);
//		productList=productService.findProductList(s_product1, pageBean);
//		total=productService.getProductCount(s_product1);
//		StringBuffer param=new StringBuffer();
//		if(s_product1!=null){
//			//鎸夌被鍒煡璇㈠晢鍝�
//			if(s_product1.getBigType()!=null){
//				param.append("s_product1.bigType.id="+s_product1.getBigType().getId());
//			}
//		}
//		pageCode=PageUtil.genPagination(request.getContextPath()+"/product_showGProduct.action", total, Integer.parseInt(page), 10, param.toString());
//		navCode=NavUtil.genNavCode1("閲戣川浼氬憳","浼氬憳鍒楄〃");
//		mainPage="huiyList.jsp";
//		return "huiyuan";
//	}
//	
//	
//	
//	/**
//	 * 鏍规嵁ID鑾峰緱鍗曚釜鍟嗗搧锛屽苟灏嗗晢鍝佺偣鍑婚噺澧炲姞1
//	 * @author libing
//	 * @return
//	 */
//	public String showProduct()throws Exception{
//		product=productService.getProductById(productId);
//		setProductWithClick(product);    //鐐瑰嚮閲忓鍔�
//		this.saveCurrentBrowse(product);     //淇濆瓨鍒版渶杩戞祻瑙堜笂
//		navCode=NavUtil.genNavCode("鍟嗗搧璇︽儏");
//		mainPage="productDetails.jsp";
//		return "productDetil";
//	}
//	
//	
//	/*----------------------浼氬憳鎿嶄綔妯″潡寮�---------------------------*/
//	
//		/**
//		 * 鏉冮檺锛氫細鍛�
//		 * 澧炲姞鍟嗗搧锛坕d!=0鏃朵负淇敼锛�
//		 * @author libing
//		 * @return
//		 */
//	   public String productAdd()throws Exception{
//		   
//		   User user=(User)request.getSession().getAttribute("currentUser");
//		   String newimageName=null;
//		   if(user!=null){
//			   product.setUser(user);
//		   }
//		   else {
//			   return "login";//璺宠浆鍒扮櫥闄嗙晫闈�
//		   }
//		   if(product!=null){
//			   Date currentTime = new Date();
//			   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//			   String dateString = formatter.format(currentTime);
//			   Random ran = new Random();
//			    int rannum= ran.nextInt(9999);
//			    newimageName=dateString+rannum+imageFileName[0];
//			   System.out.println("鍥剧墖鍚�"+newimageName);
//			   product.setProPic(newimageName);//璁剧疆鍟嗗搧棣栭〉灞曠ず鍥剧墖
//			   if(product.getId()!=0){
//				   product.setMidifyTime(new Date());
//				   productService.modifyProduct(product);
//			   }
//			   else{
//				   product.setPublishTime(new Date());
//				   product.setMidifyTime(new Date());
//				   productService.saveProduct(product);
//			   }
//		   }
//		  
//		   
//		   
//		   /*淇濆瓨鍥剧墖缁�/
//			 ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
//		     String realpath = ServletActionContext.getServletContext().getRealPath("/images");
//		     Set<Picture> pictures=new HashSet<Picture>();
//		     if (image != null) {
//		         File savedir=new File(realpath);
//		         if(!savedir.getParentFile().exists())
//		             savedir.getParentFile().mkdirs();
//		         
//		         for(int i=0;i<image.length;i++){
//		        	 
//		        	 if(i==0){
//		        		 imageFileName[i]=newimageName;
//		        		
//		        	 }
//		        	 
//		        	 else{
//		        		 
//		        	   Date currentTime = new Date();//鐢熸垚鏃ユ湡
//		  			   SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");//杞寲鎴愬埌姣鏍煎紡
//		  			   String dateString = formatter.format(currentTime);
//		  			   Random ran = new Random();
//		  			   int rannum= ran.nextInt(9999);//鐢熸垚闅忔満鏁�
//		  			   imageFileName[i]=dateString+rannum+imageFileName[i];//鎷煎噾鏂扮殑鍥剧墖鍚嶇О
//		        		 
//		        	 }
//		        	 
//		        	 
//		        	 File savefile = new File(savedir, imageFileName[i]);
//	        		 FileUtils.copyFile(image[i], savefile);
//	        		 
//	        		 Picture pic=new Picture();
//	        		 
//	        		 pic.setPicName(imageFileName[i]);
//	        		 
//	        		 if(i==0){pic.setPicType("1");}
//	        		 else{  pic.setPicType("2");}
//	        		 
//	        		 pic.setProduct(product);
//	        		 pictureService.savePicture(pic);
//		         }
//		         ActionContext.getContext().put("message", "鏂囦欢涓婁紶鎴愬姛");
//		     }
//		     
//		     /*淇濆瓨鍥剧墖缁�/
//		   return "productSave";	
//						     
//			}
//	   
//	   /**
//		 * 鏉冮檺锛氫細鍛�
//		 * 杩涘叆澧炲姞锛堜慨鏀癸級鍟嗗搧鐣岄潰
//		 * @author libing
//		 * @return
//		 */
//		public String ProductPreSave() throws Exception {
//		       String gid=String.valueOf(proId);
//		       if (StringUtil.isNotEmpty(gid)) {
//		    	   product=productService.getProductById(proId);//鏍规嵁ID鑾峰彇鍟嗗搧;
//		          request.setAttribute("product", product);
//		       }
//		       request.setAttribute("mainPage", "/background/goods/goodsSave.jsp");
//		       navCode=NavUtil.genNavCode("鍟嗗搧娣诲姞");
//		       return "productPreSave";
//		}
//		
//		
//		
//		/**
//		 * 鏉冮檺锛氫細鍛�
//		 * 杩涘叆澧炲姞锛堜慨鏀癸級鍟嗗搧鐣岄潰
//		 * @author libing
//		 * @return
//		 */
//		public String productModify() throws Exception {
//		       String gid=String.valueOf(proId);
//		       if (StringUtil.isNotEmpty(gid)) {
//		    	   product=productService.getProductById(proId);//鏍规嵁ID鑾峰彇鍟嗗搧;
//		          request.setAttribute("product", product);
//		       }
//		       request.setAttribute("mainPage", "/background/goods/goodsModify.jsp");
//		       navCode=NavUtil.genNavCode("鍟嗗搧淇敼");
//		       return "productmodify";
//		}
//	   
//	   
//	   /**
//		 * 鏉冮檺锛氫細鍛�
//		 * 鍒犻櫎鍟嗗搧
//		 * @author libing
//		 * @return
//		 */
//	   public String productDelById()throws Exception{
//		   
//		   String idd=  (String) request.getAttribute("id");
//		   String idd1= request.getParameter("id");
//		   Product  product=new Product();
//		   product=productService.getProductById(Integer.parseInt(id));
//		   productService.deleteProduct(product);
//	       //boolean delFlag;
//	         // delFlag = true;
//	          JSONObject result=new JSONObject();
//				result.put("success", true);
//				ResponseUtil.write(ServletActionContext.getResponse(), result);
//				return null;
//				
//				
//		/*String idd=  (String) request.getAttribute("ids");
//		JSONObject result=new JSONObject();
//		String []idsStr=ids.split(",");
//		for(int i=0;i<idsStr.length;i++){
//			Product product=productService.getProductById(Integer.parseInt(idsStr[i]));
//			productService.deleteProduct(product);								
//		}
//		result.put("success", true);
//		ResponseUtil.write(ServletActionContext.getResponse(), result);
//		return null;	*/		
//				
//				
//	   }
//	 
//	   
//	   
//	   
//	   
//	   
//	   /**
//		 * 鏉冮檺锛氫細鍛�
//		 * 鑾峰緱鐢ㄦ埛鐨勬墍鏈夊晢鍝�
//		 * @author libing
//		 * @return
//		 */
//	   public String showProductByUser() throws Exception {
//			User user=(User)request.getSession().getAttribute("currentUser");
//			
//			if(StringUtil.isEmpty(page)){
//				page="1";
//			}
//			if(s_product==null){
//				s_product=new Product();
//			}
//			s_product.setUser(user);
//			PageBean pageBean=new PageBean(Integer.parseInt(page),8);
//			productList=productService.findProductList(s_product, pageBean);
//			
//			Product p=new Product();
//			p.setUser(user);
//			p.setStatus(4);
//			total=productService.getProductCount(p);
//			
//			
//			Product p1=new Product();
//			p1.setUser(user);
//			p1.setStatus(2);
//			okTotal=productService.getProductCount(p1);
//			
//			Product p2=new Product();
//			p2.setUser(user);
//			p2.setStatus(1);
//			noTotal=productService.getProductCount(p2);
//			
//			String param=this.getUrlParam(s_product);
//			pageCode=PageUtil.genPagination(request.getContextPath()+"/product_showProductByUser.action", total, Integer.parseInt(page), 8, param);
//			request.setAttribute("backgoodsList",productList);
//			 navCode=NavUtil.genNavCode("鍟嗗搧缁存姢");
//			request.setAttribute("mainPage", "/background/goods/goodsList.jsp");
//			return "productPreSave";
//		} 
//	   
//	   
//	   
///*----------------------浼氬憳鎿嶄綔妯″潡缁撴潫---------------------------*/
//	   
//	   
//	   public String init()throws Exception{
//		   User user=(User)request.getSession().getAttribute("currentUser");
//		   		boolean flag;
//	          JSONObject result=new JSONObject();
//	          if(user.getStatus()==2){
//	        	  result.put("success", true);
//	          }
//	          else{
//	        	  result.put("success", false);
//	          }
//				ResponseUtil.write(ServletActionContext.getResponse(), result);
//				return null; 
//	   }
//	
//	   
//	   
//	   
//	   
//	 /*----------------------绠＄悊鍛樻搷浣滄ā鍧楀紑濮�--------------------------*/	  
//	   
//	   	/**
//		 * 澧炲姞鍟嗗搧
//		 * @return
//		 * @throws Exception
//		 */
//	   public String save()throws Exception{
//		   if(product.getId()==0){
//			   product.setPublishTime(new Date());
//			}
//		   productService.saveProduct(product);
//		   JSONObject result=new JSONObject();
//			result.put("success", true);
//			ResponseUtil.write(ServletActionContext.getResponse(), result);
//			return null;   
//		}
//	   
//		/**
//		 * 鏌ヨ鍟嗗搧闆嗗悎
//		 * @return
//		 * @throws Exception
//		 */
//		public String list()throws Exception{
//			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
//			List<Product> productList=productService.findProductList(s_product, pageBean);
//			long total=productService.getProductCount(s_product);
//			
//			JsonConfig jsonConfig = new JsonConfig();  
//			jsonConfig.setJsonPropertyFilter(new PropertyFilter() {  
//			   public boolean apply(Object source, String name, Object value) {  
//			   //閰嶇疆浣犲彲鑳藉嚭鐜伴�褰掔殑灞炴�  
//			   if (name.equals("orderList") ||name.equals("productList") ||name.equals("orderProductList") || name.equals("pictures")) {  
//			   return true;  
//			   } else {  
//			   return false;  
//			   }  
//			   }  
//			   }
//			   );  
//			
//			jsonConfig.registerJsonValueProcessor(Town.class, new ObjectJsonValueProcessor(new String[]{"tid","tname"}, Town.class));
//			jsonConfig.registerJsonValueProcessor(City.class, new ObjectJsonValueProcessor(new String[]{"cid","cname"}, City.class));
//			jsonConfig.registerJsonValueProcessor(Province.class, new ObjectJsonValueProcessor(new String[]{"pid","pname"}, Province.class));
//			jsonConfig.registerJsonValueProcessor(ProductSmallType.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductSmallType.class));
//			jsonConfig.registerJsonValueProcessor(ProductBigType.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, ProductBigType.class));
//			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
//			JSONArray rows=JSONArray.fromObject(productList,jsonConfig);
//			JSONObject result=new JSONObject();
//			result.put("rows", rows);
//			result.put("total", total);
//			System.out.println("result1:"+result);
//			ResponseUtil.write(ServletActionContext.getResponse(), result);
//			return null;
//		}
//		
//		/**
//		 * 鍒犻櫎鍟嗗搧
//		 * @return
//		 * @throws Exception
//		 */
//		public String delete()throws Exception{
//			 String idd=  (String) request.getAttribute("ids");
//			JSONObject result=new JSONObject();
//			String []idsStr=ids.split(",");
//			for(int i=0;i<idsStr.length;i++){
//				Product product=productService.getProductById(Integer.parseInt(idsStr[i]));
//				productService.deleteProduct(product);								
//			}
//			result.put("success", true);
//			ResponseUtil.write(ServletActionContext.getResponse(), result);
//			return null;
//		}
//		
//
//		/**
//		 * 璁剧疆鍟嗗搧涓虹簿鍝�
//		 * @return
//		 * @throws Exception
//		 */
//		public String setProductWithHot()throws Exception{
//			JSONObject result=new JSONObject();
//			String []idsStr=ids.split(",");
//			for(int i=0;i<idsStr.length;i++){
//				productService.setProductWithHot(Integer.parseInt(idsStr[i]));
//			}
//			result.put("success", true);
//			ResponseUtil.write(ServletActionContext.getResponse(), result);
//			return null;
//		}
//		
//		/**
//		 * 璁剧疆鍟嗗搧涓烘柊鍝�
//		 * @return
//		 * @throws Exception
//		 */
//		public String setProductWithNews()throws Exception{
//			JSONObject result=new JSONObject();
//			String []idsStr=ids.split(",");
//			for(int i=0;i<idsStr.length;i++){
//				productService.setProductWithNews(Integer.parseInt(idsStr[i]));
//			}
//			result.put("success", true);
//			ResponseUtil.write(ServletActionContext.getResponse(), result);
//			return null;
//		}
//		
//		/**
//		 * 瀹℃牳鍟嗗搧閫氳繃
//		 * @return
//		 * @throws Exception
//		 */
//		public String agreeProduct()throws Exception{
//			productService.setProductWithStauts(product.getId());
//			JSONObject result=new JSONObject();
//			result.put("success", true);
//			ResponseUtil.write(ServletActionContext.getResponse(), result);
//			return null;
//		}
//		
//		/**
//		 * 瀹℃牳鍟嗗搧涓嶉�杩�
//		 * @return
//		 * @throws Exception
//		 */
//		public String disagreeProduct()throws Exception{
//			productService.setProductWithStautsNo(product.getId());
//			JSONObject result=new JSONObject();
//			result.put("success", true);
//			ResponseUtil.write(ServletActionContext.getResponse(), result);
//			return null;
//		}
//		
//		
//		
// /*----------------------绠＄悊鍛樻搷浣滄ā鍧楃粨鏉�--------------------------*/	  	
//		
//		
//
//		
///*----------------------鍏叡鎿嶄綔鏂规硶鎿嶄綔妯″潡寮�---------------------------*/	  	
//		
//		
//		/**
//		 * 鏍规嵁ID鑾峰緱鍗曚釜鍟嗗搧锛屽苟灏嗗晢鍝佸鍔犲埌鈥滄渶杩戞祻瑙堚�妯″潡
//		 * @author libing
//		 * @return
//		 */
//		private void saveCurrentBrowse(Product product)throws Exception{
//			HttpSession session=request.getSession();
//			List<Product> currentBrowseProduct=(List<Product>) session.getAttribute("currentBrowse");
//			if(currentBrowseProduct==null){
//				currentBrowseProduct=new LinkedList<Product>();
//			}
//			boolean flag=true;
//			for(Product p:currentBrowseProduct){
//				if(p.getId()==product.getId()){
//					flag=false;
//					break;
//				}
//			}
//			if(flag){
//				currentBrowseProduct.add(0,product);
//			}
//			if(currentBrowseProduct.size()==5){
//				currentBrowseProduct.remove(4);
//			}
//			session.setAttribute("currentBrowse", currentBrowseProduct);
//		}	
//	
//	@Override
//	public void setServletRequest(HttpServletRequest request) {
//		// TODO Auto-generated method stub
//		this.request=request;
//	}
//	
//	
//	
//	  /**
//			 * 璁剧疆鍟嗗搧鐐瑰嚮閲�
//			 * @return
//			 * @throws Exception
//			 */
//			private void setProductWithClick(Product product)throws Exception{
//				productService.setProductWithClick(product);
//				
//			}
//	private String getUrlParam(Product s_product){
//		
//		StringBuffer param=new StringBuffer();
//		if(s_product!=null){
//			if(s_product.getBigType()!=null){
//				param.append("s_product.bigType.id="+s_product.getBigType().getId());
//			}
//			if(s_product.getSmallType()!=null){
//				param.append("s_product.smallType.id="+s_product.getSmallType().getId());
//			}
//			if(s_product.getProvince()!=null){
//				param.append("s_product.province.pid="+s_product.getProvince().getPid());
//			}
//			if(s_product.getCity()!=null){
//				param.append("s_product.city.cid="+s_product.getCity().getCid());
//			}
//			if(s_product.getTown()!=null){
//				param.append("s_product.town.tid="+s_product.getTown().getTid());
//			}
//			
//			if(s_product.getHot()>=0&&s_product.getHot()<3){
//				param.append("s_product.hot="+s_product.getHot());
//			}
//			
//			if(s_product.getStatus()>=0&&s_product.getStatus()<3){
//				param.append("s_product.status="+s_product.getStatus());
//			}
//			
//			if(StringUtil.isNotEmpty(s_product.getName())){
//				param.append("s_product.name="+s_product.getName());
//			}
//		}
//		return param.toString();
//	} 
//	
//	
//}
