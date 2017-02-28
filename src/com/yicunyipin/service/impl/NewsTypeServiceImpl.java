package com.yicunyipin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.NewsType;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.service.NewsTypeService;
import com.yicunyipin.util.StringUtil;



@Service("newsTypeService")
public class NewsTypeServiceImpl implements NewsTypeService{

	@Resource
	private BaseDAO<NewsType> baseDAO;
	
	@Override
	public List<NewsType> findAllNewsTypeList() {
		return baseDAO.find(" from NewsType");
	}

	@Override
	public Long getNewsTypeCount(NewsType s_newsType) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from NewsType");
		if(s_newsType!=null){
			if(StringUtil.isNotEmpty(s_newsType.getTypeName())){
				hql.append(" and name like ?");
				param.add("%"+s_newsType.getTypeName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);

	}

	@Override
	public List<NewsType> findNewsTypeList(NewsType s_newsType,PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from NewsType");
		if(s_newsType!=null){
			if(StringUtil.isNotEmpty(s_newsType.getTypeName())){
				hql.append(" and name like ?");
				param.add("%"+s_newsType.getTypeName()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);			
		}
	}


	@Override
	public void saveNewsType(NewsType newsType) {
		// TODO Auto-generated method stub
		baseDAO.merge(newsType);
	}

	@Override
	public void delete(NewsType newsType) {
		baseDAO.delete(newsType);
		
	}

	@Override
	public NewsType getNewsTypeById(int id) {
		return baseDAO.get(NewsType.class, id);

	}

}
