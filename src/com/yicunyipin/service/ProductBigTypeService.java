package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.entity.ProductSmallType;



public interface ProductBigTypeService {

	public List<ProductBigType> findAllBigTypeList();
	
	public List<ProductSmallType> findAllSmallTypeList(ProductBigType s_productBigType);
 
	
	
	/**
	 * 分页查询商品大类
	 * @param productBigType
	 * @param pageBean
	 * @return
	 */
	public List<ProductBigType> findProductBigTypeList(ProductBigType s_productBigType,PageBean pageBean);
	
	
	/**
	 * 查询商品大类数量
	 * @param s_productBigType
	 * @return
	 */
	public Long getProductBigTypeCount(ProductBigType s_productBigType);
	
	/**
	 * 保存商品大类
	 * @param productBigType
	 */
	public void saveProductBigType(ProductBigType productBigType);
	
	/**
	 * 删除商品大类
	 * @param productBigType
	 */
	public void delete(ProductBigType productBigType);
	
	/**
	 * 通过id获取商品大类实体
	 * @param id
	 * @return
	 */
	public ProductBigType getProductBigTypeById(int id);
	
}
