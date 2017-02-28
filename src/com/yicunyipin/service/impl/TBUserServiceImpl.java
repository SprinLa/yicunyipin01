package com.yicunyipin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.TBUser;
import com.yicunyipin.entity.User;
import com.yicunyipin.service.TBUserService;
import com.yicunyipin.util.StringUtil;

@Service("tbUserService")
public class TBUserServiceImpl implements TBUserService {

	@Resource
	private BaseDAO<TBUser> baseDAO;
	
	@Override
	public void updateUser(TBUser user) {
		// TODO Auto-generated method stub
		baseDAO.merge(user);
		
	}

	@Override
	public void saveUser(TBUser user) {
		// TODO Auto-generated method stub
		baseDAO.saveOrUpdate(user);
	}

	@Override
	public boolean existUserWithUserName(String userName) {
		// TODO Auto-generated method stub
		String hql="select count(*) from TBUser where username=?";
		long count=baseDAO.count(hql, new Object[]{userName});
		if(count>0){
			return true;
		}else{
			return false;			
		}
	}

	@Override
	public boolean existUserWithEmail(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public TBUser login(TBUser user) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBUser u where u.userName=? and u.password=?");
		param.add(user.getUserName());
		param.add(user.getPassword());
		return baseDAO.get(hql.toString(), param);
	}

	@Override
	public List<TBUser> findUserList(TBUser s_user, PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBUser");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and username like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return null;			
		}
	}

	@Override
	public Long getUserCount(TBUser s_user) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from TBUser");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and username like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
		}
		//hql.append(" and status=1");
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);

	}
	
	

	@Override
	public Long getUserCountWithVerified(TBUser s_user) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from TBUser");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and username like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
			
			if(s_user.getVerified()>=0)
			{
				StringBuffer verifiedStatus=new StringBuffer(" and verified_status=");
				verifiedStatus.append(s_user.getVerified());
				hql.append(verifiedStatus);
			}
			else{
				StringBuffer verifiedStatus=new StringBuffer(" and verified_status!=");
				verifiedStatus.append(1);
				hql.append(verifiedStatus);
			}
			
			if(s_user.getType()>0)
			{
				StringBuffer type=new StringBuffer(" and type=");
				type.append(s_user.getType());
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
	public void delete(TBUser user) {
		// TODO Auto-generated method stub
		baseDAO.delete(user);
	}

	@Override
	public TBUser getUserById(int id) {
		// TODO Auto-generated method stub
		return baseDAO.get(TBUser.class, id);
	}

	@Override
	public List<TBUser> findUserListByStatus(TBUser s_user, PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBUser");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and username like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
		}
		StringBuffer verifiedStatus=new StringBuffer(" and verified_status=");
		verifiedStatus.append(s_user.getVerified());
		hql.append(verifiedStatus);
		if(s_user.getType()>0)
		{
			StringBuffer type=new StringBuffer(" and type=");
			type.append(s_user.getType());
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
	public boolean existUserWithTrueName(String trueName) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<TBUser> findNewUserList(TBUser s_user, PageBean pageBean) {
		// TODO Auto-generated method stub
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from TBUser");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and username like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
		}
		StringBuffer verifiedStatus=new StringBuffer(" and verified_status!=");
		verifiedStatus.append(1);
		hql.append(verifiedStatus);
		if(s_user.getType()>0)
		{
			StringBuffer type=new StringBuffer(" and type=");
			type.append(s_user.getType());
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
	

}
