package com.yicunyipin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.Admin;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.service.AdminService;
import com.yicunyipin.util.StringUtil;


@Service("adminService")
public class AdminServiceImpl implements AdminService{

	@Resource
	private BaseDAO<Admin> baseDAO;
	
	@Override
	public void saveAdmin(Admin Admin) {
		// TODO Auto-generated method stub
		baseDAO.saveOrUpdate(Admin);
	}

	
	@Override
	public Admin login(Admin Admin) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Admin u where u.userName=? and u.password=?");
		param.add(Admin.getUserName());
		param.add(Admin.getPassword());
		return baseDAO.get(hql.toString(), param);
	}


	@Override
	public void updateAdminPassWord(Admin admin) {
		// TODO Auto-generated method stub
		baseDAO.update(admin);
	}


	@Override
	public List<Admin> findAdminListWithType(Admin s_admin, PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from Admin");
		if(s_admin!=null){
			if(StringUtil.isNotEmpty(s_admin.getUserName())){
				hql.append(" and username like ?");
				param.add("%"+s_admin.getUserName()+"%");
			}
		}
		if(s_admin.getType()>0)
		{
			StringBuffer type=new StringBuffer(" and type=");
			type.append(s_admin.getType());
			hql.append(type);
		}
		else{
			StringBuffer type=new StringBuffer(" and type !=");
			type.append(0);
			hql.append(type);
		}
		
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return null;			
		}
	}


	@Override
	public Long getAdminCountWithType(Admin s_admin) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from Admin");
		if(s_admin!=null){
			if(StringUtil.isNotEmpty(s_admin.getUserName())){
				hql.append(" and username like ?");
				param.add("%"+s_admin.getUserName()+"%");
			}
			
			if(s_admin.getType()>0)
			{
				StringBuffer type=new StringBuffer(" and type=");
				type.append(s_admin.getType());
				hql.append(type);
			}
			else{
				StringBuffer type=new StringBuffer(" and type!=");
				type.append(0);
				hql.append(type);
			}
		}
		//hql.append(" and status=1");
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);
	}


	@Override
	public Admin getAdminById(int adminId) {
		// TODO Auto-generated method stub
		return baseDAO.get(Admin.class, adminId);
	}


	@Override
	public void delete(Admin admin) {
		// TODO Auto-generated method stub
		baseDAO.delete(admin);
	}


	@Override
	public boolean existAdminWithUserName(String userName) {
		// TODO Auto-generated method stub
		String hql="select count(*) from Admin where userName=?";
		long count=baseDAO.count(hql, new Object[]{userName});
		if(count>0){
			return true;
		}else{
			return false;			
		}
	}
	
	
	

}
