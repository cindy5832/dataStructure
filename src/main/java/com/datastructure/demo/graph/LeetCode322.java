package com.datastructure.demo.graph;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCode322 {
    static int min = -1; // 需要最少硬幣數
    public int coinChange(int[] coins, int amount) {
        rec(0, coins, amount, new AtomicInteger(-1), new LinkedList<>(), true);
        return min;
    }

    private void rec(int index, int[] coins, int reminder, AtomicInteger count, LinkedList<Integer> stack, boolean first) {
        if (!first) {
            stack.push(coins[index]);
        }
        count.incrementAndGet(); // count++
        // 1. 剩餘金額 < 0
        if (reminder < 0) {
            System.out.println("無解" + stack);
        }
        // 2. 剩餘金額 > 0
        else if (reminder == 0) {
            System.out.println("有解" + stack);
            if(min == -1){
                min = count.get();
            }else{
                min = Integer.min(min, count.get());
            }
            // 3. 剩餘金額 == 0
        } else {
            for (int i = index; i < coins.length; i++) {
                rec(i, coins, reminder - coins[i], count, stack, false);
            }
        }
        count.decrementAndGet(); // count--
        if (!stack.isEmpty()) {
            stack.pop();
        }
    }

    public static void main(String[] args) {
        LeetCode322 leetCode322 = new LeetCode322();
        int count = leetCode322.coinChange(new int[]{1, 2, 5}, 5);
        System.out.println(count);
    }


}
