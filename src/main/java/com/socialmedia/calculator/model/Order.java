package com.socialmedia.calculator.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Order {

    private List<Item> orderList;

    public Order() {
        this.orderList = new ArrayList<>();
    }

    /**
     * add an item to the order list
     * @param item
     */
    public void addItem(Item item) {
        orderList.add(item);
    }
}
