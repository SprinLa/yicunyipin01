package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.News;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.Policy;

public interface PolicyService {
	public void savePolicy(Policy policy);
	public List<Policy> findPolicyList(Policy s_policy,PageBean pageBean);
	public Long getPolicyCount(Policy s_policy);
	public Policy getPolicyById(int policyId);
	public void delete(Policy policy);
}
