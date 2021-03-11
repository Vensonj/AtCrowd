package com.wen.crowd.service;

import com.github.pagehelper.PageInfo;
import com.wen.crowd.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author wen
 * @create 2021 1月 14 星期四 20:34
 * @description
 */

public interface AdminService {
	//保存管理员信息
	void saveAdmin(Admin admin);

	//查询所有的管理员信息
	List<Admin> listAll();

	//根据用户名查询管理员信息
	Admin queryAdminByAcct(@Param("loginAcct") String loginAcct,
	                       @Param("userPwd") String userPwd);

	//分页查询 keyword:查询关键字    pageNum:当前页码    pageSize:每页显示多少条
	PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize);

	void removeAdminById(Integer adminId);

	Admin getAdminById(Integer adminId);

	void updateAdmin(Admin admin);

    void saveAdminRoleRelation(Integer adminId, List<Integer> roleIdList);
}
