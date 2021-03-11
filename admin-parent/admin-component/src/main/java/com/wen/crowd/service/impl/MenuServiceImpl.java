package com.wen.crowd.service.impl;

import com.wen.crowd.entity.Menu;
import com.wen.crowd.entity.MenuExample;
import com.wen.crowd.mapper.MenuMapper;
import com.wen.crowd.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Win
 * @CreateTime: 2021/3/11 14:43
 * @Description:
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    MenuMapper menuMapper;

    @Override
    public List<Menu> getAll() {
        return menuMapper.selectByExample(new MenuExample());
    }

    @Override
    public void saveMenu(Menu menu) {
        menuMapper.insert(menu);
    }
}
