package com.yicunyipin.action;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang3.SystemUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.News;
import com.yicunyipin.entity.NewsType;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.ProductSmallType;
import com.yicunyipin.service.NewsService;
import com.yicunyipin.util.NavUtil;
import com.yicunyipin.util.PageUtil;
import com.yicunyipin.util.ResponseUtil;
import com.yicunyipin.util.StringUtil;

@Controller
public class NewsAction extends ActionSupport implements ServletRequestAware {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;

	@Resource
	private NewsService newsService;
	private String page;
	private News news;
	private int newsId;
	private String mainPage;
	private String navCode;
	private List<News> newsList;
	private News s_news;
	private Long total;
	private String pageCode;
	private String rows;
	private String ids;
	
	
	
	public String getRows() {
		return rows;
	}
	public void setRows(String rows) {
		this.rows = rows;
	}
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	public String getPageCode() {
		return pageCode;
	}
	public void setPageCode(String pageCode) {
		this.pageCode = pageCode;
	}
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public News getNews() {
		return news;
	}
	public void setNews(News news) {
		this.news = news;
	}
	public int getNewsId() {
		return newsId;
	}
	public void setNewsId(int newsId) {
		this.newsId = newsId;
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
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}

	
	public News getS_news() {
		return s_news;
	}
	public void setS_news(News s_news) {
		this.s_news = s_news;
	}
	public List<News> getNewsList() {
		return newsList;
	}
	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}
	
	
	@Override
	public String execute() throws Exception{
		if(StringUtil.isEmpty(page)){
			page="1";
		}
		PageBean pageBean=new PageBean(Integer.parseInt(page),8);
		newsList=newsService.findNewsList(s_news, pageBean);
		
		total=newsService.getNewsCount(s_news);
		StringBuffer param=new StringBuffer();
		if(s_news!=null){
			if(s_news.getNewsType()!=null){
				param.append("s_news.newsType.id="+s_news.getNewsType().getId());
			}
			
			/*if(StringUtil.isNotEmpty(s_news.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_news.getName()+"%");
			}*/
		}
		
		pageCode=PageUtil.genPagination(request.getContextPath()+"/news.action", total, Integer.parseInt(page), 8, param.toString());
		navCode=NavUtil.genNavCode("新闻列表");
		mainPage="newsList.jsp";
		return SUCCESS;	
	}
	
	
	public String showNews()throws Exception{
		news=newsService.getNewsById(newsId);
		mainPage="newsDetails.jsp";
		navCode=NavUtil.genNavCode("新闻信息");
		return SUCCESS;
	}
	
	
	
	
	/*vip会员  新闻维护	*/
	public String newsPreSave() throws Exception {
	    String gid=String.valueOf(newsId);
	       if (StringUtil.isNotEmpty(gid)) {
	    	   news=newsService.getNewsById(newsId);//根据ID获取商品;
	          request.setAttribute("news", news);
	       }

	      //categoryList=categeryDao.listCategory();//获取商品类别;
	      //request.setAttribute("categoryList", categoryList);//获取商品地址
	       request.setAttribute("mainPage", "/background/news/newsSave.jsp");
	 
	       return "newsPreSave";
	    
	  
	}
	
	//会员增加新闻
	   public String newsAdd()throws Exception{
		   newsService.saveNews(news);
		   return "newsPreSave";	
						     
			}

	   
	 //展示会员商品
	   public String showNewsByUser() throws Exception {
			//System.out.println("a"+ads);
			String userIdString=String.valueOf(request.getSession().getAttribute("userid"));
			System.out.println("用户id"+userIdString);
			//goodsList=goodsDao.getGoods();
			
			
			if(StringUtil.isEmpty(page)){
				page="1";
			}
			PageBean pageBean=new PageBean(Integer.parseInt(page),8);
	newsList=newsService.findNewsList(s_news, pageBean);
			total=newsService.getNewsCount(s_news);
			StringBuffer param=new StringBuffer();
			if(s_news!=null){
				//按类别查询商品
				if(s_news.getNewsType()!=null){
					param.append("s_product.bigType.id="+s_news.getNewsType().getId());
				}
				
				if(StringUtil.isNotEmpty(s_news.getContent())){
					param.append("s_news.name="+s_news.getContent());
				}
			}
			
			request.setAttribute("newsList",newsList);
			//addressList=addressDao.listAddress();
			//categoryList=categeryDao.listCategory();
			request.setAttribute("mainPage", "/background/news/newsList.jsp");
			return "newsPreSave";
	   }
	   
	   /**
		 * admin分页查询新闻信息
		 * @return
		 * @throws Exception
		 */
		public String list()throws Exception{
			PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
			List<News> newsList=newsService.findNewsList(s_news, pageBean);
			long total=newsService.getNewsCount(s_news);
			JsonConfig jsonConfig = new JsonConfig();
			jsonConfig.registerJsonValueProcessor(NewsType.class, new ObjectJsonValueProcessor(new String[]{"id","typeName"}, NewsType.class));
			jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));  
			JSONArray rows=JSONArray.fromObject(newsList,jsonConfig);
			JSONObject result=new JSONObject();
			result.put("rows", rows);
			result.put("total", total);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return null;
		}   
	   
		/**
		 *  admin后台-保存新闻信息
		 * @return
		 * @throws Exception
		 */
		public String save()throws Exception{
			if(news.getId()==0){
				news.setCreateTime(new Date());
			}
			newsService.saveNews(news);
			JSONObject result=new JSONObject();
			result.put("success", true);
			ResponseUtil.write(ServletActionContext.getResponse(), result);
			return null;
		}
		
		/**
		 * admin删除新闻
		 * @return
		 * @throws Exception
		 */
		public String delete()throws Exception{
			JSONObject result=new JSONObject();
			String []idsStr=ids.split(",");
			for(int i=0;i<idsStr.length;i++){
				News news=newsService.getNewsById(Integer.parseInt(idsStr[i]));
				newsService.delete(news);								
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
	
	
}
