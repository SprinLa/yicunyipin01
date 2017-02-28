package com.yicunyipin.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.entity.ProductSmallType;
import com.yicunyipin.service.ProductBigTypeService;

@Controller
public class CategoryAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	/**
	 * 
	 */
	
	@Resource
	private ProductBigTypeService  productBigTypeService;
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Integer caId;
	
	
	
	
	 public Integer getCaId() {
		return caId;
	}



	public void setCaId(Integer caId) {
		this.caId = caId;
	}



	public String getBigType() throws Exception{
			//ProvinceServiceBean provinceBean=new ProvinceServiceBean();
			List<ProductBigType> productBigTypeList=productBigTypeService.findAllBigTypeList();
			
			JsonConfig config = new JsonConfig();  
			   config.setJsonPropertyFilter(new PropertyFilter() {  
			   public boolean apply(Object source, String name, Object value) {  
			   if ( name.equals("smallTypeList")||name.equals("productList")||name.equals("tbProductList")) {  
			   return true;  
			   } else {  
			   return false;  
			   }  
			   }  
			   }
			   );  
			JSONArray jsonArray=JSONArray.fromObject(productBigTypeList,config);
			System.out.println(""+jsonArray);
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().print(jsonArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	
	
	 public String getSmallType()throws Exception{
			int pid1=caId;
			ProductBigType productBigType=productBigTypeService.getProductBigTypeById(pid1);
			List<ProductSmallType> smallTypeList=productBigTypeService.findAllSmallTypeList(productBigType);
			JsonConfig config = new JsonConfig();  
			   config.setJsonPropertyFilter(new PropertyFilter() {  
			   public boolean apply(Object source, String name, Object value) {  
			   if (name.equals("bigType") || name.equals("productList")||name.equals("tbProductList")) {  
			   return true;  
			   } else {  
			   return false;  
			   }  
			   }  
			   }
			   );  
			
			JSONArray jsonArray=JSONArray.fromObject(smallTypeList,config);
			System.out.println(""+jsonArray);
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().print(jsonArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	 
	 
	 
	 public String androidGetBigType() throws Exception{
			Map m = new HashMap();
			//ProvinceServiceBean provinceBean=new ProvinceServiceBean();
			List<ProductBigType> productBigTypeList=productBigTypeService.findAllBigTypeList();
			
			JsonConfig config = new JsonConfig();  
			   config.setJsonPropertyFilter(new PropertyFilter() {  
			   public boolean apply(Object source, String name, Object value) {  
			   if ( name.equals("smallTypeList")||name.equals("productList")||name.equals("tbProductList")) {  
			   return true;  
			   } else {  
			   return false;  
			   }  
			   }  
			   }
			   );  
			m.put("data", productBigTypeList);
			m.put("code", "A0000");
			JSONObject jsonArray=JSONObject.fromObject(m,config);
			System.out.println(""+jsonArray);
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().print(jsonArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		}
	
	 public String androidGetSmallType()throws Exception{
			int pid1=caId;
			System.out.println("zhege jieguo "+ caId);
			Map m = new HashMap();
			ProductBigType productBigType=productBigTypeService.getProductBigTypeById(pid1);
			List<ProductSmallType> smallTypeList=productBigTypeService.findAllSmallTypeList(productBigType);
			JsonConfig config = new JsonConfig();  
			   config.setJsonPropertyFilter(new PropertyFilter() {  
			   public boolean apply(Object source, String name, Object value) {  
			   if (name.equals("bigType") || name.equals("productList")||name.equals("tbProductList")) {  
			   return true;  
			   } else {  
			   return false;  
			   }  
			   }  
			   }
			   );  
			m.put("data", smallTypeList);
			m.put("code", "A0000");
			JSONObject jsonArray=JSONObject.fromObject(m,config);
			System.out.println(""+jsonArray);
			response.setContentType("text/html;charset=UTF-8");
			try {
				response.getWriter().print(jsonArray.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;
		} 
	
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request=request;
	}

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	
	
}
