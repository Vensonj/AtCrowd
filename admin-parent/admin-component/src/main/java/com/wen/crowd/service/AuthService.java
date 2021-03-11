package com.wen.crowd.service;

import com.wen.crowd.entity.Auth;

import java.util.List;
import java.util.Map;

/**
 * @Author: Win
 * @CreateTime: 2021/3/22 20:17
 */
public interface AuthService {
    List<Auth> getAll();

    List<Integer> getAssignedAuthIdByRoleId(Integer roleId);

    void saveRoleAssignToAuthRelation(Map<String, List<Integer>> map);
}
