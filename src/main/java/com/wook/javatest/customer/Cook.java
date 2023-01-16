package com.wook.javatest.customer;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Cook {

    private final String name;
    private final int price ;

    public Cook(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public Cook(MenuItem menuItem) {
        this.name = menuItem.getName();
        this.price = menuItem.getPrice();
    }
}
