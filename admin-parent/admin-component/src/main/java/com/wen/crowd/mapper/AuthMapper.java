package com.wen.crowd.mapper;

import com.wen.crowd.entity.Auth;
import com.wen.crowd.entity.AuthExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuthMapper {
    long countByExample(AuthExample example);

    int deleteByExample(AuthExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Auth record);

    int insertSelective(Auth record);

    List<Auth> selectByExample(AuthExample example);

    Auth selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByExample(@Param("record") Auth record, @Param("example") AuthExample example);

    int updateByPrimaryKeySelective(Auth record);

    int updateByPrimaryKey(Auth record);

    List<Integer> selectAssignedAuthIdByRoleId(@Param("roleId") Integer roleId);

    void deleteOldRelation(Integer roleId);

    void insertNewRelation(@Param("roleId") Integer roleId, @Param("authIdList") List<Integer> authIdList);
}