package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.NewsType;
import com.yicunyipin.entity.PageBean;



public interface NewsTypeService {

	public List<NewsType> findAllNewsTypeList();
	public Long getNewsTypeCount(NewsType s_newsType);
	/**
	 * ��ҳ��ѯ��Ʒ����
	 * @param NewsType
	 * @param pageBean
	 * @return
	 */
	public List<NewsType> findNewsTypeList(NewsType s_newsType,PageBean pageBean);
	
	
	/**
	 * ������Ʒ����
	 * @param NewsType
	 */
	public void saveNewsType(NewsType newsType);
	
	/**
	 * ɾ����Ʒ����
	 * @param NewsType
	 */
	public void delete(NewsType newsType);
	
	/**
	 * ͨ��id��ȡ��Ʒ����ʵ��
	 * @param id
	 * @return
	 */
	public NewsType getNewsTypeById(int id);


}
