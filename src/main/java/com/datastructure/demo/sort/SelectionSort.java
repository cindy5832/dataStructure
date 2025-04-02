package com.datastructure.demo.sort;

import java.util.Arrays;

// 選擇排序
// 每輪選擇，找出最大 (最小)的元素，並把他交換到合適的位置
public class SelectionSort {
    public static void sort(int[] arr) {
        // 1. 選擇輪數 a.length - 1
        // 2. 交換的索引位置 初始 a.length -1
        for (int right = arr.length - 1; right > 0; right--) {
            int max = right;
            for (int i = 0; i < right; i++) {
                if (arr[i] > arr[max]) {
                    max = i;
                }
            }
            if (max != right) {
                swap(arr, max, right);
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }

    public static void main(String[] args) {
        int[] a = {6, 5, 4, 3, 2, 1};
        System.out.println(Arrays.toString(a));
        sort(a);
        System.out.println(Arrays.toString(a));
    }
}
