package com.wen.crowd.mvc.handler;

import com.github.pagehelper.PageInfo;
import com.wen.crowd.constant.CrowdConstant;
import com.wen.crowd.entity.Admin;
import com.wen.crowd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * @author wen
 * @create 2021 1月 17 星期日 22:26
 * @description 管理员登录控制流程
 */
@Controller
public class AdminHandler {
	@Autowired
	private AdminService adminService;

	//更新记录
	@PostMapping("/admin/update")
	public String updateAdmin(Admin admin,
	                          @RequestParam("pageNum") Integer pageNum,
	                          @RequestParam(value = "keyword",required = false) String keyword) {
		adminService.updateAdmin(admin);
		return "redirect:/admin/get/page?pageNum=" + pageNum + "&keyword=" + keyword;
	}

	//跳转至修改页面
	@GetMapping("/admin/toEditPage")
	public String getAdminById(@RequestParam("adminId") Integer adminId, Model model) {
		//执行更新操作
		Admin admin = adminService.getAdminById(adminId);

		//将查询到的要修改的admin对象存入到model中，以待后续使用
		model.addAttribute("admin", admin);
		//页面跳转
		return "admin-edit";
	}

	//新增记录
	@PostMapping("/admin/saveAdmin")
	public String savaAdmin(Admin admin) {
		adminService.saveAdmin(admin);
		return "redirect:/admin/get/page?pageNum=" + Integer.MAX_VALUE;
	}

	//删除数据记录
	@RequestMapping({"/admin/remove/{adminId}/{pageNum}/{keyword}", "/admin/remove/{adminId}/{pageNum}/"})
	public String removeAdmin(@PathVariable("adminId") Integer adminId,
	                          @PathVariable("pageNum") Integer pageNum,
	                          @PathVariable(value = "keyword", required = false) String keyword) {
		//执行删除操作
		adminService.removeAdminById(adminId);
		//页面跳转，返回原来的页面,如果keyword不存在，那么返回另一个链接
		if (keyword == null || keyword.length() == 0)
			return "redirect: /admin/get/page?pageNum=" + pageNum;
		return "redirect: /admin/get/page?pageNum=" + pageNum + "&keyword=" + keyword;
	}

	//分页查询
	@RequestMapping("/admin/get/page")
	public String getPageInfo(
			@RequestParam(value = "keyword", defaultValue = "") String keyword,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
			@RequestParam(value = "pageSize", defaultValue = "7") Integer pageSize,
			Model model) {
		// 调用service方法获取PageInfo对象
		PageInfo<Admin> pageInfo = adminService.getPageInfo(keyword, pageNum, pageSize);
//		System.out.println(pageInfo);
		// 将PageInfo对象存入模型
		model.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO, pageInfo);
		return "admin-page";
	}

	// 管理员登录
	@PostMapping("/admin/login")
	public String toLogin(@RequestParam("loginAcct") String loginAcct,
	                      @RequestParam("userPwd") String userPwd,
	                      HttpSession session) {
		// 调用Service方法执行登录检查 判断数据库中是否存在相同的用户名
		Admin admin = adminService.queryAdminByAcct(loginAcct, userPwd);
		// 如果方法能够返回admin对象，说明登录成功，否则会抛出异常
		session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN, admin);
		return "redirect:/admin/toMainPage";
	}

	//管理员退出
	@GetMapping("/admin/logout")
	public String doLogout(HttpSession session) {
		//让session强制失效
		session.invalidate();
		return "redirect:/admin/toLoginPage";
	}
}
