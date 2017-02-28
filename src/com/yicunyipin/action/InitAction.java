package com.yicunyipin.action;

import com.yicunyipin.entity.*;
import com.yicunyipin.service.NewsService;
import com.yicunyipin.service.NewsTypeService;
import com.yicunyipin.service.ProductBigTypeService;
import com.yicunyipin.service.TBProductService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;



/**
 * 
 * 项目加载时初始化首页的数据.
 * 所有的数据在首页（index.jsp）显示。
 * @author libing
 *
 */

@Component
public class InitAction implements ServletContextListener,ApplicationContextAware{

	
	private static ApplicationContext applicationContext;
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext=applicationContext;
	}
	
	
	
	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
	
		
		ServletContext application=servletContextEvent.getServletContext();
		
		//获得商品所有的类别（大类、小类）
		ProductBigTypeService productBigTypeService=(ProductBigTypeService)applicationContext.getBean("productBigTypeService");
		List<ProductBigType> bigTypeList=productBigTypeService.findAllBigTypeList();
		application.setAttribute("bigTypeList", bigTypeList);
		//for (ProductBigType pro:bigTypeList){
		//	System.out.println("pro:"+pro.getName());
		//}
		
		//获得商品所有地址(省、市、县)
		//ProductAddressService productAddressService=(ProductAddressService )applicationContext.getBean("ProductAddressService");
		//List<BigProvince> addressList=productAddressService.findAllBigAddressList();
		//application.setAttribute("addressList",addressList);
		
		
		//首页的新闻列表（7条）
		NewsService newsService=(NewsService)applicationContext.getBean("newsService");
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

        //jsonArray.add(1, dataelem2);
        //jsonObject.element("data", jsonArray);
        System.out.println(jsonArray);
        application.setAttribute("NewsArray", jsonArray);

        //新闻类别
		NewsTypeService newsTypeService=(NewsTypeService)applicationContext.getBean("newsTypeService");
		List<NewsType> NewsTypeList=newsTypeService.findAllNewsTypeList();
		application.setAttribute("NewsTypeList", NewsTypeList);
		
		//按照商品的上架时间排序，获得所有精品（8个）
		TBProductService tbProductService=(TBProductService)applicationContext.getBean("tbProductService");
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
//		s_product.setType(1);				//type=1代表是精品
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


		//TBUser tbUser = new TBUser();
		//tbUser.setUserName("libing");
		//tbUser.setPassword("libing");
		//String password = tbUser.getPassword();
		//String passwordMD5 = MD5.getMD5(tbUser.getPassword().getBytes());//
		//if (!StringUtil.isEmpty(tbUser.getPassword())) {
		//	tbUser.setPassword(passwordMD5);
		//}
		//TBUserService tbUserService=(TBUserService)applicationContext.getBean("tbUserService");
		//TBUser currentUser = tbUserService.login(tbUser);
		//HttpSession session = ServletActionContext.getRequest().getSession();
        //
		//if (currentUser == null) {
		//	System.out.println("用户名为空!");
		//} else if (!currentUser.getPassword().equals(passwordMD5)) {
		//	System.out.println("输入密码错误!");
		//} else {
		//	currentUser.setPassword(password);
		//	session.setAttribute("currentUser", currentUser);
		//}
	}

	

}
