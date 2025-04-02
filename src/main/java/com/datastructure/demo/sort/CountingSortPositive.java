package com.datastructure.demo.sort;

import java.util.Arrays;

public class CountingSortPositive {
    /**
     * 1. 找到最大值，創建一個大小為 最大值 +1 的 count 數組
     * 2. count 數組的索引對應原始數組的元素，用來統計該元素的出現次數
     * 3. 遍歷 count數組，根據count數組的索引 (即原數組的元素) 以即出現次數，生成排序後內容
     * count 數組的索引：以排序好
     * 前提：待排序元素 >= 0 且最大值不能太大
     **/
    public static void sort(int[] arr) {
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
            if (arr[i] < min) {
                min = arr[i];
            }
        }
        int[] count = new int[max - min + 1];
        for (int v : arr) {
            count[v - min]++; // v 原始數組元素 - min = count 索引
        }
        System.out.println(Arrays.toString(count));
        int k = 0;
        for (int i = 0; i < count.length; i++) {
            // i + min 代表原始數組元素 count[i] 元素出現次數
            while (count[i] > 0) {
                arr[k++] = i + min;
                count[i]--;
            }
        }
    }

    public static void main(String[] args) {
        int[] a = {5, 1, 1, 3, 0, -1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
