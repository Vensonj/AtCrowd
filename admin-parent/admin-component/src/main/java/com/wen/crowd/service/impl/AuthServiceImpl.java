package com.wen.crowd.service.impl;

import com.wen.crowd.entity.Auth;
import com.wen.crowd.entity.AuthExample;
import com.wen.crowd.mapper.AuthMapper;
import com.wen.crowd.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: Win
 * @CreateTime: 2021/3/22 20:17
 * @Description:
 */
@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthMapper authMapper;

    @Override
    public List<Auth> getAll() {
        return authMapper.selectByExample(new AuthExample());
    }

    @Override
    public List<Integer> getAssignedAuthIdByRoleId(Integer roleId) {
        return authMapper.selectAssignedAuthIdByRoleId(roleId);
    }

    @Override
    public void saveRoleAssignToAuthRelation(Map<String, List<Integer>> map) {
        // 获取 roleId 的值
        List<Integer> roleIdList = map.get("roleId");
        Integer roleId = roleIdList.get(0);
        // 删除旧的关联信息
        authMapper.deleteOldRelation(roleId);
        // 获取 authIdArray
        List<Integer> authIdList = map.get("authIdArray");

        // 判断是否有效
        if (authIdList != null && authIdList.size() > 0){
            authMapper.insertNewRelation(roleId,authIdList);
        }

    }
}
