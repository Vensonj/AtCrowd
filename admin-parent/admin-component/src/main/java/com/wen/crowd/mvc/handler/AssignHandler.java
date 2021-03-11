package com.wen.crowd.mvc.handler;

import com.wen.crowd.entity.Auth;
import com.wen.crowd.entity.Role;
import com.wen.crowd.service.AdminService;
import com.wen.crowd.service.AuthService;
import com.wen.crowd.service.RoleService;
import com.wen.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @Author: Win
 * @CreateTime: 2021/3/22 18:07
 * @Description: 管理员与角色的对应关系
 */
@Controller
public class AssignHandler {
    @Autowired
    private AdminService adminService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private AuthService authService;

    @ResponseBody
    @RequestMapping("/assign/doRoleAssignToAuth")
    public ResultEntity<String> saveRoleAssignToAuthRelation(
            @RequestBody Map<String, List<Integer>> map
    ) {
        authService.saveRoleAssignToAuthRelation(map);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/assign/getAssignedAuthIdByRoleId")
    public ResultEntity<List<Integer>> getAssignedAuthIdByRoleId(
            @RequestParam("roleId") Integer roleId) {
        List<Integer> authIdList = authService.getAssignedAuthIdByRoleId(roleId);
        return ResultEntity.successWithData(authIdList);
    }

    @RequestMapping("/assign/toAssignRolePage")
    public String toAssignRolePage(@RequestParam("adminId") Integer adminId, Model model) {
        // 1、查询已分配的角色
        List<Role> assignedRoleList = roleService.getAssignedRole(adminId);
        // 2、查询未分配的角色
        List<Role> unAssignedRoleList = roleService.getUnAssignedRole(adminId);
        // 3、存入模型
        model.addAttribute("assignedRoleList", assignedRoleList);
        model.addAttribute("unAssignedRoleList", unAssignedRoleList);
        return "assign-role";
    }

    @RequestMapping("/assign/doAssignRole")
    public String saveAdminRoleRelation(@RequestParam("adminId") Integer adminId,
                                        @RequestParam("pageNum") Integer pageNum,
                                        @RequestParam("keyword") String keyword,
                                        @RequestParam(value = "roleIdList", required = false) List<Integer> roleIdList) {
        adminService.saveAdminRoleRelation(adminId, roleIdList);
        return "redirect:/admin/get/page?pageNum=" + pageNum + "&keyword=" + keyword;
    }

    @ResponseBody
    @RequestMapping("/assign/getAllAuth")
    public ResultEntity<List<Auth>> getAllAuth() {
        final List<Auth> authList = authService.getAll();
        return ResultEntity.successWithData(authList);
    }
}
