package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.NewsType;
import com.yicunyipin.entity.PageBean;



public interface NewsTypeService {

	public List<NewsType> findAllNewsTypeList();
	public Long getNewsTypeCount(NewsType s_newsType);
	/**
	 * 分页查询商品大类
	 * @param NewsType
	 * @param pageBean
	 * @return
	 */
	public List<NewsType> findNewsTypeList(NewsType s_newsType,PageBean pageBean);
	
	
	/**
	 * 保存商品大类
	 * @param NewsType
	 */
	public void saveNewsType(NewsType newsType);
	
	/**
	 * 删除商品大类
	 * @param NewsType
	 */
	public void delete(NewsType newsType);
	
	/**
	 * 通过id获取商品大类实体
	 * @param id
	 * @return
	 */
	public NewsType getNewsTypeById(int id);


}
