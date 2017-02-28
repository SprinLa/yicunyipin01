package com.yicunyipin.action;

import java.io.File;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.City;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Picture;
import com.yicunyipin.entity.Product;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.entity.ProductSmallType;
import com.yicunyipin.entity.Province;
import com.yicunyipin.entity.Town;
import com.yicunyipin.entity.User;
import com.yicunyipin.service.PictureService;
import com.yicunyipin.service.ProductAddressService;
import com.yicunyipin.service.ProductService;
import com.yicunyipin.service.UserService;
import com.yicunyipin.util.NavUtil;
import com.yicunyipin.util.PageUtil;
import com.yicunyipin.util.ResponseUtil;
import com.yicunyipin.util.StringUtil;

@Controller
public class PictureAction extends ActionSupport implements ServletRequestAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private ProductService productService;
	
	
	@Resource
	private PictureService  pictureService;
	
	private HttpServletRequest request;
	
	
	
	
	private List<Product> productList;
	private Product s_product;
	
	private List<Picture> pictureList;
	private Picture s_picture;
	
	
	
	
	
	
	private String page;
	private String rows;
	private Long total;
	private String pageCode;
	private String mainPage;
	private String navCode;
	
	private int productId;
	private Product product;
	private int proId;//vip浼氬憳缁存姢鍟嗗搧鏃秈d
	private String ids;
	private String id;
	
	private File[] image; //涓婁紶鐨勬枃浠�
	private String[] imageContentType; //鏂囦欢绫诲瀷
	private String[] imageFileName; //鏂囦欢鍚嶇О
	
	private File img;//涓婁紶鍟嗗搧鐨勫浘鐗囨枃浠�
	private String imgContentType;
	private String imgFileName;
	
	
	
	
	
	
	
	
	
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	public String getImgContentType() {
		return imgContentType;
	}
	public void setImgContentType(String imgContentType) {
		this.imgContentType = imgContentType;
	}
	public String getImgFileName() {
		return imgFileName;
	}
	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public List<Product> getProductList() {
		return productList;
	}
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
	public Product getS_product() {
		return s_product;
	}
	public void setS_product(Product s_product) {
		this.s_product = s_product;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
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
	
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	
	
	public int getProId() {
		return proId;
	}
	public void setProId(int proId) {
		this.proId = proId;
	}
	
	
	//灞曠ず鎵�湁绮惧搧鍟嗗搧
	public String showAllProduct() throws Exception {
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),12);
		if(s_product==null){
			Product s_product=new Product();
			s_product.setHot(1);
		}
		productList=productService.findProductList(s_product, pageBean);
		total=productService.getProductCount(s_product);
		StringBuffer param=new StringBuffer();
		if(s_product!=null){
			//鎸夌被鍒煡璇㈠晢鍝�
			if(s_product.getBigType()!=null){
				param.append("s_product.bigType.id="+s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				param.append("s_product.smallType.id="+s_product.getSmallType().getId());
			}
			//鎸夊湴鍧�煡璇㈠晢鍝�
			if(s_product.getProvince()!=null){
				param.append("s_product.province.pid="+s_product.getProvince().getPid());
			}
			if(s_product.getCity()!=null){
				param.append("s_product.city.cid="+s_product.getCity().getCid());
			}
			if(s_product.getTown()!=null){
				param.append("s_product.town.tid="+s_product.getTown().getTid());
			}
			
			if(s_product.getHot()!=-1){
				param.append("s_product.hot="+s_product.getHot());
			}
			
			
			if(StringUtil.isNotEmpty(s_product.getName())){
				param.append("s_product.name="+s_product.getName());
			}
		}
		pageCode=PageUtil.genPagination(request.getContextPath()+"/product.action", total, Integer.parseInt(page), 12, param.toString());
		navCode=NavUtil.genNavCode1("绮惧搧灞曠ず","鍟嗗搧鍒楄〃");
		//navCode=NavUtil.genNavCode("鍟嗗搧鍒楄〃");
		mainPage="productList.jsp";
		return super.execute();
	}
	
	
	//鏍规嵁ID鑾峰緱鍗曚釜鍟嗗搧
	public String showProduct()throws Exception{
		product=productService.getProductById(productId);
		setProductWithClick(product);
		this.saveCurrentBrowse(product);
		//saveCurrentBrowse(product);
		navCode=NavUtil.genNavCode("鍟嗗搧璇︽儏");
		mainPage="productDetails.jsp";
		return "productDetil";
	}
	
	//鏈�繎娴忚娣诲姞
	private void saveCurrentBrowse(Product product)throws Exception{
		HttpSession session=request.getSession();
		List<Product> currentBrowseProduct=(List<Product>) session.getAttribute("currentBrowse");
		if(currentBrowseProduct==null){
			currentBrowseProduct=new LinkedList<Product>();
		}
		
		boolean flag=true;
		for(Product p:currentBrowseProduct){
			if(p.getId()==product.getId()){
				flag=false;
				break;
			}
		}
		
		if(flag){
			currentBrowseProduct.add(0,product);
		}
		
		if(currentBrowseProduct.size()==5){
			currentBrowseProduct.remove(4);
		}
		
		session.setAttribute("currentBrowse", currentBrowseProduct);
	}
	
	 /*vip浼氬憳   鍟嗗搧缁存姢	*/
	public String ProductPreSave() throws Exception {
	    String gid=String.valueOf(proId);
	    System.out.println(""+proId);
	       if (StringUtil.isNotEmpty(gid)) {
	    	   product=productService.getProductById(proId);//鏍规嵁ID鑾峰彇鍟嗗搧;
	          request.setAttribute("product", product);
	       }

	      //categoryList=categeryDao.listCategory();//鑾峰彇鍟嗗搧绫诲埆;
	      //request.setAttribute("categoryList", categoryList);//鑾峰彇鍟嗗搧鍦板潃
	       request.setAttribute("mainPage", "/background/goods/goodsSave.jsp");
	       navCode=NavUtil.genNavCode("鍟嗗搧娣诲姞");
	       return "productPreSave";
	    
	  
	}
	
	
	
	//浼氬憳澧炲姞鍟嗗搧
	   public String productAdd()throws Exception{
		   User user=(User)request.getSession().getAttribute("currentUser");
		   product.setUser(user);
		   if(product.getId()!=-1){
			   product.setPublishTime(new Date());
		   }else{
			   product.setMidifyTime(new Date());
		   }
		   product.setProPic(imageFileName[0]);
		   productService.saveProduct(product);
		return id;
	   }
		   
		   //System.out.println("鍟嗗搧"+product.getId()+product.getName());
		   /*淇濆瓨鍥剧墖缁�/
			 ServletActionContext.getRequest().setCharacterEncoding("UTF-8");
		     String realpath = ServletActionContext.getServletContext().getRealPath("/images");
		     System.out.println(realpath);
		     Set<Picture> pictures=new HashSet<Picture>();
		     if (image != null) {
		         File savedir=new File(realpath);
		         if(!savedir.getParentFile().exists())
		             savedir.getParentFile().mkdirs();
		         
		         for(int i=0;i<image.length;i++){
		             File savefile = new File(savedir, imageFileName[i]);
		             FileUtils.copyFile(image[i], savefile);
		          
		             Picture pic=new Picture();
		             
		             pic.setPicName(imageFileName[i]);
		             
		             if(i==0){pic.setPicType("1");}
		             else{  pic.setPicType("2");}
		             
		             pic.setProduct(product);
		            //System.out.println("鍟嗗搧鍚嶇Оid"+pic.getProduct().getId());
		            //System.out.println("鍥剧墖"+pic.getPicName());
		           // System.out.println("鍟嗗搧鍚嶇О"+pic.getProduct().getName());
		             pictureService.savePicture(pic);
		         }
		         
		         
		         ActionContext.getContext().put("message", "鏂囦欢涓婁紶鎴愬姛");
		     }
		     /*淇濆瓨鍥剧墖缁�/
		     
		   return "productSave";	
						     
			}
	   public String productDel()throws Exception{
		   productService.deleteProduct(product);
	       boolean delFlag;
	          delFlag = true;
	          JSONObject result=new JSONObject();
				result.put("delFlag", true);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
				return null; 
	   
	   }
	   
	   
	   public String init()throws Exception{
		   User user=(User)request.getSession().getAttribute("currentUser");
		   
		   		boolean flag;
	          JSONObject result=new JSONObject();
	          if(user.getStatus()==2){
	        	  // flag = true;
	        	  result.put("success", true);
	          }
	          else{
	        	  //flag = false;
	        	  result.put("success", false);
	          }
				ResponseUtil.write(ServletActionContext.getResponse(), result);
				return null; 
	   
	   }
	  //灞曠ず浼氬憳鍟嗗搧
	   public String showProductByUser() throws Exception {
			//System.out.println("a"+ads);
			//String userIdString=String.valueOf(request.getSession().getAttribute("userid"));
			//goodsList=goodsDao.getGoods();
			//HttpSession session=request.getSession();
			User user=(User)request.getSession().getAttribute("currentUser");
			System.out.println("鐢ㄦ埛id"+user.getId());
			
			if(StringUtil.isEmpty(page)){
				page="1";
			}
			Product s_product=new Product();
			s_product.setUser(user);
			PageBean pageBean=new PageBean(Integer.parseInt(page),8);
			productList=productService.findProductList(s_product, pageBean);
			total=productService.getProductCount(s_product);
			StringBuffer param=new StringBuffer();
			if(s_product!=null){
				//鎸夌被鍒煡璇㈠晢鍝�
				if(s_product.getBigType()!=null){
					param.append("s_product.bigType.id="+s_product.getBigType().getId());
				}
				if(s_product.getSmallType()!=null){
					param.append("s_product.smallType.id="+s_product.getSmallType().getId());
				}
				//鎸夊湴鍧�煡璇㈠晢鍝�
				if(s_product.getProvince()!=null){
					param.append("s_product.province.pid="+s_product.getProvince().getPid());
				}
				if(s_product.getCity()!=null){
					param.append("s_product.city.cid="+s_product.getCity().getCid());
				}
				if(s_product.getTown()!=null){
					param.append("s_product.town.tid="+s_product.getTown().getTid());
				}
				if(s_product.getUser()!=null){
					param.append("s_product.user.id="+s_product.getUser().getId());
				}
				
				
				
				if(StringUtil.isNotEmpty(s_product.getName())){
					param.append("s_product.name="+s_product.getName());
				}
			}
			
			
			pageCode=PageUtil.genPagination(request.getContextPath()+"/product_showProductByUser.action", total, Integer.parseInt(page), 8, param.toString());
			
			request.setAttribute("backgoodsList",productList);
			 navCode=NavUtil.genNavCode("鍟嗗搧缁存姢");
			request.setAttribute("mainPage", "/background/goods/goodsList.jsp");
			return "productPreSave";
		}
	
	   
	   
	   
	   
	   
	   /**
		 * 璁剧疆鍟嗗搧鐐瑰嚮閲�
		 * @return
		 * @throws Exception
		 */
		private void setProductWithClick(Product product)throws Exception{
			productService.setProductWithClick(product);
			
		}
	
	
	
		//admin澧炲姞鍟嗗搧
		   public String save()throws Exception{
			   if(product.getId()==0){
				   product.setPublishTime(new Date());
				}
			   System.out.println("productid"+product.getId());
			   productService.saveProduct(product);
			   JSONObject result=new JSONObject();
				result.put("success", true);
				ResponseUtil.write(ServletActionContext.getResponse(), result);
				return null;   
				}
		/**
		 * admin鏌ヨ鍟嗗搧闆嗗悎
		 * @return
		 * @throws Exception
		 */
		public String list()throws Exception{
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			List<Picture> PictureList=pictureService.findPictureList(s_picture, pageBean);
			long total=pictureService.getPictureCount(s_picture);
			
			JsonConfig jsonConfig = new JsonConfig();  
			jsonConfig.setJsonPropertyFilter(new PropertyFilter() {  
			   public boolean apply(Object source, String name, Object value) {  
			   //閰嶇疆浣犲彲鑳藉嚭鐜伴�褰掔殑灞炴�  
			   if (name.equals("user") ||name.equals("pictures") ||name.equals("orderPictureList")) {  
			   return true;  
			   } else {  
			   return false;  
			   }  
			   }  
			   }
			   );  
			
			
			/*JsonConfig jsonConfig = new JsonConfig();
			
			jsonConfig.setExcludes(new String[]{"orderPictureList"});*/
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
			JSONArray rows=JSONArray.fromObject(PictureList,jsonConfig);
			JSONObject result=new JSONObject();
			result.put("rows", rows);
			result.put("total", total);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return null;
		}
		/**
		 * 鍒犻櫎鍟嗗搧
		 * @return
		 * @throws Exception
		 */
		public String delete()throws Exception{
			JSONObject result=new JSONObject();
			String []idsStr=ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				Product product=productService.getProductById(Integer.parseInt(idsStr[i]));
				productService.deleteProduct(product);								
			}
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return null;
		}
		
		
		
		

		/**
		 * 璁剧疆鍟嗗搧涓虹簿鍝�
		 * @return
		 * @throws Exception
		 */
		public String setProductWithHot()throws Exception{
			JSONObject result=new JSONObject();
			String []idsStr=ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				productService.setProductWithHot(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return null;
		}
		
		/**
		 * 璁剧疆鍟嗗搧涓烘柊鍝�
		 * @return
		 * @throws Exception
		 */
		public String setProductWithNews()throws Exception{
			JSONObject result=new JSONObject();
			String []idsStr=ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				productService.setProductWithNews(Integer.parseInt(idsStr[i]));
			}
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return null;
		}
	/*private HttpServletResponse response=ServletActionContext.getResponse();*/

	/*寰楀埌鐪佺骇鍗曚綅
    public String getProvince(){
		//ProvinceServiceBean provinceBean=new ProvinceServiceBean();
		List<Province> provinceList=ProductAddressService.findAllAddressList();
		
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
		   //锟斤拷锟斤拷锟斤拷锟斤拷艹锟斤拷值莨锟斤拷锟斤拷锟斤拷  
		   if (name.equals("bigProvince") || name.equals("cityList")||name.equals("productList")) {  
		   return true;  
		   } else {  
		   return false;  
		   }  
		   }  
		   }
		   );  
		JSONArray jsonArray=JSONArray.fromObject(provinceList,config);
		System.out.println(""+jsonArray);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	public String getCity(){
		//CityServiceBean cityBean=new CityServiceBean();
		//String pidString=String.valueOf(pid);
		//System.out.println("省id"+pidString);
		int pid1=pid;
		List<City> cityList=ProductAddressService.findAllCityList(pid1);
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
		   //锟斤拷锟斤拷锟斤拷锟斤拷艹锟斤拷值莨锟斤拷锟斤拷锟斤拷  
		   if (name.equals("province") || name.equals("townList")||name.equals("productList")) {  
		   return true;  
		   } else {  
		   return false;  
		   }  
		   }  
		   }
		   );  
		
		JSONArray jsonArray=JSONArray.fromObject(cityList,config);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String getTown(){
		//CityServiceBean cityBean=new CityServiceBean();
		//String pidString=String.valueOf(pid);
		//System.out.println("省id"+pidString);
		int cid1=cid;
		List<Town> townList=ProductAddressService.findAllTownList(cid1);
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
		   //锟斤拷锟斤拷锟斤拷锟斤拷艹锟斤拷值莨锟斤拷锟斤拷锟斤拷  
		   if (name.equals("city") || name.equals("productList")) {  
		   return true;  
		   } else {  
		   return false;  
		   }  
		   }  
		   }
		   );  
		
		JSONArray jsonArray=JSONArray.fromObject(townList,config);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;}*/
	
		
		
		
		
	
	
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}
	
	
	
}
