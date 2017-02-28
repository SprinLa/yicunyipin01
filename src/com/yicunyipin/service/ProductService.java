package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Product;
import com.yicunyipin.entity.User;



public interface ProductService {

	/**
	 * 
	 * 保存商品
	 * @param：商品 
	 * @author libing
	 * @return null
	 */
	public void saveProduct(Product product);
	
	/**
	 * 
	 * 修改商品
	 * @param：商品 
	 * @author libing
	 * @return null
	 */
	public void modifyProduct(Product product);
	
	
	/**
	 * 
	 * 根据商品属性和页面大小获得商品
	 * @param1：商品 
	 * @param2：页面
	 * @author libing
	 * @return list
	 */
	public List<Product> findProductList(Product s_product,PageBean pageBean);
	
	/**
	 * 备注：暂时未用上
	 * 根据商品点击量和页面大小获得商品
	 * @param1：页面
	 * @author libing
	 * @return List
	 */
	public List<Product> findProductList(PageBean pageBean);
	
	/**
	 * 
	 * 根据商品属性和页面大小获得商品个数用于分页
	 * @param1：商品 
	 * @param2：页面
	 * @author libing
	 * @return long
	 */
	public Long getProductCount(Product s_product);
	
	
	/**
	 * 
	 * 根据商品id获得商品
	 * @param：商品 id
	 * @author libing
	 * @return 商品(Product)
	 */
	public Product getProductById(int productId);
	
	
	/**
	 * 备注：暂时未用上
	 * 设置商品的点击量（点击一次增加一次）
	 * @param：商品 
	 * @author libing
	 * @return null
	 *
	 */
	public void setProductWithClick(Product product);
	
	/**
	 * 是否存在指定商品小类的商品
	 * @param smallTypeId
	 * @return
	 */
	public boolean existProductWithPrivnceId(int privnceId);
	public boolean existProductWithCityId(int cityId);
	public boolean existProductWithTownId(int townId);
	public boolean existProductWithSmallTypeId(int smallTypeId);
	/**
	 * 是否存在指定商品的用户
	 * @param bigTypeId
	 * @return
	 */
	public boolean existProductWithUserId(int userId);
	
	
	/**
	 * 
	 * 删除商品
	 * @param：商品 
	 * @author libing
	 * @return null
	 */
	public void deleteProduct(Product product);
	
	

	/**
	 * 
	 * 根据商品id把该商品设为精品（hot属性=2为精品）
	 * @param：商品id
	 * @author libing
	 * @return null
	 *
	 */

	public void setProductWithHot(int productId);
	/**
	 * 
	 * 根据商品id把该商品设为新品（hot属性=1为新品）
	 * @param：商品id
	 * @author libing
	 * @return null
	 *
	 */
	public void setProductWithNews(int productId);
	
	/**
	 * 
	 * 根据商品id把该商品设为新品（hot属性=1为新品）
	 * @param：商品id
	 * @author libing
	 * @return null
	 *
	 */
	public void setProductWithStauts(int productId);
	
	
	/**
	 * 权限：管理员
	 * 根据商品id审核商品（Status属性=1为审核未通过）
	 * @param：商品id
	 * @author libing
	 * @return null
	 */
	public void setProductWithStautsNo(int productId);
}
