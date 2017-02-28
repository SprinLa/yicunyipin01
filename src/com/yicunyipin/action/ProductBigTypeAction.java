package com.yicunyipin.action;


import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.service.ProductBigTypeService;
import com.yicunyipin.service.ProductSmallTypeService;
import com.yicunyipin.util.ResponseUtil;
/**
 * ��Ʒ����Action��
 * @author Administrator
 *
 */
@Controller
public class ProductBigTypeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private ProductBigTypeService productBigTypeService;
	
	@Resource
	private ProductSmallTypeService productSmallTypeService;
	
	private String page;
	private String rows;
	private ProductBigType productBigType;
	private ProductBigType s_productBigType;
	private String ids;
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
	public ProductBigType getProductBigType() {
		return productBigType;
	}
	public void setProductBigType(ProductBigType productBigType) {
		this.productBigType = productBigType;
	}
	public ProductBigType getS_productBigType() {
		return s_productBigType;
	}
	public void setS_productBigType(ProductBigType s_productBigType) {
		this.s_productBigType = s_productBigType;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	/**
	 * ��ҳ��ѯ��Ʒ������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		//System.out.println("����");
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<ProductBigType> productBigTypeList=productBigTypeService.findProductBigTypeList(s_productBigType, pageBean);
		//System.out.println("����111");
		long total=productBigTypeService.getProductBigTypeCount(s_productBigType);
		//System.out.println("����2222");
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"smallTypeList","tbProductList","productList"});
		//System.out.println("����333");
		JSONArray rows=JSONArray.fromObject(productBigTypeList,jsonConfig);
		System.out.println("dalei:"+rows);
		
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ��̨-������Ʒ������Ϣ
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		productBigTypeService.saveProductBigType(productBigType);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * ɾ����Ʒ����
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			if(productSmallTypeService.existSmallTypeWithBigTypeId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "商品大类包含商品小类，无法删除！");
			}else{
				ProductBigType productBigType=productBigTypeService.getProductBigTypeById(Integer.parseInt(idsStr[i]));
				productBigTypeService.delete(productBigType);				
			}
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * ��Ʒ���������򼯺�
	 * @return
	 * @throws Exception
	 */
	public String comboList()throws Exception{
		//System.out.println("����������");
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		List<ProductBigType> productBigTypeList=productBigTypeService.findProductBigTypeList(null, null);
		JsonConfig jsonConfig = new JsonConfig();
		//System.out.println("����������222");
		jsonConfig.setExcludes(new String[]{"smallTypeList","productList","tbProductList"});
		//System.out.println("����������111");
		JSONArray rows=JSONArray.fromObject(productBigTypeList,jsonConfig);
		//System.out.println("����������333��"+rows);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}
}
