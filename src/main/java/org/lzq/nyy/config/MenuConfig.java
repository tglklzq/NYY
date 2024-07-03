package org.lzq.nyy.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuConfig {
    public static final Map<Integer, List<String>> ROLE_MENU_MAP = new HashMap<>();

    static {
        // 角色的菜单映射
        ROLE_MENU_MAP.put(1, List.of("菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7", "菜单8"));
        ROLE_MENU_MAP.put(2, List.of("菜单4", "菜单5", "菜单6", "菜单7", "菜单8"));
        ROLE_MENU_MAP.put(3, List.of("菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7", "菜单8"));
        ROLE_MENU_MAP.put(4, List.of("菜单1", "菜单2", "菜单3", "菜单4", "菜单5", "菜单6", "菜单7", "菜单8"));
    }

    public static final Map<String, List<String>> MENU_ITEMS = new LinkedHashMap<>(); // 使用LinkedHashMap

    static {
        MENU_ITEMS.put("菜单1", List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("菜单2",  List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("菜单3",  List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("菜单4",  List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("菜单5",  List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("菜单6",  List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("菜单7",  List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
        MENU_ITEMS.put("菜单8",  List.of("页面1", "页面2", "页面3", "页面4", "页面5"));
    }
}
