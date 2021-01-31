package com.wen.crowd;

import com.wen.crowd.entity.Admin;
import com.wen.crowd.entity.Role;
import com.wen.crowd.mapper.AdminMapper;
import com.wen.crowd.mapper.RoleMapper;
import com.wen.crowd.service.AdminService;
import com.wen.crowd.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml", "classpath:spring-tx.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdminService adminService;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RoleService roleService;

    @Test
    public void testRole() {
        for (int i = 0; i < 107; i++) {
            roleMapper.insert(new Role(i + 1, "宋定娥" + (i + 1) + "号"));
        }
    }

    @Test
    public void testAddAdmin() {
        for (int i = 1; i <= 617; i++) {
            Admin admin = new Admin(null, "jerry" + i, "123456", "杰瑞" + i + "号", "Jerry-" + i + "@gmail.com");
            adminService.saveAdmin(admin);
        }

    }

    @Test
    public void test2() {
        Admin admin = new Admin(null, "tom", "123456", "汤姆", "Tomcat@gmail.com");
        adminMapper.insert(admin);
    }

    @Test
    public void test1() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        System.out.println(connection);
    }

}
