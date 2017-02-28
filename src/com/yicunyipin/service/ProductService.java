package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Product;
import com.yicunyipin.entity.User;



public interface ProductService {

	/**
	 * 
	 * ������Ʒ
	 * @param����Ʒ 
	 * @author libing
	 * @return null
	 */
	public void saveProduct(Product product);
	
	/**
	 * 
	 * �޸���Ʒ
	 * @param����Ʒ 
	 * @author libing
	 * @return null
	 */
	public void modifyProduct(Product product);
	
	
	/**
	 * 
	 * ������Ʒ���Ժ�ҳ���С�����Ʒ
	 * @param1����Ʒ 
	 * @param2��ҳ��
	 * @author libing
	 * @return list
	 */
	public List<Product> findProductList(Product s_product,PageBean pageBean);
	
	/**
	 * ��ע����ʱδ����
	 * ������Ʒ�������ҳ���С�����Ʒ
	 * @param1��ҳ��
	 * @author libing
	 * @return List
	 */
	public List<Product> findProductList(PageBean pageBean);
	
	/**
	 * 
	 * ������Ʒ���Ժ�ҳ���С�����Ʒ�������ڷ�ҳ
	 * @param1����Ʒ 
	 * @param2��ҳ��
	 * @author libing
	 * @return long
	 */
	public Long getProductCount(Product s_product);
	
	
	/**
	 * 
	 * ������Ʒid�����Ʒ
	 * @param����Ʒ id
	 * @author libing
	 * @return ��Ʒ(Product)
	 */
	public Product getProductById(int productId);
	
	
	/**
	 * ��ע����ʱδ����
	 * ������Ʒ�ĵ���������һ������һ�Σ�
	 * @param����Ʒ 
	 * @author libing
	 * @return null
	 *
	 */
	public void setProductWithClick(Product product);
	
	/**
	 * �Ƿ����ָ����ƷС�����Ʒ
	 * @param smallTypeId
	 * @return
	 */
	public boolean existProductWithPrivnceId(int privnceId);
	public boolean existProductWithCityId(int cityId);
	public boolean existProductWithTownId(int townId);
	public boolean existProductWithSmallTypeId(int smallTypeId);
	/**
	 * �Ƿ����ָ����Ʒ���û�
	 * @param bigTypeId
	 * @return
	 */
	public boolean existProductWithUserId(int userId);
	
	
	/**
	 * 
	 * ɾ����Ʒ
	 * @param����Ʒ 
	 * @author libing
	 * @return null
	 */
	public void deleteProduct(Product product);
	
	

	/**
	 * 
	 * ������Ʒid�Ѹ���Ʒ��Ϊ��Ʒ��hot����=2Ϊ��Ʒ��
	 * @param����Ʒid
	 * @author libing
	 * @return null
	 *
	 */

	public void setProductWithHot(int productId);
	/**
	 * 
	 * ������Ʒid�Ѹ���Ʒ��Ϊ��Ʒ��hot����=1Ϊ��Ʒ��
	 * @param����Ʒid
	 * @author libing
	 * @return null
	 *
	 */
	public void setProductWithNews(int productId);
	
	/**
	 * 
	 * ������Ʒid�Ѹ���Ʒ��Ϊ��Ʒ��hot����=1Ϊ��Ʒ��
	 * @param����Ʒid
	 * @author libing
	 * @return null
	 *
	 */
	public void setProductWithStauts(int productId);
	
	
	/**
	 * Ȩ�ޣ�����Ա
	 * ������Ʒid�����Ʒ��Status����=1Ϊ���δͨ����
	 * @param����Ʒid
	 * @author libing
	 * @return null
	 */
	public void setProductWithStautsNo(int productId);
}
