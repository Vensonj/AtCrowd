package com.wen.crowd.service;

import com.wen.crowd.entity.Menu;

import java.util.List;

/**
 * @Author: Win
 * @CreateTime: 2021/3/11 14:43
 * @Description:
 */
public interface MenuService {
    List<Menu> getAll();

    void saveMenu(Menu menu);
}
