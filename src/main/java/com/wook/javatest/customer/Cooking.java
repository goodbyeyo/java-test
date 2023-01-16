package com.wook.javatest.customer;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Cooking {

    public Cook makeCook(MenuItem menuItem) {
        Cook cook = new Cook(menuItem);
        return new Cook("초밥", 15000);
    }
}
