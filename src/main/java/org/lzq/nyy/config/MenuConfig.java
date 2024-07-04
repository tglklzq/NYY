package org.lzq.nyy.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuConfig {
    public static final Map<Integer, List<String>> ROLE_MENU_MAP = new HashMap<>();

    static {
        // 角色的菜单映射
        ROLE_MENU_MAP.put(1, List.of("客户管理", "房间管理", "订单管理", "员工管理",
                "系统管理", "系统设置", "系统日志", "系统公告"));
        ROLE_MENU_MAP.put(2, List.of("系统管理", "系统设置", "系统日志", "系统公告"));
        ROLE_MENU_MAP.put(3, List.of("菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7", "菜单8"));
        ROLE_MENU_MAP.put(4, List.of("菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7", "菜单8"));
    }

    public static final Map<String, List<String>> MENU_ITEMS = new LinkedHashMap<>(); // 使用LinkedHashMap

    static {
        MENU_ITEMS.put("客户管理", List.of("子菜单1", "子菜单2", "子菜单3", "子菜单4", "子菜单5"));
        MENU_ITEMS.put("房间管理", List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("订单管理", List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("员工管理", List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("系统管理", List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("系统设置", List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("系统日志", List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("系统公告", List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
    }

    // 新增方法：根据角色ID获取菜单数据并格式化为前端需要的结构
    public static List<Map<String, Object>> getMenuForRole(int roleId) {
        List<Map<String, Object>> menuList = new ArrayList<>();
        List<String> menus = ROLE_MENU_MAP.get(roleId);

        if (menus != null) {
            for (String menu : menus) {
                Map<String, Object> menuMap = new HashMap<>();
                menuMap.put("title", menu);

                List<String> subMenus = MENU_ITEMS.get(menu);
                if (subMenus != null) {
                    List<Map<String, String>> children = new ArrayList<>();
                    for (String subMenu : subMenus) {
                        Map<String, String> childMap = new HashMap<>();
                        childMap.put("title", subMenu);
                        children.add(childMap);
                    }
                    menuMap.put("children", children);
                } else {
                    menuMap.put("children", new ArrayList<>()); // 确保有空子菜单列表
                }
                menuList.add(menuMap);
            }
        }

        return menuList;
    }
}
