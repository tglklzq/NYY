package org.lzq.nyy.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuConfig {
    public static final Map<Integer, List<String>> ROLE_MENU_MAP = new HashMap<>();

    static {
        ROLE_MENU_MAP.put(1, List.of("Menu1", "Menu2", "Menu3", "Menu4"));
        ROLE_MENU_MAP.put(2, List.of("Menu4", "Menu5", "Menu6", "Menu7", "Menu8"));
        // 其他角色的菜单映射
    }

    public static final Map<String, List<String>> MENU_ITEMS = new LinkedHashMap<>(); // 使用LinkedHashMap

    static {
        MENU_ITEMS.put("Menu1", List.of("1", "1", "1", "1", "1"));
        MENU_ITEMS.put("Menu2", List.of("2", "2", "2", "2", "2"));
        MENU_ITEMS.put("Menu3", List.of("3", "3", "3", "3", "3"));
        MENU_ITEMS.put("Menu4", List.of("4", "4", "4", "4", "4"));
        MENU_ITEMS.put("Menu5", List.of("5", "5", "5", "5", "5"));
        MENU_ITEMS.put("Menu6", List.of("6", "6", "6", "6", "6"));
        MENU_ITEMS.put("Menu7", List.of("7", "7", "7", "7", "7"));
        MENU_ITEMS.put("Menu8", List.of("8", "8", "8", "8", "8"));
    }
}
