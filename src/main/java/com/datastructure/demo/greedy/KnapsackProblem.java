package com.datastructure.demo.greedy;

import java.util.Arrays;
import java.util.Comparator;

// 貪心解法 - 可能無法達到最優解
public class KnapsackProblem {
    /**
     * 背包問題
     * 1. n個物品是固體，有重量和價值
     * 2. 現在要取走不超過10克的物品
     * 3. 每次可以不拿或全拿，問罪高價值是多少
     *
     *    編號    重量(g)   價值($)
     *     0       1       1_000_000
     *     1       4       1600
     *     2       8       2400
     *     3       5       30
     **/

    static class Item {

        int index;
        int weight;
        int value;

        public Item(int index, int weight, int value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
        }

        public int unitValue() {
            return value / weight;
        }


        @Override
        public String toString() {
            return "Item{" +
                    "index=" + index +
                    ", weight=" + weight +
                    ", value=" + value +
                    '}';
        }

        private static void select(Item[] items, int total) {
            Arrays.sort(items, Comparator.comparing(Item::unitValue).reversed());
            int max = 0; // 最大價值
            for (Item item : items) { // 拿得完
                System.out.println(item);
                if(total >= item.weight){
                    total -= item.weight;
                    max += item.value;
                }
            }
            System.out.println(max);
        }

        /**
         * 1. n個物品都是液體，有重量和價值
         * 2. 要取走10L 液體
         * 3. 每次可以不拿、全拿或拿一部分，問最高價值多少
         * 編號  重量(L)   價值
         * 0      4       24
         * 1      8       160
         * 2      2       4000
         * 3      6       108
         * 4      1       4000
         **/
        public static void main(String[] args) {
            Item[] items = new Item[]{
                    new Item(0, 1, 1000000),
                    new Item(1, 4, 1600),
                    new Item(2, 8, 2400),
                    new Item(3, 5, 30),
            };
            select(items, 10);
        }
    }
}
