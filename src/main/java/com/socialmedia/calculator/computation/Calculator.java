package com.socialmedia.calculator.computation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@AllArgsConstructor
public class Calculator {

    /**
     * Transfer an array to a list
     * Add a number to the end
     * Transfer the list back to an array
     * @param arr   An array
     * @param x     the number needs to be added
     * @return
     */
    private int[] addX(int[] arr, int x) {
        List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        arrList.add(x);
        return arrList.stream().mapToInt(i->i).toArray();
    }

    /**
     * Algorithm of the bundle calculator
     * @param numsInBundle
     * @param quantity
     * @return resultList   A list that is the result of the calculation
     */
    public List<Integer> calculating(List<Integer> numsInBundle, int quantity) {
        int[] nums = numsInBundle.stream().mapToInt(i->i).toArray();
        int[] result = new int[quantity + 1];
        Arrays.fill(result, quantity + 1);
        int[][] nums_results = new int[quantity + 1][1];
        result[0] = 0;

        for (int i = 1; i < quantity + 1; i++) {
            for (int num : nums) {
                if ((i >= num) && ((result[i-num] + 1) < result[i])) {
                    result[i] = result[i-num] + 1;
                    nums_results[i] = addX(nums_results[i-num], num);
                }
            }
        }

        List<Integer> resultList = Arrays.stream(nums_results[quantity]).boxed().collect(Collectors.toList());
        return resultList;
    }
}
