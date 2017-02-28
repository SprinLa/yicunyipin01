package com.yicunyipin.service;

import java.util.Date;
import java.util.List;

import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.TBOrder;

import java.util.List;

public interface TBOrderService {
    public void saveOrder(TBOrder order);
    public void saveOrUpdateOrder(TBOrder order);

    public List<TBOrder> findOrder(TBOrder s_order, PageBean pageBean);

    public Long getOrderCount(TBOrder s_order);

    public void updateOrderStatus(int status, String orderNo);

    public TBOrder getOrderById(int id);

    public TBOrder getOrder(int BuyerId, int proId);
    
    //by kongjun
    public List<TBOrder> findOrderBySeller(int productId, Date startTime, Date endTime);
}
