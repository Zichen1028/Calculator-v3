package com.socialmedia.calculator.computation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Setter
@Getter
@AllArgsConstructor
public class Calculator {

    //Not completed yet, needs to be fixed later,
    // and the Naming also needs to be changed to fit the Java's convention 'camelCase'
    public static List<Integer> calculatingChange(List<Integer> coins, int amount) {

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
