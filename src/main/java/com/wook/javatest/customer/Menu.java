package com.wook.javatest.customer;

import java.util.List;

public class Menu {

    private final List<MenuItem> menuItems;

    public  Menu(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }

    public MenuItem choose(String name) {
        this.menuItems.stream()
                .filter(menuItem -> menuItem.matches(name))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 메뉴 이름 입니다"));
        return new MenuItem(name, 15000);
    }
}
