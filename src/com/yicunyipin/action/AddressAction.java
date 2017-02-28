package com.yicunyipin.action;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.BigProvince;
import com.yicunyipin.entity.City;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Province;
import com.yicunyipin.entity.Town;
import com.yicunyipin.service.ProductAddressService;
import com.yicunyipin.service.ProductService;
import com.yicunyipin.util.ResponseUtil;

@Controller
public class AddressAction extends ActionSupport implements ServletRequestAware,ServletResponseAware {

	/**
	 * 
	 */
	
	@Resource
	private ProductService productService;
	
	@Resource
	private ProductAddressService  ProductAddressService;
	
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpServletResponse response;
	private Integer pid;
	private Integer cid;
	private Integer tid;
	private Province province;
	private Province s_province;
	private City city;
	private City s_city;
	private Town town;
	private Town s_town;
	private String page;
	private String rows;
	private String ids;
	
	
	
	
	
	
	public String getIds() {
		return ids;
	}

	public void setIds(String ids) {
		this.ids = ids;
	}

	public Province getProvince() {
		return province;
	}

	public void setProvince(Province province) {
		this.province = province;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Town getTown() {
		return town;
	}

	public void setTown(Town town) {
		this.town = town;
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

	public Province getS_province() {
		return s_province;
	}

	public void setS_province(Province s_province) {
		this.s_province = s_province;
	}

	public City getS_city() {
		return s_city;
	}

	public void setS_city(City s_city) {
		this.s_city = s_city;
	}

	public Town getS_town() {
		return s_town;
	}

	public void setS_town(Town s_town) {
		this.s_town = s_town;
	}

	

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	
	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}

	
	
	/*private HttpServletResponse response=ServletActionContext.getResponse();*/

	
	
	//��Ա
    public String getProvince1() throws Exception{
		List<Province> provinceList=ProductAddressService.findAllProvinceList();
		
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
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
    
    
    
    public String getCitys()throws Exception{
		List<City> cityList=ProductAddressService.findAllCityList(s_province);
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
		   if (name.equals("province") || name.equals("townList")||name.equals("productList")) {  
		   return true;  
		   } else {  
		   return false;  
		   }  
		   }  
		   }
		   );  
		
