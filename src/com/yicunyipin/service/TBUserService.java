package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.PageBean;
import com.yicunyipin.entity.TBUser;


public interface TBUserService {

	public void updateUser(TBUser user);
	public void saveUser(TBUser user);
	public boolean existUserWithUserName(String userName);
	
	public boolean existUserWithEmail(String userName);
	
	public TBUser login(TBUser user);
	
	/**
	 * 分页查询用户
	 * @param s_user
	 * @param pageBean
	 * @return
	 */
	public List<TBUser> findUserList(TBUser s_user,PageBean pageBean);
	
	/**
	 * 查询用户数量
	 * @param s_user
	 * @return
	 */
	public Long getUserCount(TBUser s_user);
	
	
	/**
	 * 删除用户
	 * @param user
	 */
	public void delete(TBUser user);
	
	/**
	 * 通过id获取用户实体
	 * @return
	 */
	public TBUser getUserById(int id);
	List<TBUser> findUserListByStatus(TBUser s_user, PageBean pageBean);
	boolean existUserWithTrueName(String trueName);
	public Long getUserCountWithVerified(TBUser s_user);
	List<TBUser> findNewUserList(TBUser s_user, PageBean pageBean);
}
