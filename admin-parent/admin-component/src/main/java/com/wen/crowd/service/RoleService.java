package com.wen.crowd.service;

import com.github.pagehelper.PageInfo;
import com.wen.crowd.entity.Role;

import java.util.List;

/**
 * @author wen
 * @create 2021 1月 26 星期二 21:59
 * @description
 */
public interface RoleService {

	PageInfo<Role> getPageInfo(Integer pageNum, Integer pageSize, String keyword);

    void saveRole(Role role);

    void updateRole(Role role);

    void removeRole(List<Integer> roleIdList);
}
