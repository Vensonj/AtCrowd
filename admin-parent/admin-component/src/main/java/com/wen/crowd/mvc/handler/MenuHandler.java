package com.wen.crowd.mvc.handler;

import com.wen.crowd.entity.Menu;
import com.wen.crowd.service.MenuService;
import com.wen.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Win
 * @CreateTime: 2021/3/11 14:42
 * @Description:
 */
@Controller
public class MenuHandler {
    @Autowired
    MenuService menuService;

    @ResponseBody
    @RequestMapping("/menu/save")
    public ResultEntity<String> saveMenu(Menu menu) {
        menuService.saveMenu(menu);
        return ResultEntity.successWithoutData();
    }

    @ResponseBody
    @RequestMapping("/menu/get/whole/tree")
    public ResultEntity<Menu> getWholeTreeNew() {
        // 查询全部的 Menu 对象
        List<Menu> menuList = menuService.getAll();
        // 声明一个变量用来存储找到的根节点
        Menu root = null;
        // 遍历menuList,将每个menu存入menuHashMap,key为menu的id,值为menu对象
        Map<Integer, Menu> menuHashMap = new HashMap<>();
        // 遍历menuList
        for (Menu menu : menuList) {
            // 获取当前对象的 id 属性值
            Integer id = menu.getId();
            // 存入menuHashMap
            menuHashMap.put(id, menu);

        }
        // 再次遍历menuList寻找父节点
        for (Menu menu : menuList) {

            // 获取每个节点的 pid
            Integer pid = menu.getPid();

            // 检查 pid 是否为null
            if (pid == null) {
                // 把当前正在遍历的这个对象赋给 root
                root = menu;
                // 停止本次循环，进行下一次循环
                continue;
            }
            // 如果 pid 不为 null，说明当前节点有父节点，找到父节点就可以进行组装，建立父子关系
            Menu menuFather = menuHashMap.get(pid);
            menuFather.getChildren().add(menu);
        }
        return ResultEntity.successWithData(root);
    }
}
//    @ResponseBody
//    @RequestMapping("/menu/get/whole/tree")
//    public ResultEntity<Menu> getWholeTreeOld() { // 时间复杂度为On2
//        // 查询全部的 Menu 对象
//        List<Menu> menuList = menuService.getAll();
//        // 声明一个变量用来存储找到的根节点
//        Menu root = null;
//        // 遍历menuList
//        for (Menu menu : menuList) {
//            // 获取当前对象的 pid 属性值
//            Integer pid = menu.getPid();
//            // 检查 pid 是否为null
//            if (pid == null) {
//                // 把当前正在遍历的这个对象赋给 root
//                root = menu;
//                // 停止本次循环，进行下一次循环
//                continue;
//            }
//            // 如果 pid 不为 null，说明当前节点有父节点，找到父节点就可以进行组装，建立父子关系
//            for (Menu m : menuList) {
//                Integer id = m.getId();
//                if (Objects.equals(id, pid)) {
//                    m.getChildren().add(menu);
//                    break;
//                }
//            }
//        }
//        return ResultEntity.successWithData(root);
//    }
