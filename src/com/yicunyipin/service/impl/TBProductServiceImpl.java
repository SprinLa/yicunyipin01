package com.yicunyipin.service.impl;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.TBProduct;
import com.yicunyipin.entity.TBUser;
import com.yicunyipin.service.TBProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;


@Service("tbProductService")
public class TBProductServiceImpl implements TBProductService {

	@Resource
	private BaseDAO<TBProduct> baseDAO;
	
	@Override
	public void saveProduct(TBProduct product) {
		// TODO Auto-generated method stub
		baseDAO.saveOrUpdate(product);
	}

	@Override
	public void modifyProduct(TBProduct product) {
		// TODO Auto-generated method stub
		baseDAO.update(product);
	}

	@Override
	public List<TBProduct> findProductList(TBProduct s_product,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBProduct");
		
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
			if(s_product.getUser()!=null){
				System.out.println("33");
				hql.append(" and user.id=?");
				param.add(s_product.getUser().getId());
			}
			if(s_product.getVerified()>=0&&s_product.getVerified()<3){
				System.out.println("22");
				hql.append(" and verified=?");
				param.add(s_product.getVerified());
				
			}

			//type==0ʲô������(��ȡ������Ʒʱ�õ�)��type==2����Ʒ����hot=1(��Ʒ)
			if(s_product.getType()>0&&s_product.getType()<3){
				System.out.println("444");
				hql.append(" and type=?");
				param.add(s_product.getType());
			}
			
			hql.append(" order by addTime desc");
			
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}

	public List<TBProduct> findBestsellersList(TBProduct s_product, PageBean pageBean) {

		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBProduct");

		if(s_product!=null){
			if(s_product.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
			if(s_product.getUser()!=null){
				System.out.println("33");
				hql.append(" and user.id=?");
				param.add(s_product.getUser().getId());
			}
			if(s_product.getVerified()>=0&&s_product.getVerified()<3){
				hql.append(" and verified=?");
				param.add(s_product.getVerified());

			}

			//type==0ʲô������(��ȡ������Ʒʱ�õ�)��type==2����Ʒ����hot=1(��Ʒ)
			if(s_product.getType()>0&&s_product.getType()<3){
				hql.append(" and type=?");
				param.add(s_product.getType());
			}

			hql.append(" order by orderNum desc");

		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}

	}

	@Override
	public List<TBProduct> findNewProductList(TBProduct s_product,
			PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBProduct");
		
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
			if(s_product.getUser()!=null){
				hql.append(" and user.id=?");
				param.add(s_product.getUser().getId());
			}
			
				hql.append(" and verified!=?");
				param.add(1);
				
			
			
//			if(StringUtil.isNotEmpty(s_product.getName())){
//				hql.append(" and name like ?");
//				param.add("%"+s_product.getName()+"%");
//			}
			
			//type==0ʲô������(��ȡ������Ʒʱ�õ�)��type==2����Ʒ����hot=1(��Ʒ)
			if(s_product.getType()>0&&s_product.getType()<3){
				hql.append(" and type=?");
				param.add(s_product.getType());
			}
			
			hql.append(" order by add_Time desc");
			
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
	}

	@Override
	public List<TBProduct> findProductList(PageBean pageBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getProductCount(TBProduct s_product) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from TBProduct");
		
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
//			if(StringUtil.isNotEmpty(s_product.getName())){
//				hql.append(" and name like ?");
//				param.add("%"+s_product.getName()+"%");
//			}
			if(s_product.getVerified()>=0&&s_product.getVerified()<3){
				hql.append(" and verified=?");
				param.add(s_product.getVerified());
				
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	
	@Override
	public Long getProductCountWithVerified(TBProduct s_product) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from TBProduct");
		
		if(s_product!=null){
			if(s_product.getBigType()!=null){
				hql.append(" and bigType.id=?");
				param.add(s_product.getBigType().getId());
			}
			if(s_product.getSmallType()!=null){
				hql.append(" and smallType.id=?");
				param.add(s_product.getSmallType().getId());
			}
//			if(StringUtil.isNotEmpty(s_product.getName())){
//				hql.append(" and name like ?");
//				param.add("%"+s_product.getName()+"%");
//			}
			if(s_product.getVerified()>=0&&s_product.getVerified()<3){
				hql.append(" and verified=?");
				param.add(s_product.getVerified());
				
			}else {
				hql.append(" and verified!=?");
				param.add(1);
			}
			
			if(s_product.getType()>0)
			{
				hql.append(" and type=?");
				param.add(s_product.getType());
			}
			else {
				hql.append(" and type!=?");
				param.add(s_product.getType());
			}
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
	public TBProduct getProductById(int productId) {
		// TODO Auto-generated method stub
		return baseDAO.get(TBProduct.class, productId);
	}

	@Override
	public void setProductWithClick(TBProduct product) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean existProductWithPrivnceId(int privnceId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existProductWithCityId(int cityId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existProductWithTownId(int townId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean existProductWithSmallTypeId(int smallTypeId) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer("from TBProduct where smallType.id="+smallTypeId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}

	@Override
	public boolean existProductWithUserId(int userId) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer("from TBProduct where user.id="+userId);
		if(baseDAO.find(hql.toString()).size()>0){
			return true;
		}else{
			return false;			
		}
	}

	@Override
	public void deleteProduct(TBProduct product) {
		// TODO Auto-generated method stub
		baseDAO.delete(product);
	}

	@Override
	public void setProductWithHot(int productId) {
		// TODO Auto-generated method stub
		TBProduct product=baseDAO.get(TBProduct.class, productId);
		product.setType(1);
		//product.setMidifyTime(new Date());
		baseDAO.save(product);
	}

	@Override
	public void setProductWithNews(int productId) {
		// TODO Auto-generated method stub
		TBProduct product=baseDAO.get(TBProduct.class, productId);
		product.setType(2);
		//product.setMidifyTime(new Date());
		baseDAO.save(product);
	}

	@Override
	public void setProductWithStauts(int productId) {
		// TODO Auto-generated method stub
		TBProduct product=baseDAO.get(TBProduct.class, productId);
		product.setVerified(1);;
		//product.setMidifyTime(new Date());
		baseDAO.save(product);
	}

	@Override
	public void setProductWithStautsNo(int productId) {
		// TODO Auto-generated method stub
		TBProduct product=baseDAO.get(TBProduct.class, productId);
		product.setVerified(2);
		//product.setMidifyTime(new Date());
		baseDAO.save(product);
	}
	
	//by kongjun
	@Override
	public List<TBProduct> getProductByUser(TBUser user) {
		// TODO Auto-generated method stub
		StringBuffer hql=new StringBuffer("from TBProduct where user.id= " +user.getId());

		return baseDAO.find(hql.toString());
	}
}
