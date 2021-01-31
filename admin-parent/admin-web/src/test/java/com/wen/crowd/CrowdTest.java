package com.wen.crowd;

import com.wen.crowd.entity.Admin;
import com.wen.crowd.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-mybatis.xml"})
public class CrowdTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    AdminMapper adminMapper;

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
