package com.yicunyipin.action;


import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionSupport;
import com.yicunyipin.entity.NewsType;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.NewsType;
import com.yicunyipin.entity.ProductSmallType;
import com.yicunyipin.service.NewsService;
import com.yicunyipin.service.NewsTypeService;
import com.yicunyipin.service.NewsTypeService;
import com.yicunyipin.service.ProductSmallTypeService;
import com.yicunyipin.util.ResponseUtil;
/**
 * 商品大类Action类
 * @author Administrator
 *
 */
@Controller
public class NewsTypeAction extends ActionSupport{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Resource
	private NewsTypeService newsTypeService;
	
	@Resource
	private NewsService newsService;
	
	private String page;
	private String rows;
	private NewsType newsType;
	private NewsType s_newsType;
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
	public String getIds() {
		return ids;
	}
	public void setIds(String ids) {
		this.ids = ids;
	}
	
	public NewsType getNewsType() {
		return newsType;
	}
	public void setNewsType(NewsType newsType) {
		this.newsType = newsType;
	}
	public NewsType getS_newsType() {
		return s_newsType;
	}
	public void setS_newsType(NewsType s_newsType) {
		this.s_newsType = s_newsType;
	}
	/**
	 * 分页查询商品大类信息
	 * @return
	 * @throws Exception
	 */
	public String list()throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<NewsType> NewsTypeList=newsTypeService.findNewsTypeList(s_newsType, pageBean);
		long total=newsTypeService.getNewsTypeCount(s_newsType);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"newsList"});
		JSONArray rows=JSONArray.fromObject(NewsTypeList,jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rows);
		result.put("total", total);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 后台-保存商品大类信息
	 * @return
	 * @throws Exception
	 */
	public String save()throws Exception{
		newsTypeService.saveNewsType(newsType);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}

	/**
	 * 删除商品大类
	 * @return
	 * @throws Exception
	 */
	public String delete()throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			if(newsService.existNewsWithNewsTypeId(Integer.parseInt(idsStr[i]))){
				result.put("exist", "该类包含新闻");
			}else{
				NewsType newsType=newsTypeService.getNewsTypeById(Integer.parseInt(idsStr[i]));
				newsTypeService.delete(newsType);								
			}
		}
		result.put("success", true);
		ResponseUtil.write(ServletActionContext.getResponse(), result);
		return null;
	}
	
	/**
	 * 商品大类下拉框集合
	 * @return
	 * @throws Exception
	 */
	public String comboList()throws Exception{
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("id", "");
		jsonObject.put("name", "请选择...");
		jsonArray.add(jsonObject);
		List<NewsType> NewsTypeList=newsTypeService.findNewsTypeList(null, null);
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[]{"newsList"});
		JSONArray rows=JSONArray.fromObject(NewsTypeList,jsonConfig);
		jsonArray.addAll(rows);
		ResponseUtil.write(ServletActionContext.getResponse(), jsonArray);
		return null;
	}
}
