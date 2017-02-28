package com.yicunyipin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.entity.ProductSmallType;
import com.yicunyipin.service.ProductBigTypeService;
import com.yicunyipin.util.StringUtil;



@Service("productBigTypeService")
public class ProductBigTypeServiceImpl implements ProductBigTypeService{

	@Resource
	private BaseDAO<ProductBigType> baseDAO;
	
	@Resource
	private BaseDAO<ProductSmallType> baseDAO2;
	
	
	@Override
	public List<ProductBigType> findAllBigTypeList() {
		return baseDAO.find(" from ProductBigType");
	}

	@Override
	public List<ProductSmallType> findAllSmallTypeList(ProductBigType s_productBigType) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from ProductSmallType");
			if(s_productBigType!=null){
				hql.append(" and bigType=?");
				param.add(s_productBigType);
			}
		
		return baseDAO2.find(hql.toString().replaceFirst("and", "where"), param);
		
	}

	@Override
	public ProductBigType getProductBigTypeById(int BigTypeId) {
		return baseDAO.get(ProductBigType.class, BigTypeId);
	}

	@Override
	public List<ProductBigType> findProductBigTypeList(ProductBigType s_productBigType, PageBean pageBean) {
		
			List<Object> param=new LinkedList<Object>();
			StringBuffer hql=new StringBuffer("from ProductBigType");
			if(s_productBigType!=null){
				if(StringUtil.isNotEmpty(s_productBigType.getName())){
					hql.append(" and name like ?");
					param.add("%"+s_productBigType.getName()+"%");
				}
			}
			if(pageBean!=null){
				return baseDAO.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
			}else{
				return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);			
			}
	}

	@Override
	public Long getProductBigTypeCount(ProductBigType s_productBigType) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from ProductBigType");
		if(s_productBigType!=null){
			if(StringUtil.isNotEmpty(s_productBigType.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_productBigType.getName()+"%");
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);

	}

	@Override
	public void saveProductBigType(ProductBigType productBigType) {
		baseDAO.merge(productBigType);
		
	}

	@Override
	public void delete(ProductBigType productBigType) {
		baseDAO.delete(productBigType);
		
	}

}
