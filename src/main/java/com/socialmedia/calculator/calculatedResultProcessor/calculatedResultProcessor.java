package com.socialmedia.calculator.calculatedResultProcessor;

import com.socialmedia.calculator.bundle.Bundle;
import com.socialmedia.calculator.computation.SolutionAlgorithm;
import com.socialmedia.calculator.model.Item;
import com.socialmedia.calculator.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class calculatedResultProcessor {

    private Bundle bundle;

    /**
     * Count frequency of elements in a List
     * Then transfer the result as a map: key is the unique element, value is the frequency
     * @param resultList
     * @return
     */
    private Map<Integer, Long> transferResultListToMap(List<Integer> resultList) {
        Map<Integer, Long> frequencyMap =
                resultList.stream().collect(Collectors.groupingBy(Function.identity(),
                        Collectors.counting()));
        return frequencyMap;
    }

    /**
     * Write the processed result from each item to the output txt file
     */
    public void transferResultToFrequencyMap(Order order, SolutionAlgorithm solutionAlgorithm) {
        List<Item> itemList = order.getOrderList();

        for (Item item : itemList) {
            String format = item.getFormat();
            int quantity = item.getQuantity();

            Map<Integer, BigDecimal> bundleMap = this.bundle.getAllProductFormatAndBundleMap().get(format);
            List<Integer> numbersOfOneBundlelist = this.bundle.getNumbersInOneBundle(bundleMap);
            List<Integer> resultList = solutionAlgorithm.calculateSolution(numbersOfOneBundlelist, quantity);
            resultList.remove(0);
            Map<Integer, Long> frequencyMap = transferResultListToMap(resultList);
            item.setResultMap(frequencyMap);
        }
    }
}
