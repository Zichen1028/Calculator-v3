package com.socialmedia.calculator.processor;

import com.socialmedia.calculator.bundle.Bundle;
import com.socialmedia.calculator.computation.Calculator;
import com.socialmedia.calculator.item.Item;
import com.socialmedia.calculator.order.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class Processor {

    Order order;
    Bundle bundle;

    public Map<Integer, Long> transferResultListToMap(List<Integer> resultList) {
        Map<Integer, Long> frequencyMap =
                resultList.stream().collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        return frequencyMap;
    }

    public void processing() {
        List<Item> itemList = order.getOrderList();
        for (Item item : itemList) {
            String format = item.getFormat();
            int quantity = item.getQuantity();

            HashMap<Integer, BigDecimal> bundleMap = this.bundle.getAllProductFormatAndBundleMap().get(format);
            List<Integer> numbersOfOneBundlelist = bundle.getNumbersInOneBundle(bundleMap);
            List<Integer> resultList = Calculator.calculatingChange(numbersOfOneBundlelist, quantity);
            resultList.remove(0);
            Map<Integer, Long> frequencyMap = transferResultListToMap(resultList);
            item.setResultMap(frequencyMap);
        }
    }
}
