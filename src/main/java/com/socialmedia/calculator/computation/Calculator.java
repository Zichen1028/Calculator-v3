package com.socialmedia.calculator.computation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
    public static int[] addX_2(int[] arr, int x) {
        List<Integer> arrList = Arrays.stream(arr).boxed().collect(Collectors.toList());
        arrList.add(x);
        return arrList.stream().mapToInt(i->i).toArray();
    }

    public List<Integer> calculating(List<Integer> coins, int amount) {
        int[] nums = coins.stream().mapToInt(i->i).toArray();
        int[] result = new int[amount + 1];
        Arrays.fill(result, amount + 1);
        int[][] nums_results = new int[amount + 1][1];
        result[0] = 0;

        for (int i = 1; i < amount + 1; i++) {
            for (int num : nums) {
                if ((i >= num) && ((result[i-num] + 1) < result[i])) {
                    result[i] = result[i-num] + 1;
                    nums_results[i] = addX_2(nums_results[i-num], num);
                }
            }
        }

        List<Integer> resultList = Arrays.stream(nums_results[amount]).boxed().collect(Collectors.toList());
        return resultList;
    }

    //Not completed yet, needs to be fixed later,
    // and the Naming also needs to be changed to fit the Java's convention 'camelCase'
    public List<Integer> calculatingChange(List<Integer> coins, int amount) {

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < amount; i++) {
            result.add(amount + 1);
        }

        result.add(0, 0);

        List<Integer> zero = new ArrayList<>();
        zero.add(0);
        List<List<Integer>> coins_results = new ArrayList<>();
        for (int i = 0; i < amount + 1; i++) {
            coins_results.add(zero);
        }

        for (int i = 1; i < amount + 1; i++) {
            for (Integer coin : coins) {
                if ((i >= coin) && ((result.get(i - coin) + 1) < result.get(i))) {
                    result.set(i, result.get(i - coin) + 1);
                    List<Integer> store = coins_results.get(i - coin);
                    store.add(coin);
                    Collections.copy(coins_results.get(i), store);
                }
            }
        }

        return coins_results.get(amount);
    }
}
