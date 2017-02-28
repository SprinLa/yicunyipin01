package com.yicunyipin.service.impl;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yicunyipin.dao.BaseDAO;
import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.User;
import com.yicunyipin.service.UserService;
import com.yicunyipin.util.StringUtil;


@Service("userService")
public class UserServiceImpl implements UserService{

	@Resource
	private BaseDAO<User> baseDAO;
	
	@Override
	public void saveUser(User user) {
		// TODO Auto-generated method stub
		baseDAO.saveOrUpdate(user);
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		baseDAO.merge(user);
	}
	
	@Override
	public boolean existUserWithUserName(String userName) {
		String hql="select count(*) from User where userName=?";
		long count=baseDAO.count(hql, new Object[]{userName});
		if(count>0){
			return true;
		}else{
			return false;			
		}
	}
	
	@Override
	public boolean existUserWithTrueName(String trueName) {
		String hql="select count(*) from User where trueName=?";
		long count=baseDAO.count(hql, new Object[]{trueName});
		if(count>0){
			return true;
		}else{
			return false;			
		}
	}
	
	@Override
	public boolean existUserWithEmail(String email) {
		String hql="select count(*) from User where email=?";
		long count=baseDAO.count(hql, new Object[]{email});
		if(count>0){
			return true;
		}else{
			return false;			
		}
	}

	@Override
	public User login(User user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User u where u.userName=? and u.password=?");
		param.add(user.getUserName());
		param.add(user.getPassword());
		return baseDAO.get(hql.toString(), param);
	}

	
	//查询所有用户
	@Override
	public List<User> findUserList(User s_user, PageBean pageBean) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("from User");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and userName like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
		}
		if(pageBean!=null){
			return baseDAO.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
		}else{
			return null;			
		}
	}

	
	//安生和条件查询用户
		@Override
		public List<User> findUserListByStatus(User s_user, PageBean pageBean) {
			List<Object> param=new LinkedList<Object>();
			StringBuffer hql=new StringBuffer("from User");
			if(s_user!=null){
				if(StringUtil.isNotEmpty(s_user.getUserName())){
					hql.append(" and userName like ?");
					param.add("%"+s_user.getUserName()+"%");
				}
			}
			StringBuffer status=new StringBuffer(" and status=");
			status.append(s_user.getStatus());
			hql.append(status);
			if(pageBean!=null){
				return baseDAO.find(hql.toString().replaceFirst("and", "where"),param,pageBean);
			}else{
				return null;			
			}
		}
	
	
	
	@Override
	public Long getUserCount(User s_user) {
		List<Object> param=new LinkedList<Object>();
		StringBuffer hql=new StringBuffer("select count(*) from User");
		if(s_user!=null){
			if(StringUtil.isNotEmpty(s_user.getUserName())){
				hql.append(" and userName like ?");
				param.add("%"+s_user.getUserName()+"%");
			}
		}
		//hql.append(" and status=1");
		return baseDAO.count(hql.toString().replaceFirst("and", "where"), param);

	}

	@Override
	public void delete(User user) {
		baseDAO.delete(user);
		
	}

	@Override
	public User getUserById(int id) {
		return baseDAO.get(User.class, id);
	}

}
