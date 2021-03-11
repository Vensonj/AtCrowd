package com.wen.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wen.crowd.constant.CrowdConstant;
import com.wen.crowd.entity.Admin;
import com.wen.crowd.entity.AdminExample;
import com.wen.crowd.exception.LoginAcctAlreadyInUseException;
import com.wen.crowd.exception.LoginAcctAlreadyInUseForUpdateException;
import com.wen.crowd.exception.LoginFailedException;
import com.wen.crowd.mapper.AdminMapper;
import com.wen.crowd.service.AdminService;
import com.wen.crowd.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author wen
 * @create 2021 1月 14 星期四 20:35
 * @description
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        //密码加密
        String userPwd = admin.getUserPwd();
        String md5Pwd = MD5Util.md5(userPwd);
        admin.setUserPwd(md5Pwd);
        try {
            adminMapper.insert(admin);
        } catch (Exception e) {
//			e.printStackTrace();
            if (e instanceof DuplicateKeyException)
                throw new LoginAcctAlreadyInUseException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_EXISTS);
        }
    }

    @Override
    public List<Admin> listAll() {
        return adminMapper.selectByExample(new AdminExample());
    }


    @Override
    public Admin queryAdminByAcct(String loginAcct, String userPwd) {
        // 1、根据登录账号查询Admin对象
        // 1️⃣创建AdminExample对象
        AdminExample adminExample = new AdminExample();
        // 2️⃣创建Criteria对象
        AdminExample.Criteria criteria = adminExample.createCriteria();
        // 3️⃣在Criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);
        // 4️⃣调用AdminMapper的方法执行查询
        List<Admin> adminList = adminMapper.selectByExample(adminExample);
        // 2、判断Admin对象是否为null
        if (adminList == null || adminList.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        if (adminList.size() > 1) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGINACCT_NOT_UNIQUE);
        }
        Admin admin = adminList.get(0);
        // 3、如果Admin对象为null则抛出异常
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }
        // 4、如果Admin对象不为null则将数据库密码从Admin对象中取出
        String userPwdFromDB = admin.getUserPwd(); //数据库取出的密码
        // 5、将表单提交的明文密码进行加密
        String userPwdFromFromMd5 = MD5Util.md5(userPwd);//表单提交的密码加密
        // 6、对密码进行比较
        if (Objects.equals(userPwdFromDB, userPwdFromFromMd5))
            // 7、如果一直则返回Admin对象
            return admin;
        else
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        // 8、如果结果不一致，还是抛出异常

    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1、调用PageHelper的静态方法开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2、执行查询
        List<Admin> adminList = adminMapper.selectAdminByKeyword(keyword);
        // 3、封装到PageInfo对象中
        return new PageInfo<Admin>(adminList);
    }

    @Override
    public void removeAdminById(Integer adminId) {
        adminMapper.deleteByPrimaryKey(adminId);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminMapper.selectByPrimaryKey(adminId);
    }

    @Override
    public void updateAdmin(Admin admin) {
        //有选择的更新，没有更改的字段不更新
        try {
            adminMapper.updateByPrimaryKeySelective(admin);
        } catch (Exception e) {
//			e.printStackTrace();
            if (e instanceof DuplicateKeyException)
                throw new LoginAcctAlreadyInUseForUpdateException(CrowdConstant.MESSAGE_LOGIN_ACCT_ALREADY_EXISTS);
        }
    }

    @Override
    public void saveAdminRoleRelation(Integer adminId, List<Integer> roleIdList) {
        // 1、根据 adminId 删除数据库中原有的关系
        adminMapper.deleteOldRelation(adminId);

        // 2、然后再添加新的关系
        if (roleIdList != null && roleIdList.size() > 0) {
            adminMapper.insertNewRelation(adminId, roleIdList);
        }
    }

}
