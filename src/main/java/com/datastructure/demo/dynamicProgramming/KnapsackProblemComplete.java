package com.datastructure.demo.dynamicProgramming;

public class KnapsackProblemComplete {
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
            for (int j = 0; j < total + 1; j++) {
                if (j >= item0.weight) {
                    dp[0][j] = dp[0][j - item0.weight] + item0.value;
                }
            }
            print(dp);
            for (int i = 1; i < items.length; i++) {
                for (int j = 0; j < total + 1; j++) {
                    Item item = items[i];
                    int x = dp[i - 1][j];// 上次最大值
                    if (j >= item.weight) {
                        dp[i][j] = Integer.max(x, dp[i][j - item.weight] + item.value);
                    } else {
                        dp[i][j] = x;
                    }
                }
            }
            print(dp);
            return dp[items.length - 1][total];
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
         * 0   1   2   3   4   5   6
         * 1  0   0   c   c   cc  cc  ccc
         * 2  0   0   c   s   cc  sc  ccc
         * 3  0   0   c   s   g   g   cg
         **/
        public static void main(String[] args) {
            Item[] items = new Item[]{
                    new Item(1, "copper", 2, 3), // c
                    new Item(2, "silver", 3, 4), // s
                    new Item(3, "gold", 4, 7)  // g
            };
            int total = select(items, 6);
            System.out.println(total);
        }
    }

}
