package com.yicunyipin.service.impl;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.TBOrder;
import com.yicunyipin.service.TBOrderService;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;

//@Service("tbOrderService")
public class TBOrderImpl implements TBOrderService {

	@Resource
	private BaseDAO<TBOrder> baseDAO;

	@Override
	public void saveOrder(TBOrder order) {

	}

	@Override
	public void saveOrUpdateOrder(TBOrder order) {

	}

	@Override
	public List<TBOrder> findOrder(TBOrder s_order, PageBean pageBean) {
		return null;
	}

	@Override
	public Long getOrderCount(TBOrder s_order) {
		return null;
	}

	@Override
	public void updateOrderStatus(int status, String orderNo) {

	}

	@Override
	public TBOrder getOrderById(int id) {
		return null;
	}

	@Override
	public TBOrder getOrder(int BuyerId, int proId) {
		return null;
	}

	@Override
	public List<TBOrder> findOrderBySeller(int productId, Date startTime,
			Date endTime) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
