package com.rocketseat.expertsclub.tddwebflux;


import com.rocketseat.expertsclub.tddwebflux.domain.Order;

public class OrderBuilder {

    public static Order order() {
        return Order.builder()
                .id("ec-1")
                .name("tdd com webflux")
                .price(6000L)
                .build();
    }

}
