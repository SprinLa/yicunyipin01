package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.ProductBigType;
import com.yicunyipin.entity.ProductSmallType;



public interface ProductBigTypeService {

	public List<ProductBigType> findAllBigTypeList();
	
	public List<ProductSmallType> findAllSmallTypeList(ProductBigType s_productBigType);
 
	
	
	/**
	 * ��ҳ��ѯ��Ʒ����
	 * @param productBigType
	 * @param pageBean
	 * @return
	 */
	public List<ProductBigType> findProductBigTypeList(ProductBigType s_productBigType,PageBean pageBean);
	
	
	/**
	 * ��ѯ��Ʒ��������
	 * @param s_productBigType
	 * @return
	 */
	public Long getProductBigTypeCount(ProductBigType s_productBigType);
	
	/**
	 * ������Ʒ����
	 * @param productBigType
	 */
	public void saveProductBigType(ProductBigType productBigType);
	
	/**
	 * ɾ����Ʒ����
	 * @param productBigType
	 */
	public void delete(ProductBigType productBigType);
	
	/**
	 * ͨ��id��ȡ��Ʒ����ʵ��
	 * @param id
	 * @return
	 */
	public ProductBigType getProductBigTypeById(int id);
	
}