		JSONArray jsonArray=JSONArray.fromObject(cityList,config);
		System.out.println(""+jsonArray);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String getTowns() throws Exception{
		
		List<Town> townList=ProductAddressService.findAllTownList(s_city);
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
		   if (name.equals("city") || name.equals("productList")) {  
		   return true;  
		   } else {  
		   return false;  
		   }  
		   }  
		   }
		   );  
		
		JSONArray jsonArray=JSONArray.fromObject(townList,config);
		System.out.println(""+jsonArray);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
    
    
	
	public String getCity1()throws Exception{
		int pid1=pid;
		Province province=ProductAddressService.getProvinceById(pid1);
		List<City> cityList=ProductAddressService.findAllCityList(province);
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
		   if (name.equals("province") || name.equals("townList")||name.equals("productList")) {  
		   return true;  
		   } else {  
		   return false;  
		   }  
		   }  
		   }
		   );  
		
		JSONArray jsonArray=JSONArray.fromObject(cityList,config);
		System.out.println(""+jsonArray);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	public String getTown1() throws Exception{
		
		int cid1=cid;
		City city=ProductAddressService.getCityById(cid1);
		List<Town> townList=ProductAddressService.findAllTownList(city);
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
		   if (name.equals("city") || name.equals("productList")) {  
		   return true;  
		   } else {  
		   return false;  
		   }  
		   }  
		   }
		   );  
		
		JSONArray jsonArray=JSONArray.fromObject(townList,config);
		System.out.println(""+jsonArray);
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	
	
	
	
	
	//��Ա�޸�Ь��
    public String getProvinceById() throws Exception{
		//ProvinceServiceBean provinceBean=new ProvinceServiceBean();
		List<Province> provinceList=ProductAddressService.findAllProvinceList();
		
		JsonConfig config = new JsonConfig();  
		   config.setJsonPropertyFilter(new PropertyFilter() {  
		   public boolean apply(Object source, String name, Object value) {  
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
	
	
	
	
	
	/**
	 * adminʡ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public String provinceList()throws Exception{
		
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Province> provinceList=ProductAddressService.findProvinceList(s_province, pageBean);
		long total=ProductAddressService.getProvinceCount(s_province);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"cityList","productList"});
		jsonConfig.registerJsonValueProcessor(BigProvince.class, new ObjectJsonValueProcessor(new String[]{"id","name"}, BigProvince.class));
		
		JSONArray rows=JSONArray.fromObject(provinceList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	/**
	 * ��ҳ��ѯ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String cityList()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<City> cityList=ProductAddressService.findCityList(s_city, pageBean);
		long total=ProductAddressService.getCityCount(s_city);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList","townList"});
		jsonConfig.registerJsonValueProcessor(Province.class, new ObjectJsonValueProcessor(new String[]{"pid","pname"}, Province.class));
		JSONArray rows=JSONArray.fromObject(cityList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	/**
	 * ��ҳ��ѯ����Ϣ
	 * @return
	 * @throws Exception
	 */
	public String townList()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<Town> townList=ProductAddressService.findTownList(s_town, pageBean);
		long total=ProductAddressService.getTownCount(s_town);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"productList"});
		jsonConfig.registerJsonValueProcessor(City.class, new ObjectJsonValueProcessor(new String[]{"cid","cname"}, City.class));
		JSONArray rows=JSONArray.fromObject(townList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	/**
	 * ��̨-����ʡ
	 * @return
	 * @throws Exception
	 */
	/*public String save()throws Exception{
		productBigTypeService.saveProductBigType(productBigType);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}*/
	
	/**
	 * ʡ�������򼯺�
	 * @return
	 * @throws Exception
	 */
	public String pcomboList()throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("pid", "");
		jsonObject.put("pname", "请选择...");
		List<Province> provinceList=ProductAddressService.findProvinceList(null, null);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"bigProvince","cityList","productList"});
		JSONArray rows=JSONArray.fromObject(provinceList,jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}
	
	/**
	 * xian�������򼯺�
	 * @return
	 * @throws Exception
	 */
	public String tcomboList()throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("tid", "");
		jsonObject.put("tname", "请选择...");
		jsonArray.add(jsonObject);
		List<Town> provinceList=ProductAddressService.findTownList(s_town, null);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"city","productList"});
		JSONArray rows=JSONArray.fromObject(provinceList,jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}
	
	/**
	 * �������򼯺�
	 * @return
	 * @throws Exception
	 */
	public String ccomboList()throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("cid", "");
		jsonObject.put("cname", "请选择...");
		jsonArray.add(jsonObject);
		List<City> cityList=ProductAddressService.findCityList(s_city, null);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"province","townList","productList"});
		JSONArray rows=JSONArray.fromObject(cityList,jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}
	
	/**
	 * ���������򼯺�
	 * @return
	 * @throws Exception
	 */
	public String bcomboList()throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		List<BigProvince> provinceList=ProductAddressService.findAllBigAddressList();
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"provinceList"});
		JSONArray rows=JSONArray.fromObject(provinceList,jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}
	
	/**
	 * ��̨-����ʡ
	 * @return
	 * @throws Exception
	 */
	public String psave()throws Exception{
//		if(province.getPid()==0)
//		{
//			if(province.getBigProvince().getId()==0)
//				province.setBigProvince(null);
//		}
		ProductAddressService.saveProvince(province);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	/**
	 * ��̨-������
	 * @return
	 * @throws Exception
	 */
	public String csave()throws Exception{
		ProductAddressService.saveCity(city);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ��̨-������
	 * @return
	 * @throws Exception
	 */
	public String tsave()throws Exception{
		//System.out.println(town.getTid());
		ProductAddressService.saveTown(town);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	
	/**
	 * ɾ��ʡ
	 * @return
	 * @throws Exception
	 */
	public String pdelete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			if(ProductAddressService.existCityWithProvinceId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "该省还存在下级市，无法删除");
			}
//			if(productService.existProductWithPrivnceId(Integer.parseInt(idsStr[i]))){
//				result.put("exist", "��ʡ������Ʒ");
//			}
				
			else{
				Province province=ProductAddressService.getProvinceById(Integer.parseInt(idsStr[i]));
				ProductAddressService.delete(province);				
			}
		}
		
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	
	
	/**
	 * ɾ����
	 * @return
	 * @throws Exception
	 */
	public String cdelete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			if(ProductAddressService.existTownWithCityId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "该市还存在下级县，无法删除");
			}
//			if(productService.existProductWithCityId(Integer.parseInt(idsStr[i]))){
//				result.put("exist", "��ʡ������Ʒ");
//			}
				
			else{
				City city=ProductAddressService.getCityById(Integer.parseInt(idsStr[i]));
				ProductAddressService.delete(city);				
			}
		}
		
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ɾ����
	 * @return
	 * @throws Exception
	 */
	public String tdelete()throws Exception{
		JSONObject result=new JSONObject();
		System.out.println("��ӡ"+ids);
		
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
//			if(productService.existProductWithTownId(Integer.parseInt(idsStr[i]))){
//				result.put("exist", "���ش�����Ʒ");
//			}
//				
//			else{
				Town town=ProductAddressService.getTownById(Integer.parseInt(idsStr[i]));
				ProductAddressService.delete(town);				
//			}
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

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response=response;
	}
	
	
}
