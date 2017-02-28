package com.yicunyipin.service;

import java.util.List;

import com.yicunyipin.entity.Admin;
import com.yicunyipin.entity.PageBean;

public interface AdminService {

	public void saveAdmin(Admin admin);
	
	public void updateAdminPassWord(Admin admin);
	
	public Admin login(Admin admin);
	public List<Admin> findAdminListWithType(Admin s_admin,PageBean pageBean);
	/**
	 * 查询系统操作员数量
	 * @param s_admin
	 * @return
	 */
	public Long getAdminCountWithType(Admin s_admin);
	public Admin getAdminById(int adminId);
	public void delete(Admin admin);
	public boolean existAdminWithUserName(String userName);
}
