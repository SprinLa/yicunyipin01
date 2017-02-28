package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.User;


public interface UserService {

	public void updateUser(User user);
	public void saveUser(User user);
	public boolean existUserWithUserName(String userName);
	
	public boolean existUserWithEmail(String userName);
	
	public User login(User user);
	
	/**
	 * 分页查询用户
	 * @param s_user
	 * @param pageBean
	 * @return
	 */
	public List<User> findUserList(User s_user,PageBean pageBean);
	
	/**
	 * 查询用户数量
	 * @param s_user
	 * @return
	 */
	public Long getUserCount(User s_user);
	
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(User user);
	
	/**
	 * 通过id获取用户实体
	 * @return
	 */
	public User getUserById(int id);
	List<User> findUserListByStatus(User s_user, PageBean pageBean);
	boolean existUserWithTrueName(String trueName);
	
}
