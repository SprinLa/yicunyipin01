package com.yicunyipin.action;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ApplicationAware;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.News;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Product;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.service.NewsService;
import com.yicunyipin.service.ProductBigTypeService;
import com.yicunyipin.service.ProductService;
import com.yicunyipin.util.ResponseUtil;

/**
 * ϵͳAction��
 * @author Administrator
 *
 */
@Controller
public class SysAction extends ActionSupport implements ApplicationAware{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Map<String, Object> application;
	
	
	
	@Resource
	private ProductService productService;
	
	public void setApplication(Map<String, Object> application) {
		this.application= application;
	}
	
	/**
	 * ˢ��ϵͳ��ҳչʾ����
	 * @return
	 * @throws Exception
	 */
	public String refreshSystem()throws Exception{
		//������Ʒ���ϼ�ʱ�����򣬻�����о�Ʒ��8����
		Product s_product=new Product();
		s_product.setStatus(2);     		//status=2������Ʒͨ������ˡ�
		s_product.setHot(2);				//hot=2�����Ǿ�Ʒ
		List<Product> specialPriceProductList=productService.findProductList(s_product, new PageBean(1,9));
		application.put("specialPriceProductList", specialPriceProductList);
		
		//������Ʒ���ϼ�ʱ�����򣬻��������Ʒ��8����
		s_product=new Product();
		s_product.setStatus(2);
		s_product.setHot(1);				//hot=1��������Ʒ
		List<Product> hotProductList=productService.findProductList(s_product, new PageBean(1,9));
		application.put("hotProductList", hotProductList);

		
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	

}
