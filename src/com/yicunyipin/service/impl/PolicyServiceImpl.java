package com.yicunyipin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Policy;
import com.yicunyipin.service.PolicyService;
import org.springframework.stereotype.Service;

@Service("policyService")
public class PolicyServiceImpl implements PolicyService {
	
	@Resource
	private BaseDAO<Policy> baseDAO;
	public PolicyServiceImpl() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void savePolicy(Policy policy) {
		// TODO Auto-generated method stub
		baseDAO.saveOrUpdate(policy);
	}

	@Override
	public List<Policy> findPolicyList(Policy s_policy, PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Policy");

		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"), param, pageBean);
		}else{
			return null;
		}
		
	}
	//产品政策数量
	@Override
	public Long getPolicyCount(Policy s_policy) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Policy");
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}

	
	@Override
	public Policy getPolicyById(int policyId) {
		// TODO Auto-generated method stub
		return baseDAO.get(Policy.class, policyId);
	}

	@Override
	public void delete(Policy policy) {
		// TODO Auto-generated method stub
		baseDAO.delete(policy);
	}
	
	

}
