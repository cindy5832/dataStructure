package com.datastructure.demo.greedy;

import java.util.Arrays;
import java.util.Comparator;


// 貪心解法
public class FractionalKnapsackProblem {

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
                }else{ // 拿不完
                    max += total * item.unitValue();
                    break;
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
                    new Item(0, 4, 24),
                    new Item(1, 8, 160),
                    new Item(2, 2, 4000),
                    new Item(3, 6, 108),
                    new Item(4, 1, 4000),
            };
            select(items, 10);
        }
    }
}
