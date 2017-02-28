package com.yicunyipin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.News;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.service.NewsService;



@Service("newsService")
public class NewsServiceImpl implements NewsService{

	@Resource
	private BaseDAO<News> baseDAO;
	
	@Override
	public List<News> findNewsList(News s_news, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from News");

		if(s_news!=null){
			/*根据类型查找*/
		   if(s_news.getNewsType()!=null){
			hql.append(" and newsType.id=?");
			param.add(s_news.getNewsType().getId());
			System.out.println(""+s_news.getNewsType().getId());
		   }
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}

	@Override
	public News getNewsById(int newsId) {
		return baseDAO.get(News.class, newsId);
	}

	//新闻数量
	@Override
	public Long getNewsCount(News s_news) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from News");
		if(s_news!=null){
			if(s_news.getNewsType()!=null){
				hql.append(" and newsType.id=?");
				param.add(s_news.getNewsType().getId());
			}
			
			/*if(StringUtil.isNotEmpty(s_news.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_news.getName()+"%");
			}*/
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public void saveNews(News news) {
		baseDAO.merge(news);
	}

	@Override
	public void delete(News news) {
		// TODO Auto-generated method stub
		baseDAO.delete(news);
	}

	@Override
	public boolean existNewsWithNewsTypeId(int newsTypeId) {
		StringBuffer hql=new StringBuffer("from News where newsType.id="+newsTypeId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}

	
	
}
