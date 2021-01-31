package com.wen.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.crowd.entity.Role;
import com.wen.crowd.mapper.RoleMapper;
import com.wen.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wen
 * @create 2021 1月 26 星期二 21:59
 * @description
 */
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	RoleMapper roleMapper;

	@Override
	public PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword) {
		// 开启分页功能
		PageHelper.startPage(pageNum, pageSize);
		// 执行查询
		List<Role> roleList = roleMapper.selectRoleByKeyword(keyword);
		// 封装为PageInfo返回
		return new PageInfo<>(roleList);
	}
}
