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
import com.yicunyipin.util.NavUtil;
import com.yicunyipin.util.PageUtil;
import com.yicunyipin.util.ResponseUtil;
import com.yicunyipin.util.StringUtil;

/**
 * œµÕ≥Action¿‡
 * @author Administrator
 *
 */
@Controller
public class ErrorAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public String execute() throws Exception{
		
		return "error";	
	}
	
	
	

}
