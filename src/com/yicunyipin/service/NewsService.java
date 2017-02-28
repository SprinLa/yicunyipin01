package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.News;
import com.yicunyipin.entity.PageBean;



public interface NewsService {

	public List<News> findNewsList(News s_news,PageBean pageBean);
	
	public Long getNewsCount(News s_news);
	
	public void saveNews(News news);

	public News getNewsById(int newsId);

	public void delete(News news);

	public boolean existNewsWithNewsTypeId(int newsTypeId);
}
