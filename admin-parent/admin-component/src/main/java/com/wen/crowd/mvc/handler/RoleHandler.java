package com.wen.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.wen.crowd.entity.Role;
import com.wen.crowd.service.RoleService;
import com.wen.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wen
 * @create 2021 1月 26 星期二 21:58
 * @description
 */
@Controller
public class RoleHandler {
	@Autowired
	RoleService roleService;

	/*
	按照Ajax请求返回数据
	 */
	@ResponseBody
	@RequestMapping("/role/get/page/info")
	public ResultEntity<PageInfo<Role>> getPageInfo(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize,
			@RequestParam(value = "keyword", defaultValue = "") String keyword
	) {
		// 调用Service方法获取分页数据
		PageInfo<Role> pageInfo = roleService.getPageInfo(pageNum, pageSize, keyword);
		// 封装到ResultEntity对象中返回
		return ResultEntity.successWithData(pageInfo);

	}
}
