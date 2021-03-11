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

    @Override
    public void updateMenu(Menu menu) {
        /*
        由于没有传入 pid 属性，所以应该使用有选择的更新，以免 pid 字段被置空
         */
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public void removeMenu(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }
}
