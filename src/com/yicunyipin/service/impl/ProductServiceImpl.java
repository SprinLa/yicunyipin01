package com.yicunyipin.service.impl;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Product;
import com.yicunyipin.service.ProductService;
import com.yicunyipin.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Resource
	private BaseDAO<Product> baseDAO;
	
	
	
	/**
	 * 
	 * ������Ʒ���Ժ�ҳ���С�����Ʒ
	 * @param1����Ʒ 
	 * @param2��ҳ��
	 * @author libing
	 * @return list
	 */
	@Override
	public List<Product> findProductList(Product s_product, PageBean pageBean) {
		
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Product");
		
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
			if(s_product.getProvince()!=null){
				hql.append(" and province.pid=?");
				param.add(s_product.getProvince().getPid());
			}
			if(s_product.getCity()!=null){
				hql.append(" and city.cid=?");
				param.add(s_product.getCity().getCid());
			}
			if(s_product.getUser()!=null){
				hql.append(" and user.id=?");
				param.add(s_product.getUser().getId());
			}
			
			if(s_product.getStatus()>=0&&s_product.getStatus()<3){
				hql.append(" and status=?");
				param.add(s_product.getStatus());
				
			}
			
			if(StringUtil.isNotEmpty(s_product.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_product.getName()+"%");
			}
			
			//hot==0ʲô������(��ȡ������Ʒʱ�õ�)��hot==1����Ʒ����hot=2(��Ʒ)
			if(s_product.getHot()>0&&s_product.getHot()<3){
				hql.append(" and hot=?");
				param.add(s_product.getHot());
			}
			
			hql.append(" order by publishTime desc");
			
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}




	
	
	
	/**
	 * 
	 * ������Ʒ���Ժ�ҳ���С�����Ʒ�������ڷ�ҳ
	 * @param1����Ʒ 
	 * @param2��ҳ��
	 * @author libing
	 * @return long
	 */
	@Override
	public Long getProductCount(Product s_product) {
		
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Product");
		
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
			if(s_product.getProvince()!=null){
				hql.append(" and province.pid=?");
				param.add(s_product.getProvince().getPid());
			}
			if(s_product.getCity()!=null){
				hql.append(" and city.cid=?");
				param.add(s_product.getCity().getCid());
			}
			if(s_product.getUser()!=null){
				hql.append(" and user.id=?");
				param.add(s_product.getUser().getId());
			}
			if(StringUtil.isNotEmpty(s_product.getName())){
				hql.append(" and name like ?");
				param.add("%"+s_product.getName()+"%");
			}
			if(s_product.getStatus()>=0&&s_product.getStatus()<3){
				hql.append(" and status=?");
				param.add(s_product.getStatus());
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}
	
	
	
	/**
	 * 
	 * ������Ʒid�����Ʒ
	 * @param����Ʒ id
	 * @author libing
	 * @return ��Ʒ(Product)
	 */
	@Override
	public Product getProductById(int productId) {
		return baseDAO.get(Product.class, productId);
	}

	
	/**
	 * 
	 * ������Ʒ
	 * @param����Ʒ 
	 * @author libing
	 * @return null
	 */
	@Override
	public void saveProduct(Product product) {

		 baseDAO.save(product);
		
	}
	
	
	/**
	 * 
	 * �޸���Ʒ
	 * @param����Ʒ 
	 * @author libing
	 * @return null
	 */
	@Override
	public void modifyProduct(Product product) {
		baseDAO.update(product);
	}
	
	/**
	 * 
	 * ɾ����Ʒ
	 * @param����Ʒ 
	 * @author libing
	 * @return null
	 */
	@Override
	public void deleteProduct(Product product) {
		baseDAO.delete(product);
	}
	
	
	/**
	 * ��ע����ʱδ����
	 * ������Ʒ�ĵ���������һ������һ�Σ�
	 * @param����Ʒ 
	 * @author libing
	 * @return null
	 *
	 */
	@Override
	public void setProductWithClick(Product product) {
		product.setClick(product.getClick()+1);
		baseDAO.merge(product);
		
	}

	
	/**
	 * ��ע����ʱδ����
	 * ������Ʒ�������ҳ���С�����Ʒ
	 * @param1��ҳ��
	 * @author libing
	 * @return List
	 */
	@Override
	public List<Product> findProductList(PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Product order by click desc");
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}

	
	
	/**
	 * ��ע����ʱδ����
	 * ����ʡid�жϸ�ʡ�Ƿ������Ʒ
	 * @param��ʡid
	 * @author libing
	 * @return boolean
	 */
	
	@Override
	public boolean existProductWithPrivnceId(int privnceId) {
		StringBuffer hql=new StringBuffer("from Product where province.pid="+privnceId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}
	
	/**
	 * ��ע����ʱδ����
	 * ������id�жϸ�ʡ�Ƿ������Ʒ
	 * @param����id
	 * @author libing
	 * @return boolean
	 */
	@Override
	public boolean existProductWithCityId(int cityId) {
		StringBuffer hql=new StringBuffer("from Product where city.cid="+cityId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}
	
	
	/**
	 * ��ע����ʱδ����
	 * ������id�жϸ�ʡ�Ƿ������Ʒ
	 * @param����id
	 * @author libing
	 * @return boolean
	 */
	@Override
	public boolean existProductWithTownId(int townId) {
		StringBuffer hql=new StringBuffer("from Product where town.tid="+townId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}

	
	
	/**
	 * ��ע����ʱδ����
	 * ������ƷС��id�жϸ�����Ƿ������Ʒ
	 * @param��С���id
	 * @author libing
	 * @return boolean
	 *
	 */

	@Override
	public boolean existProductWithSmallTypeId(int smallTypeId) {
		StringBuffer hql=new StringBuffer("from Product where smallType.id="+smallTypeId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}
	
	
	
	/**
	 * 
	 * ������Ʒid�Ѹ���Ʒ��Ϊ��Ʒ��hot����=2Ϊ��Ʒ��
	 * @param����Ʒid
	 * @author libing
	 * @return null
	 *
	 */

	@Override
	public void setProductWithHot(int productId) {
		Product product=baseDAO.get(Product.class, productId);
		product.setHot(2);
		product.setMidifyTime(new Date());
		baseDAO.save(product);
		
	}

	
	/**
	 * 
	 * ������Ʒid�Ѹ���Ʒ��Ϊ��Ʒ��hot����=1Ϊ��Ʒ��
	 * @param����Ʒid
	 * @author libing
	 * @return null
	 *
	 */
	@Override
	public void setProductWithNews(int productId) {
		Product product=baseDAO.get(Product.class, productId);
		product.setHot(1);
		product.setMidifyTime(new Date());
		baseDAO.save(product);
		
	}
	
	
	
	/**
	 * Ȩ�ޣ�����Ա
	 * ������Ʒid�����Ʒ��Status����=2Ϊ���ͨ����
	 * @param����Ʒid
	 * @author libing
	 * @return null
	 *
	 */
	
	@Override
	public void setProductWithStauts(int productId) {
		Product product=baseDAO.get(Product.class, productId);
		product.setStatus(2);
		product.setMidifyTime(new Date());
		baseDAO.save(product);
	}


	/**
	 * Ȩ�ޣ�����Ա
	 * ������Ʒid�����Ʒ��Status����=1Ϊ���δͨ����
	 * @param����Ʒid
	 * @author libing
	 * @return null
	 */
	@Override
	public void setProductWithStautsNo(int productId) {
		Product product=baseDAO.get(Product.class, productId);
		product.setStatus(1);
		product.setMidifyTime(new Date());
		baseDAO.save(product);
	}





	@Override
	public boolean existProductWithUserId(int userId) {
		StringBuffer hql=new StringBuffer("from Product where user.id="+userId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}

	

}
