package com.datastructure.demo.dynamicProgramming;

import java.util.Arrays;
import java.util.Comparator;

public class KnapsackProblem {
    static class Item {

        int index;
        int weight;
        int value;
        String name;

        public Item(int index, String name, int weight, int value) {
            this.index = index;
            this.weight = weight;
            this.value = value;
            this.name = name;
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
                    ", name='" + name + '\'' +
                    '}';
        }

        private static int select(Item[] items, int total) {
            int[][] dp = new int[items.length][total + 1];
            Item item0 = items[0];
            for (int i = 0; i < total + 1; i++) {
                if (i >= item0.weight) {
                    dp[0][i] = item0.value; // 裝得下
                } else {
                    dp[0][i] = 0; // 裝不下
                }
            }
            print(dp);
            for (int i = 1; i < dp.length; i++) {
                Item item = items[i];
                for (int j = 0; j < total + 1; j++) {
                    if (j >= item.weight) {
                        // 裝得下
                        dp[i][j] = Integer.max(dp[i - 1][j], item.value + dp[i - 1][j - item.weight]);
                    } else {
                        // 裝不下
                        dp[i][j] = dp[i - 1][j];
                    }
                    print(dp);
                }
            }
            return dp[dp.length - 1][total];
        }

        private static void print(int[][] dp) {
            for (int i = 0; i < dp[0].length; i++) {
                System.out.print(i + "\t");
            }
            System.out.println();
            for (int i = 0; i < dp.length; i++) {
                for (int j = 0; j < dp[i].length; j++) {
                    System.out.print(dp[i][j] + "\t");
                }
                System.out.println();
            }
            System.out.println("===============================================");
        }

        /**
         * 1. n個物品都是液體，有重量和價值
         * 2. 要取走10L 液體
         * 3. 每次可以不拿、全拿或拿一部分，問最高價值多少
         * 編號  重量(L)   價值
         * 1      4       1600    gold
         * 2      8       2400    Ruby
         * 3      5       30      silver
         * 4      1       10000   diamond
         **/
        public static void main(String[] args) {
            Item[] items = new Item[]{
                    new Item(1, "gold", 4, 1600),
                    new Item(2, "Ruby", 8, 2400),
                    new Item(3, "silver", 5, 30),
                    new Item(4, "diamond", 1, 10000),
            };
            int total = select(items, 10);
            System.out.println(total);
        }
    }

}
