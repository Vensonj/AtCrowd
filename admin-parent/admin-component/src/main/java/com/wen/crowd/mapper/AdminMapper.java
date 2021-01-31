package com.wen.crowd.mapper;

import com.wen.crowd.entity.Admin;
import com.wen.crowd.entity.AdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
	long countByExample(AdminExample example);

	int deleteByExample(AdminExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(Admin record);

	int insertSelective(Admin record);

	List<Admin> selectByExample(AdminExample example);

	Admin selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") Admin record, @Param("example") AdminExample example);

	int updateByExample(@Param("record") Admin record, @Param("example") AdminExample example);

	int updateByPrimaryKeySelective(Admin record);

	int updateByPrimaryKey(Admin record);

	/*自己声明的方法*/
	List<Admin> selectAdminByKeyword(@Param("keyword") String keyword);
}