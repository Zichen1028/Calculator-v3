package com.socialmedia.calculator.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Item {

    private int quantity;
    private String format;
    private Map<Integer, Long> resultMap;//<bundleNum, frequency>
}
