package com.yicunyipin.service.impl;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.TBOrder;
import com.yicunyipin.service.TBOrderService;
import com.yicunyipin.util.StringUtil;

@Service("tbOrderService")
public class TBOrderIServiceImpl implements TBOrderService {

	@Resource
	private BaseDAO<TBOrder> baseDAO;

	@Override
	public void saveOrder(TBOrder order) {
		// TODO Auto-generated method stub
		baseDAO.save(order);
	}

    @Override
    public void saveOrUpdateOrder(TBOrder order){
        baseDAO.saveOrUpdate(order);
    }

	@Override
	public List<TBOrder> findOrder(TBOrder s_order, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBOrder");
		if(s_order!=null){
			if(s_order.getBuyerUser()!=null&&s_order.getBuyerUser().getId()!=0){
				hql.append(" and buyerUser.id=?");
				param.add(s_order.getBuyerUser().getId());
			}
			if(StringUtil.isNotEmpty(s_order.getBuyerName())){
				hql.append(" and buyerName like ?");
				param.add("%"+s_order.getBuyerName()+"%");
			}
		}
		hql.append(" order by buyerTime desc");
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param,pageBean);
		}else{
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param);
		}
	}

	@Override
	public void updateOrderStatus(int status, String orderNo) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		String hql="update TBOrder set status=? where orderNo=?";
		param.add(status);
		param.add(orderNo);
		baseDAO.executeHql(hql, param);
	}

	@Override
	public Long getOrderCount(TBOrder s_order) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from TBOrder");
		if(s_order!=null){
            if(s_order.getBuyerUser()!=null&&s_order.getBuyerUser().getId()!=0){
                hql.append(" and buyerUser.id=?");
                param.add(s_order.getBuyerUser().getId());
            }
            if(StringUtil.isNotEmpty(s_order.getBuyerName())){
                hql.append(" and buyerName like ?");
                param.add("%"+s_order.getBuyerName()+"%");
            }
		}
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	@Override
    public TBOrder getOrderById(int id) {
        return baseDAO.get(TBOrder.class, id);
    }
    @Override
    public TBOrder getOrder(int BuyerId,int proId) {
       List<TBOrder> list =  baseDAO.find("from TBOrder where buyerUser.id="+BuyerId+" and product.id="+proId);
        if (list != null && list.size()>0){
            return list.get(0);
        }
        return null;
	}
    // by kongjun
    @Override
	public List<TBOrder> findOrderBySeller(int productId, Date startTime, Date endTime) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBOrder");
		hql.append(" where product.id= ? and buyerTime >= ? and buyerTime < ?");
		param.add(productId);
		param.add(startTime);
		param.add(endTime);
		return baseDAO.find(hql.toString(), param);
	}
}
