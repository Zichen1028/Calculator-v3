package com.socialmedia.calculator.order;

import com.socialmedia.calculator.item.Item;
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

    public void addItem(Item item) {
        orderList.add(item);
    }
}
