package com.wen.crowd.mvc.handler;

import com.wen.crowd.entity.Admin;
import com.wen.crowd.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author wen
 * @create 2021 1月 14 星期四 22:25
 * @description
 */
@Controller
public class TestHandler {
    @Autowired
    AdminService adminService;

    @RequestMapping("/test/send")
    public String testSend(@RequestParam("array[]") List<Integer> array){
        System.out.println(array);
        return "target";
    }
    @RequestMapping("/test/ssm")
    public String test(Model model) {
        List<Admin> adminList = adminService.listAll();
        model.addAttribute("adminList", adminList);
//        int i = 10 / 0;
        return "target";
    }

}
