package com.yicunyipin.action;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.*;
import com.yicunyipin.service.NewsService;
import com.yicunyipin.service.NewsTypeService;
import com.yicunyipin.service.ProductBigTypeService;
import com.yicunyipin.service.TBProductService;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class RefreshAction extends ActionSupport implements ServletRequestAware {

	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private HttpSession session;
	private ServletContext application;
	@Resource
	private TBProductService tbProductService;
	@Resource
	private ProductBigTypeService productBigTypeService;
	@Resource
	private NewsService newsService;
	@Resource
	private NewsTypeService newsTypeService;

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public ServletContext getApplication() {
		return application;
	}

	public void setApplication(ServletContext application) {
		this.application = application;
	}

	public TBProductService getTbProductService() {
		return tbProductService;
	}

	public void setTbProductService(TBProductService tbProductService) {
		this.tbProductService = tbProductService;
	}

	public ProductBigTypeService getProductBigTypeService() {
		return productBigTypeService;
	}

	public void setProductBigTypeService(ProductBigTypeService productBigTypeService) {
		this.productBigTypeService = productBigTypeService;
	}

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	public NewsTypeService getNewsTypeService() {
		return newsTypeService;
	}

	public void setNewsTypeService(NewsTypeService newsTypeService) {
		this.newsTypeService = newsTypeService;
	}



	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request=request;
		this.session = request.getSession();
		this.application = session.getServletContext();
	}

	public String refreshPage(){

		//获得商品所有的类别（大类、小类）
		List<ProductBigType> bigTypeList=productBigTypeService.findAllBigTypeList();
		application.setAttribute("bigTypeList", bigTypeList);
		//首页的新闻列表（7条）
		List<News> newsList=newsService.findNewsList(null, new PageBean(1,70));
		application.setAttribute("newsList", newsList);
		
		JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < newsList.size(); i ++) {
            News news = newsList.get(i);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("url", "news_showNews.action?newsId="+news.getId());
            jsonObject.put("title", news.getTitle());
            Date time = news.getCreateTime();
            if (time == null) {
				time = new Date();
				}
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String dateString = formatter.format(time);
            //System.out.println(dateString);
            jsonObject.put("time", dateString);
            jsonArray.add(i, jsonObject);
        }

        System.out.println(jsonArray);
        application.setAttribute("NewsArray", jsonArray);
        
		//新闻类别
		List<NewsType> NewsTypeList=newsTypeService.findAllNewsTypeList();
		application.setAttribute("NewsTypeList", NewsTypeList);

		//按照商品的上架时间排序，获得所有精品（8个）
		TBProduct s_product=new TBProduct();
		s_product.setVerified(1);     		//status=1代表商品通过了审核。
		s_product.setType(1);				//type=1代表是精品
		List<TBProduct> specialPriceProductList=tbProductService.findProductList(s_product, new PageBean(1,9));
		application.setAttribute("specialPriceProductList", specialPriceProductList);

		//按照商品的上架时间排序，获得所有新品（8个）
		s_product=new TBProduct();
		s_product.setVerified(1);
		s_product.setType(2);				//hot=2代表是新品
		List<TBProduct> hotProductList=tbProductService.findProductList(s_product, new PageBean(1,9));
		application.setAttribute("hotProductList", hotProductList);

		//按照商品的上架时间排序，获得最畅销商品（5个）
		s_product=new TBProduct();
		s_product.setVerified(1);
		List<TBProduct> bestsellersList=tbProductService.findBestsellersList(s_product, new PageBean(1,5));
		application.setAttribute("bestsellersList", bestsellersList);

		//首页金质会员模块
		s_product=new TBProduct();
		//规定商品类别==5为金质会员
		ProductBigType bt=new ProductBigType();
		bt.setId(5);
		s_product.setBigType(bt);
		List<TBProduct> huiProductList=tbProductService.findProductList(s_product, new PageBean(1,8));
		application.setAttribute("huiProductList", huiProductList);
		System.out.println("huiyuan"+huiProductList.size());

		//根据浏览量排列商品
		List<TBProduct> clickProductList=tbProductService.findProductList(new PageBean(1,8));
		application.setAttribute("clickProductList", clickProductList);
		return "success";
	}

}
