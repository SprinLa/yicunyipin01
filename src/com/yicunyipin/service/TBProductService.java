package com.yicunyipin.service;

import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Product;
import com.yicunyipin.entity.TBProduct;
import com.yicunyipin.entity.TBUser;

import java.util.List;


public interface TBProductService {

	/**
	 * 
	 * ������Ʒ
	 * @param����Ʒ 
	 * @author ss
	 * @return null
	 */
	public void saveProduct(TBProduct product);
	
	/**
	 * 
	 * �޸���Ʒ
	 * @param����Ʒ 
	 * @author ss
	 * @return null
	 */
	public void modifyProduct(TBProduct product);
	
	
	/**
	 * 
	 * �����Ʒ���Ժ�ҳ���С�����Ʒ
	 * @param1����Ʒ 
	 * @param2��ҳ��
	 * @author ss
	 * @return list
	 */
	public List<TBProduct> findProductList(TBProduct s_product,PageBean pageBean);
	public List<TBProduct> findBestsellersList(TBProduct s_product, PageBean pageBean);
	/**
	 * 
	 * �����Ʒ���Ժ�ҳ���С��״̬�����Ʒ
	 * @param1����Ʒ 
	 * @param2��ҳ��
	 * @author ss
	 * @return list
	 */
//	public List<TBProduct> findProductListByStatus(TBProduct s_product,PageBean pageBean);
	
	/**
	 * ��ע����ʱδ����
	 * �����Ʒ�������ҳ���С�����Ʒ
	 * @param1��ҳ��
	 * @author ss
	 * @return List
	 */
	public List<TBProduct> findProductList(PageBean pageBean);
	
	/**
	 * 
	 * �����Ʒ���Ժ�ҳ���С�����Ʒ�������ڷ�ҳ
	 * @param1����Ʒ 
	 * @param2��ҳ��
	 * @author ss
	 * @return long
	 */
	public Long getProductCount(TBProduct s_product);
	
	
	/**
	 * 
	 * �����Ʒid�����Ʒ
	 * @param����Ʒ id
	 * @author ss
	 * @return ��Ʒ(Product)
	 */
	public TBProduct getProductById(int productId);
	
	
	/**
	 * ��ע����ʱδ����
	 * ������Ʒ�ĵ���������һ������һ�Σ�
	 * @param����Ʒ 
	 * @author ss
	 * @return null
	 *
	 */
	public void setProductWithClick(TBProduct product);
	
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
	 * @author ss
	 * @return null
	 */
	public void deleteProduct(TBProduct product);
	
	

	/**
	 * 
	 * �����Ʒid�Ѹ���Ʒ��Ϊ��Ʒ��TYPE����=1Ϊ��Ʒ 2-��Ʒ��
	 * @param����Ʒid
	 * @author ss
	 * @return null
	 *
	 */

	public void setProductWithHot(int productId);
	/**
	 * 
	 * �����Ʒid�Ѹ���Ʒ��Ϊ��Ʒ��type����=2Ϊ��Ʒ��
	 * @param����Ʒid
	 * @author ss
	 * @return null
	 *
	 */
	public void setProductWithNews(int productId);
	
	/**
	 * 
	 * �����Ʒid�Ѹ���Ʒ��Ϊ��Ʒ��hot����=1Ϊ��Ʒ��
	 * @param����Ʒid
	 * @author ss
	 * @return null
	 *
	 */
	public void setProductWithStauts(int productId);
	
	
	/**
	 * Ȩ�ޣ�����Ա
	 * �����Ʒid�����Ʒ��verified���� 0δ��ˣ�1��˹�2��˲�ͨ�� ��
	 * @param����Ʒid
	 * @author ss
	 * @return null
	 */
	public void setProductWithStautsNo(int productId);
	public List<TBProduct> findNewProductList(TBProduct s_product, PageBean pageBean);
	public Long getProductCountWithVerified(TBProduct s_product);
	//by kongjun
	public List<TBProduct> getProductByUser(TBUser user);
}
